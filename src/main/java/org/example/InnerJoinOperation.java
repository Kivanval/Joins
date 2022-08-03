package org.example;

import java.util.ArrayList;
import java.util.Collection;

public class InnerJoinOperation<K, V1, V2>
        implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection,
                                                     Collection<DataRow<K, V2>> rightCollection) {
        ArrayList<JoinedDataRow<K, V1, V2>> resultCollection = new ArrayList<>(JoinOperationUtils
                .minSize(leftCollection, rightCollection));
        for (DataRow<K, V1> leftDataRow : leftCollection) {
            for (DataRow<K, V2> rightDataRow : rightCollection) {
                if (leftDataRow.getKey().equals(rightDataRow.getKey())) {
                    JoinedDataRow<K, V1, V2> joinedDataRow = new JoinedDataRow<>(
                            leftDataRow,
                            rightDataRow
                    );
                    resultCollection.add(joinedDataRow);
                }
            }
        }
        return resultCollection;
    }
}
