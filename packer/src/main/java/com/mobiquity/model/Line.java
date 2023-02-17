package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Line {
    private double maxWeight;
    private List<Package> packages;

    public Line(double maxWeight, List<Package> packages){
        this.maxWeight = maxWeight;
        List<Package> localPackages = new LinkedList<>(packages);
        this.packages = localPackages;
    }
}
