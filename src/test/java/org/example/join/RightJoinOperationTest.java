package org.example.join;

import org.apache.commons.collections4.CollectionUtils;
import org.example.join.join.DataRow;
import org.example.join.join.JoinOperation;
import org.example.join.join.JoinedDataRow;
import org.example.join.join.RightJoinOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RightJoinOperationTest {

    JoinOperation<DataRow<Integer, String>, DataRow<Integer, String>,
            JoinedDataRow<Integer, String, String>> joinOperation = new RightJoinOperation<>();

    @Test
    void emptyCollectionsTest() {
        Collection<DataRow<Integer, String>> leftCollection = new ArrayList<>();
        Collection<DataRow<Integer, String>> rightCollection = new ArrayList<>();
        assertEquals(0, joinOperation.join(leftCollection, rightCollection).size());
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideEmptyRightCollection")
    void emptyRightCollectionTest(Collection<DataRow<Integer, String>> leftCollection) {
        Collection<DataRow<Integer, String>> rightCollection = new ArrayList<>();
        assertEquals(0, joinOperation.join(leftCollection, rightCollection).size());
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideEmptyLeftCollection")
    void emptyLeftCollectionTest(Collection<DataRow<Integer, String>> rightCollection,
                                 Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        Collection<DataRow<Integer, String>> leftCollection = new ArrayList<>();
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideNonIntersectingCollections")
    void nonIntersectingCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                        Collection<DataRow<Integer, String>> rightCollection,
                                        Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#providePartiallyIntersectingCollections")
    void partiallyIntersectingCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                              Collection<DataRow<Integer, String>> rightCollection,
                                              Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideFullyIntersectingCollections")
    void fullyIntersectingCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                          Collection<DataRow<Integer, String>> rightCollection,
                                          Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideLeftSizeSmallerThenRightSizeCollections")
    void leftSizeSmallerThenRightSizeCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                                     Collection<DataRow<Integer, String>> rightCollection,
                                                     Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideLeftSizeSmallerThenRightSizeCollections")
    void rightSizeSmallerThenLeftSizeCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                                     Collection<DataRow<Integer, String>> rightCollection,
                                                     Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }

    @ParameterizedTest
    @MethodSource("org.example.join.RightJoinOperationDataRowArguments#provideRightSizeEqualsLeftSizeCollections")
    void rightSizeEqualsLeftSizeCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                                Collection<DataRow<Integer, String>> rightCollection,
                                                Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(resultCollection,
                joinOperation.join(leftCollection, rightCollection)));
    }
}
