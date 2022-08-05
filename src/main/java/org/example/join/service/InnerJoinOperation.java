package org.example.join.service;

import org.example.join.model.DataRow;
import org.example.join.model.JoinedDataRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class InnerJoinOperation<K, V1, V2> implements FastJoinOperation<K, V1, V2> {

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> hashMapOnRightCollection(Collection<DataRow<K, V1>> leftCollection,
                                                                         Collection<DataRow<K, V2>> rightCollection) {
        Collection<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(JoinOperationUtils
                .minSize(leftCollection, rightCollection));
        Map<K, V2> hashMap = JoinOperationUtils.dataRowCollectionToHashMap(rightCollection);
        for (DataRow<K, V1> leftDataRow : leftCollection) {
            if (hashMap.containsKey(leftDataRow.getKey())) {
                V2 rightValue = hashMap.get(leftDataRow.getKey());
                JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                        leftDataRow.getKey(),
                        leftDataRow.getValue(),
                        rightValue
                );
                resultCollection.add(joinedDataRow);
            }
        }
        return resultCollection;
    }

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> hashMapOnLeftCollection(Collection<DataRow<K, V1>> leftCollection,
                                                                        Collection<DataRow<K, V2>> rightCollection) {
        Collection<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(JoinOperationUtils
                .minSize(leftCollection, rightCollection));
        Map<K, V1> hashMap = JoinOperationUtils.dataRowCollectionToHashMap(leftCollection);
        for (DataRow<K, V2> rightDataRow : rightCollection) {
            if (hashMap.containsKey(rightDataRow.getKey())) {
                V1 leftValue = hashMap.get(rightDataRow.getKey());
                JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                        rightDataRow.getKey(),
                        leftValue,
                        rightDataRow.getValue()
                );
                resultCollection.add(joinedDataRow);
            }
        }
        return resultCollection;
    }
}
