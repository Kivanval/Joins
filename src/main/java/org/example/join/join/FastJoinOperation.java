package org.example.join.join;

import java.util.Collection;

public interface FastJoinOperation<K, V1, V2>
        extends JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    default Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection,
                                                      Collection<DataRow<K, V2>> rightCollection) {
        if (leftCollection.size() >= rightCollection.size())
            return hashMapOnRightCollection(leftCollection, rightCollection);
        else
            return hashMapOnLeftCollection(leftCollection, rightCollection);
    }

    Collection<JoinedDataRow<K, V1, V2>> hashMapOnRightCollection(Collection<DataRow<K, V1>> leftCollection,
                                                                  Collection<DataRow<K, V2>> rightCollection);

    Collection<JoinedDataRow<K, V1, V2>> hashMapOnLeftCollection(Collection<DataRow<K, V1>> leftCollection,
                                                                 Collection<DataRow<K, V2>> rightCollection);
}
