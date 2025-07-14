package org.apitests.data;

import org.apitest.models.*;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class AdditionalResourcesTestDataProvider{

    public static Stream<Arguments> provideCommentData() {
        return Stream.of(
                // Positive Case
                Arguments.of(new Comment() {{
                    setPostId(1);
                    setName("Test Comment");
                    setEmail("test@example.com");
                    setBody("This is a test comment");
                }}, 201),
                // Negative Case (Invalid Email)
                Arguments.of(new Comment() {{
                    setPostId(1);
                    setName("Invalid Email Comment");
                    setEmail("invalid-email");
                    setBody("Comment with invalid email");
                }}, 400)
        );
    }

    public static Stream<Arguments> provideAlbumData() {
        return Stream.of(
                // Positive Case
                Arguments.of(new Album() {{
                    setUserId(1);
                    setTitle("Test Album");
                }}, 201),
                // Edge Case (Empty Title)
                Arguments.of(new Album() {{
                    setUserId(1);
                    setTitle("");
                }}, 201)
        );
    }

    public static Stream<Arguments> providePhotoData() {
        return Stream.of(
                // Positive Case
                Arguments.of(new Photo() {{
                    setAlbumId(1);
                    setTitle("Test Photo");
                    setUrl("https://example.com/photo.jpg");
                    setThumbnailUrl("https://example.com/thumb.jpg");
                }}, 201),
                // Negative Case (Missing URL)
                Arguments.of(new Photo() {{
                    setAlbumId(1);
                    setTitle("Photo without URL");
                    setThumbnailUrl("https://example.com/thumb.jpg");
                }}, 400)
        );
    }

    public static Stream<Arguments> provideTodoData() {
        return Stream.of(
                // Positive Case
                Arguments.of(new Todo() {{
                    setUserId(1);
                    setTitle("Test Todo");
                    setCompleted(false);
                }}, 201),
                // Edge Case (Long Title)
                Arguments.of(new Todo() {{
                    setUserId(1);
                    setTitle("x".repeat(100));
                    setCompleted(true);
                }}, 201)
        );

    }


}
