package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class LeftJoinOperation<K, V1, V2>
        implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection,
                                                     Collection<DataRow<K, V2>> rightCollection) {
        ArrayList<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(JoinOperationUtils
                .minSize(leftCollection, rightCollection));
        if (leftCollection.size() >= rightCollection.size()) {
            Map<K, V2> hashMap = JoinOperationUtils.dataRowCollectionToHashMap(rightCollection);
            for (DataRow<K, V1> leftDataRow : leftCollection) {
                V2 rightValue = hashMap.get(leftDataRow.getKey());
                JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                        leftDataRow.getKey(),
                        leftDataRow.getValue(),
                        rightValue
                );
                resultCollection.add(joinedDataRow);
            }
        } else {
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
                    hashMap.remove(rightDataRow.getKey());
                }
            }
            for (Map.Entry<K, V1> memoryLeftMapEntry : hashMap.entrySet()) {
                JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                        memoryLeftMapEntry.getKey(),
                        memoryLeftMapEntry.getValue(),
                        null
                );
                resultCollection.add(joinedDataRow);
            }
        }
        return resultCollection;
    }
}
