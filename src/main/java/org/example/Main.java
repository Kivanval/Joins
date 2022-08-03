package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<DataRow<Integer, String>> leftCollection = Arrays.asList(
                new DataRow<>(0, "Ukraine"),
                new DataRow<>(1, "Germany"),
                new DataRow<>(2, "France")
        );
        Collection<DataRow<Integer, String>> rightCollection = Arrays.asList(
                new DataRow<>(0, "Kyiv"),
                new DataRow<>(1, "Berlin"),
                new DataRow<>(3, "Budapest")
        );
        System.out.println(new RightJoinOperation<Integer, String, String>().join(leftCollection, rightCollection));
    }
}