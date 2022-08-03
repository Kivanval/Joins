package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class RightJoinOperation<K, V1, V2>
        implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {
    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection, Collection<DataRow<K, V2>> rightCollection) {
        ArrayList<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(JoinOperationUtils
                .minSize(leftCollection, rightCollection));
        if (leftCollection.size() >= rightCollection.size()) {
            for (DataRow<K, V2> rightDataRow : rightCollection) {
                boolean hasJoin = false;
                for (DataRow<K, V1> leftDataRow : leftCollection) {
                    if (rightDataRow.getKey().equals(leftDataRow.getKey())) {
                        JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                                leftDataRow,
                                rightDataRow
                        );
                        resultCollection.add(joinedDataRow);
                        hasJoin = true;
                    }
                }
                if (!hasJoin) {
                    JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                            rightDataRow.getKey(),
                            null,
                            rightDataRow.getValue()
                    );
                    resultCollection.add(joinedDataRow);
                }
            }
        } else {
            Map<K, V1> hashMap = JoinOperationUtils.dataRowCollectionToHashMap(leftCollection);
            for (DataRow<K, V2> rightDataRow : rightCollection) {
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
