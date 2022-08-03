package org.example.join.join;

import java.util.*;
import java.util.stream.Collectors;

public final class JoinOperationUtils {
    private JoinOperationUtils() {
    }

    static <T1, T2> int minSize(Collection<T1> leftCollection, Collection<T2> rightCollection) {
        return Math.min(leftCollection.size(), rightCollection.size());
    }

    static <K, V> Map<K, V> dataRowCollectionToHashMap(Collection<DataRow<K, V>> dataRows) {
        return dataRows.stream().collect(Collectors.toMap(DataRow::getKey, DataRow::getValue));
    }

}
