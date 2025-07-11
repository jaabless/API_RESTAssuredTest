package org.apitests.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PostTestDataProvider {
    public static Stream<Arguments> postData() {
        return Stream.of(
                Arguments.of("foo", "bar", 1)
        );
    }

    public static Stream<Arguments> invalidPostData() {

        return Stream.of(
                Arguments.of("", "", -1)
        );
    }
}
