package org.example.join;


import org.example.join.join.DataRow;
import org.example.join.join.JoinedDataRow;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class InnerJoinOperationDataRowArguments {

    static Stream<Arguments> provideCollection() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Ukraine"),
                                new DataRow<>(1, "Germany"),
                                new DataRow<>(2, "France")
                        )
                ),
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Kyiv"),
                                new DataRow<>(1, "Berlin"),
                                new DataRow<>(3, "Budapest")
                        )
                )
        );
    }

    static Stream<Arguments> provideNonIntersectingCollections() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Ukraine"),
                                new DataRow<>(1, "Germany"),
                                new DataRow<>(2, "France")
                        ),
                        Arrays.asList(
                                new DataRow<>(3, "Budapest"),
                                new DataRow<>(4, "Riga"),
                                new DataRow<>(5, "Tokyo")
                        )
                )
        );
    }

    static Stream<Arguments> providePartiallyIntersectingCollections() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Ukraine"),
                                new DataRow<>(1, "Germany"),
                                new DataRow<>(2, "France")
                        ),
                        Arrays.asList(
                                new DataRow<>(0, "Kyiv"),
                                new DataRow<>(1, "Berlin"),
                                new DataRow<>(3, "Budapest")
                        ),
                        Arrays.asList(
                                new JoinedDataRow<>(0, "Ukraine", "Kyiv"),
                                new JoinedDataRow<>(1, "Germany", "Berlin")
                        )
                )
        );
    }

    static Stream<Arguments> provideFullyIntersectingCollections() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Ukraine"),
                                new DataRow<>(1, "Germany"),
                                new DataRow<>(2, "France")
                        ),
                        Arrays.asList(
                                new DataRow<>(0, "Kyiv"),
                                new DataRow<>(1, "Berlin"),
                                new DataRow<>(2, "Paris")
                        ),
                        Arrays.asList(
                                new JoinedDataRow<>(0, "Ukraine", "Kyiv"),
                                new JoinedDataRow<>(1, "Germany", "Berlin"),
                                new JoinedDataRow<>(2, "France", "Paris")
                        )
                )
        );
    }

    static Stream<Arguments> provideLeftSizeSmallerThenRightSizeCollections() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new DataRow<>(0, "Ukraine")
                        ),
                        Arrays.asList(
                                new DataRow<>(0, "Kyiv"),
                                new DataRow<>(1, "Berlin"),
                                new DataRow<>(3, "Budapest")
                        ),
                        Arrays.asList(
                                new JoinedDataRow<>(0, "Ukraine", "Kyiv")
                        )
                )
        );
    }

    static Stream<Arguments> provideRightSizeSmallerThenLeftSizeCollections() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Ukraine"),
                                new DataRow<>(1, "Germany"),
                                new DataRow<>(2, "France")
                        ),
                        Arrays.asList(
                                new DataRow<>(0, "Kyiv")
                        ),
                        Arrays.asList(
                                new JoinedDataRow<>(0, "Ukraine", "Kyiv")
                        )
                )
        );
    }

    static Stream<Arguments> provideRightSizeEqualsLeftSizeCollections() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new DataRow<>(0, "Ukraine"),
                                new DataRow<>(1, "Germany"),
                                new DataRow<>(2, "France")
                        ),
                        Arrays.asList(
                                new DataRow<>(0, "Kyiv"),
                                new DataRow<>(1, "Berlin"),
                                new DataRow<>(3, "Budapest")
                        ),
                        Arrays.asList(
                                new JoinedDataRow<>(0, "Ukraine", "Kyiv"),
                                new JoinedDataRow<>(1, "Germany", "Berlin")
                        )
                )
        );
    }

}
