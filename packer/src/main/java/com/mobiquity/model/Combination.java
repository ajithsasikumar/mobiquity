package com.mobiquity.model;

import lombok.Data;
import java.util.LinkedList;
import java.util.List;

@Data
public class Combination implements Comparable {

    private Double weight;
    private Double cost;
    private List<Package> combPkgList;

    public Combination(double weight,double cost,List<Package> combPkgList){
        this.weight = weight;
        this.cost = cost;
        List<Package> pkgList = new LinkedList<>(combPkgList);
        this.combPkgList = pkgList;
    }

    @Override
    public int compareTo(Object o) {
        Combination combObj = (Combination)o;

        if(this.getCost()<combObj.getCost()) return 1;
        else if (this.getCost()>combObj.getCost()) return -1;
        else return this.getCost().toString().compareTo(combObj.getCost().toString());

    }
}
