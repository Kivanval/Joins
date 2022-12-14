package org.example.join.service;

import org.apache.commons.collections4.CollectionUtils;
import org.example.join.model.DataRow;
import org.example.join.model.JoinedDataRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InnerJoinOperationTest {

    @Spy
    InnerJoinOperation<Integer, String, String> joinOperation;


    @Test
    void emptyCollectionsTest() {
        Collection<DataRow<Integer, String>> leftCollection = new ArrayList<>();
        Collection<DataRow<Integer, String>> rightCollection = new ArrayList<>();
        assertEquals(0, joinOperation.join(leftCollection, rightCollection).size());
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideCollection")
    void emptyRightCollectionTest(Collection<DataRow<Integer, String>> leftCollection) {
        Collection<DataRow<Integer, String>> rightCollection = new ArrayList<>();
        assertEquals(0, joinOperation.join(leftCollection, rightCollection).size());
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideCollection")
    void emptyLeftCollectionTest(Collection<DataRow<Integer, String>> rightCollection) {
        Collection<DataRow<Integer, String>> leftCollection = new ArrayList<>();
        assertEquals(0, joinOperation.join(leftCollection, rightCollection).size());
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideNonIntersectingCollections")
    void nonIntersectingCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                        Collection<DataRow<Integer, String>> rightCollection) {
        assertEquals(0, joinOperation.join(leftCollection, rightCollection).size());
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#providePartiallyIntersectingCollections")
    void partiallyIntersectingCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                              Collection<DataRow<Integer, String>> rightCollection,
                                              Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(
                resultCollection,
                joinOperation.join(leftCollection, rightCollection))
        );
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideFullyIntersectingCollections")
    void fullyIntersectingCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                          Collection<DataRow<Integer, String>> rightCollection,
                                          Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(
                resultCollection,
                joinOperation.join(leftCollection, rightCollection))
        );
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideLeftSizeSmallerThenRightSizeCollections")
    void leftSizeSmallerThenRightSizeCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                                     Collection<DataRow<Integer, String>> rightCollection,
                                                     Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(
                resultCollection,
                joinOperation.join(leftCollection, rightCollection))
        );
        verify(joinOperation).hashMapOnLeftCollection(leftCollection, rightCollection);
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideRightSizeSmallerThenLeftSizeCollections")
    void rightSizeSmallerThenLeftSizeCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                                     Collection<DataRow<Integer, String>> rightCollection,
                                                     Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(
                resultCollection,
                joinOperation.join(leftCollection, rightCollection))
        );
        verify(joinOperation).hashMapOnRightCollection(leftCollection, rightCollection);
    }

    @ParameterizedTest
    @MethodSource("org.example.join.service.InnerJoinOperationArguments#provideRightSizeEqualsLeftSizeCollections")
    void rightSizeEqualsLeftSizeCollectionsTest(Collection<DataRow<Integer, String>> leftCollection,
                                                Collection<DataRow<Integer, String>> rightCollection,
                                                Collection<JoinedDataRow<Integer, String, String>> resultCollection) {
        assertTrue(CollectionUtils.isEqualCollection(
                resultCollection,
                joinOperation.join(leftCollection, rightCollection))
        );
        verify(joinOperation).hashMapOnRightCollection(leftCollection, rightCollection);
    }
}