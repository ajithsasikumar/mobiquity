package com.mobiquity.packer;

import com.mobiquity.constants.Constants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Combination;
import com.mobiquity.model.Line;
import com.mobiquity.model.Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Packer {

  private static List<String> linesList = new ArrayList<>();
  private static List<Line> parsedlinesList = new ArrayList<>();


  public static Result result = new Result();

  public static String pack (String filePath) throws APIException {

    readLinesFromFile(filePath);
    parsedlinesList = parseLine(linesList);

    List<Combination> combFinalList = new ArrayList<>();
      for(Line line: parsedlinesList){
        Combination comb = result.findCombinations(line);

        combFinalList.add(comb);
        System.out.println("combFinalList :=======>>>>>>"+combFinalList.toString());
      }

    return combFinalList.toString();
  }



  private static void readLinesFromFile(String filePath) throws APIException{
    try {
      linesList = Files
              .lines(Path.of(filePath))
              .collect(Collectors.toList());
    }catch(IOException e){
      throw new APIException(e.getMessage());
    }
  }

  private static List<Line> parseLine(List<String> linesList) throws APIException {
    List<Line> parsedList = new LinkedList<>();
    List<Package> packages = new LinkedList<>();
    for(int i=0;i<linesList.size();i++){
      String line = linesList.get(i);
      String[] splitLine = line.split(":");

      if (splitLine.length != 2) {
        throw new APIException("Error in line "+(i++));
      }

      final double maxWeight = Double.parseDouble(splitLine[0]);

      Pattern pattern = Pattern.compile(Constants.REGEX);
      Matcher matcher = pattern.matcher(splitLine[1]);

      while (matcher.find()) {
        try{
          int index = Integer.valueOf(matcher.group(Constants.INDEX));
          double weight = Double.valueOf(matcher.group(Constants.WEIGHT));
          double cost = Double.valueOf(matcher.group(Constants.COST));

          if (index > Constants.MAX_ITEMS_IN_LINE || index < 0) {
            throw new APIException("index mas be in range 1 to "+ Constants.MAX_ITEMS_IN_LINE, line, i+1);
          }

          if (weight > Constants.MAX_WEIGHT || weight < 0) {
            throw new APIException("weight mas be in range 0 to "+Constants.MAX_WEIGHT, line, i+1);
          }

          if (cost > Constants.MAX_COST || cost < 0) {
            throw new APIException("cost mas be in range 0 to "+ Constants.MAX_COST, line, i+1);
          }

          packages.add(new Package(index, weight, cost));

        }catch (Exception e){
          throw new APIException("Error in Package",line,i+1);
        }
      }
      Line lineObj = new Line(maxWeight, packages);
      parsedList.add(lineObj);
      packages.clear();
    }
   return parsedList;
  }

}
