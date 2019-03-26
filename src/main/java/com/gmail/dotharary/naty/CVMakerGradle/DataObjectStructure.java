package com.gmail.dotharary.naty.CVMakerGradle;

import java.util.ArrayList;

// An interface defining the getter/setter methods for data in the program.

public interface DataObjectStructure {
    public void setInputFilePath(String userInputFilePath);

    public String getInputFilePath();

    public void setOutputFilePath(String userOutputFilePath);

    public String getOutputFilePath();

    public void setCycleNumber(String userCycleNumber);

    public String getCycleNumber();

    public void setReadLines(ArrayList<String> userReadLines);

    public ArrayList<String> getReadLines();

    public void setParsedLines(ArrayList<ArrayList<Double>> userParsedLines);

    public ArrayList<ArrayList<Double>> getParsedLines();
}
