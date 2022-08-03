package org.example.join.join;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataRow <K, V>{

    private K key;

    private V value;

}
