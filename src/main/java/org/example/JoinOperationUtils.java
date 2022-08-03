package org.example;

import java.util.*;
import java.util.stream.Collectors;

public final class JoinOperationUtils {
    private JoinOperationUtils() {
    }

    public static <T1, T2> int minSize(Collection<T1> leftCollection, Collection<T2> rightCollection) {
        return Math.min(leftCollection.size(), rightCollection.size());
    }

    public static <K, V> Map<K, V> dataRowCollectionToHashMap(Collection<DataRow<K, V>> dataRows) {
        return dataRows.stream().collect(Collectors.toMap(DataRow::getKey, DataRow::getValue));
    }

    public static <K extends Comparable<K>, V> Set<DataRow<K, V>> dataRowCollectionToIndex(Collection<DataRow<K, V>> dataRows) {
        TreeSet<DataRow<K, V>> index = new TreeSet<>(Comparator.comparing(DataRow::getKey));
        index.addAll(dataRows);
        return index;
    }


}
