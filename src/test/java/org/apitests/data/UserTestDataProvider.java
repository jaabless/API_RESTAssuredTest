package org.apitests.data;


import org.apitest.models.User;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import java.util.stream.Stream;

public class UserTestDataProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(org.junit.jupiter.api.extension.ExtensionContext context) {
        return Stream.of(
                // Positive case: Valid user
                Arguments.of(new User() {{
                    setName("Test User");
                    setUsername("testuser");
                    setEmail("test@example.com");
                }}, 201),
                // Negative case: Invalid email
                Arguments.of(new User() {{
                    setName("Test User");
                    setUsername("testuser");
                    setEmail("invalid-email");
                }}, 400),
                // Edge case: Empty username
                Arguments.of(new User() {{
                    setName("Test User");
                    setUsername("");
                    setEmail("test@example.com");
                }}, 201),
                // Edge case: Long name
                Arguments.of(new User() {{
                    setName("x".repeat(100));
                    setUsername("longnameuser");
                    setEmail("longname@example.com");
                }}, 201),
                // Negative case: Missing required fields
                Arguments.of(new User() {{
                    setEmail("missingfields@example.com");
                }}, 400)
        );
    }

    // Data provider for update tests
    public static Stream<Arguments> provideUpdateUserData() {
        return Stream.of(
                // Positive case: Complete update
                Arguments.of(new User() {{
                    setId(1);
                    setName("Updated User");
                    setUsername("updateduser");
                    setEmail("updated@example.com");
                }}, 200),
                // Negative case: Non-existent ID
                Arguments.of(new User() {{
                    setId(9999);
                    setName("Non-existent User");
                    setUsername("nonexistent");
                    setEmail("nonexistent@example.com");
                }}, 404)
        );
    }
}