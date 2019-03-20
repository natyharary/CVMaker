package com.gmail.dotharary.naty.CVMakerGradle;

/* ===============================================================================
CVMaker is a graphing app for creating Cyclic Voltammetry graphs out of txt files.
Graphing is done with the open-license GRAL Java Graphing library.

Methods:
readFile - reads the file content into data structure
parseData - parses the file
=============================================================================== */

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class AcquireData {

    // Reads the file specified by the user
    public static ArrayList<String> readFile(String filepath) {
        ArrayList<String> readLines = new ArrayList<String>();
        String file = filepath;
        Charset charset = Charset.forName("ISO-8859-1"); // an ASCII encoding that doesnt throw IOerrors

        try (BufferedReader reader = (BufferedReader) Files.newBufferedReader(Paths.get(file), charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return readLines;
    }

    public static ArrayList<ArrayList<Double>> parseData(ArrayList readLines, String inputtedUserCycle) {
        // Defining ArrayLists for data
        ArrayList<Double> voltageList = new ArrayList<>();
        ArrayList<Double> currentList = new ArrayList<>();
        ArrayList<Double> cycleList = new ArrayList<>();
        // Transforming inputtedUserCycle from String to int to userCycle
        // TODO find a smarter way to transform user input from string to integer. Maybe to make the array contain int and string?...
        int userCycle = Integer.parseInt(inputtedUserCycle);

        // TODO change the counter later to filter out according to the "mode ox/red" parameters line
        for (int counter = 56; counter < readLines.size(); counter++) {
            /*            String currentLine = (String) readLines.get(counter); //currentLine holds the current line now read from the readLines ArrayList*/

            ArrayList<String> parsedLine = new ArrayList<>();

            String currentLine = (String) readLines.get(counter);
            for (String str : currentLine.split("\t")) {
                parsedLine.add(str); //Parsing the currentLine into an ArrayList called parsedLine
            }

            // Convert the scientific notation number of a cycle into an integer, which will be used as lineCycle in the if statement
            int lineCycle = new BigDecimal(parsedLine.get(9)).intValue();

            // Select only the lines which contain the cycle the user chose, which is userCycle
            if (lineCycle == userCycle) {

                //Assigning variables out of parsedLine to ArrayLists called voltageList, currentList and cycleList,
                double doubleVoltage = Double.parseDouble(parsedLine.get(7));
                voltageList.add(doubleVoltage);

                double doubleCurrent = Double.parseDouble(parsedLine.get(8));
                ;
                currentList.add(doubleCurrent);

                // Cycle is just an integer, but it is assigned as double since I cant return arrayLists of mixed data types
                double doubleCycle = Double.parseDouble(parsedLine.get(9));
                cycleList.add(doubleCycle);
            }
        }

        // Creating an ArrayList called returnedLists, to return all 3 ArrayLists containing the parsed data
        ArrayList<ArrayList<Double>> returnedLists = new ArrayList<>(3);
        returnedLists.add(voltageList);
        returnedLists.add(currentList);
        returnedLists.add(cycleList);

        return returnedLists;
    }

    public static void callGraphData (ArrayList returnedLists) throws IOException {
        /*new GraphData(returnedLists).showInFrame();*/ //TODO this is used for windowed graphic test
        new GraphData(returnedLists);

    }
}
