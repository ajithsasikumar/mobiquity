package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Combination;
import com.mobiquity.model.Line;
import com.mobiquity.model.Package;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Result {

    public Combination combination;
    public List<Combination> combinationList = new LinkedList<>();
    public List<Combination> sortedComboList = new LinkedList<>();

    public Combination findCombinations(Line line) throws APIException{
        combinationList.clear();
        sortedComboList.clear();
        List<Package> packageList = line.getPackages();
        double maxWeight = line.getMaxWeight();
        List<Package> combPkgList = new LinkedList<>();
        double weight =0;
        double cost =0;
        try {
            for (int i = 0; i < packageList.size(); i++) {
                weight = packageList.get(i).getWeight();

                if (weight <= maxWeight) {
                    combPkgList.add(packageList.get(i));
                    cost = packageList.get(i).getCost();

                    for (int j = (i + 1); j < packageList.size(); j++) {
                        weight += packageList.get(j).getWeight();
                        if (weight <= maxWeight) {
                            combPkgList.add(packageList.get(j));
                            cost += packageList.get(j).getCost();
                        } else weight -= packageList.get(j).getWeight();

                    }

                    combination = new Combination(weight, cost, combPkgList);
                    combinationList.add(combination);
                }

                combPkgList.clear();

            }

        if(combinationList.size()>0) {
            return getBestCombo(combinationList);
        }
        }catch (Exception e){
            throw new APIException(e.getMessage());
        }
        return null;
    }

    public  Combination getBestCombo(List<Combination> combinationList){
        Collections.sort(combinationList);
        return combinationList.get(0);
    }


}
