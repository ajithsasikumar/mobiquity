package com.mobiquity.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Package {
    private int index;
    private double weight;
    private double cost;
}
