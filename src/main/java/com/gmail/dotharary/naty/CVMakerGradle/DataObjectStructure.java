package com.gmail.dotharary.naty.CVMakerGradle;

import java.util.ArrayList;

// An interface defining the getter/setter methods for data in the program.

public interface DataObjectStructure {
    void setInputFilePath(String userInputFilePath);

    String getInputFilePath();

    void setOutputFilePath(String userOutputFilePath);

    String getOutputFilePath();

    void setCycleNumber(String userCycleNumber);

    String getCycleNumber();

    void setReadLines(ArrayList<String> userReadLines);

    ArrayList<String> getReadLines();

    void setParsedLines(ArrayList<ArrayList<Double>> userParsedLines);

    ArrayList<ArrayList<Double>> getParsedLines();
}
