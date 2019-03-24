package com.gmail.dotharary.naty.CVMakerGradle;

import java.util.ArrayList;

public class DataForGraphing {
    String inputFilePath;
    String outputFilePath;
    String cycleNumber;
    ArrayList<String> readLines;
    ArrayList<ArrayList<Double>> parsedLines;

    static int count =+ 1; //Indicates the number of instances

    // Indicates the number of DataForGraphing instances
    public static int getCount(){
        return count;
    }

    // Getter setter methods for all instance variables
    public void setInputFilePath(String userInputFilePath){
        inputFilePath = userInputFilePath;
    }

    public String getInputFilePath(){
        return inputFilePath;
    }

    public void setOutputFilePath(String userOutputFilePath){
        outputFilePath = userOutputFilePath;
    }

    public String getOutputFilePath(){
        return outputFilePath;
    }

    public void setCycleNumber(String userCycleNumber){
        cycleNumber = userCycleNumber;
    }

    public String getCycleNumber(){
        return cycleNumber;
    }

    public void setReadLines(ArrayList<String> userReadLines){
        readLines = userReadLines;
    }

    public ArrayList<String> getReadLines(){
        return readLines;
    }

    public void setParsedLines(ArrayList<ArrayList<Double>> userParsedLines){
        parsedLines = userParsedLines;
    }

    public ArrayList<ArrayList<Double>> getParsedLines(){
        return parsedLines;
    }
}
