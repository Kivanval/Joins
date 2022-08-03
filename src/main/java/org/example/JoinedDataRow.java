package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinedDataRow<K, V1, V2> {
    private K key;
    private V1 leftValue;
    private V2 rightValue;


    public JoinedDataRow(DataRow<K, V1> leftDataRow, DataRow<K, V2> rightDataRow) {
        if (!leftDataRow.getKey().equals(rightDataRow.getKey())) {
            throw new IllegalArgumentException();
        }
        this.key = leftDataRow.getKey();
        this.leftValue = leftDataRow.getValue();
        this.rightValue = rightDataRow.getValue();
    }
}
