package org.example;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataRow <K, V>{

    private K key;

    private V value;

}
