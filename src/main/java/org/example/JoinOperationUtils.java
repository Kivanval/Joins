package org.example;

import java.util.Collection;

public final class JoinOperationUtils {
    private JoinOperationUtils() {
    }

    public static <T1, T2> int minSize(Collection<T1> leftCollection, Collection<T2> rightCollection) {
        return Math.min(leftCollection.size(), rightCollection.size());
    }


}
