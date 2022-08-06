package org.example.join.service;

import org.example.join.model.DataRow;
import org.example.join.model.JoinedDataRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class RightJoinOperation<K, V1, V2> implements HashJoinOperation<K, V1, V2> {

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> hashMapOnRightCollection(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection) {
        Collection<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(rightCollection.size());
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
                hashMap.remove(leftDataRow.getKey());
            }
        }
        for (Map.Entry<K, V2> memoryRightMapEntry : hashMap.entrySet()) {
            JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                    memoryRightMapEntry.getKey(),
                    null,
                    memoryRightMapEntry.getValue()
            );
            resultCollection.add(joinedDataRow);
        }
        return resultCollection;
    }

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> hashMapOnLeftCollection(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection) {
        Collection<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(rightCollection.size());
        Map<K, V1> hashMap = JoinOperationUtils.dataRowCollectionToHashMap(leftCollection);
        for (DataRow<K, V2> rightDataRow : rightCollection) {
            V1 leftValue = hashMap.get(rightDataRow.getKey());
            JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                    rightDataRow.getKey(),
                    leftValue,
                    rightDataRow.getValue()
            );
            resultCollection.add(joinedDataRow);
            hashMap.remove(rightDataRow.getKey());
        }
        return resultCollection;
    }
}
