package com.gmail.dotharary.naty.CVMakerGradle;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static com.gmail.dotharary.naty.CVMakerGradle.MainMenu.*;

public class GenerateButtonListener implements ActionListener {

    // When clicking on generate JButton, methods from the AcquireData class are called to read the file,
    // parse data and generate a graph
    public void actionPerformed(ActionEvent event) {

        // Set cycle number
        //TODO make it user input and not hard-coded
        data0.setCycleNumber("1");
        data1.setCycleNumber("1");
        data2.setCycleNumber("1");
        data3.setCycleNumber("1");
        data4.setCycleNumber("1");

        // Sets output path AND name
        //TODO move this elsewhere, and also make it only the path and not include the filename
        data0.setOutputFilePath("C:\\CVMaker_data\\");
        data1.setOutputFilePath("C:\\CVMaker_data\\");
        data2.setOutputFilePath("C:\\CVMaker_data\\");
        data3.setOutputFilePath("C:\\CVMaker_data\\");
        data4.setOutputFilePath("C:\\CVMaker_data\\");


        // Call methods in AcquireData. Get variables from class before calling each method
        String inputFile = data0.getInputFilePath();
        AcquireData.readFile(inputFile);

        ArrayList readLines = data0.getReadLines();
        String cycle = data0.getCycleNumber();
        AcquireData.parseData(readLines, cycle);

        // Get amount of pre-defined data objects
        int dataCounter = DataForGraphingList.size();

        // Iterate through all data objects
        for (int i = 0; i < dataCounter; i++) {
            DataForGraphing currentObj = DataForGraphingList.get(i);

            //Check if user inputted inputFilePath, to proceed to graphing. If not, do nothing
            String currentPath = currentObj.getInputFilePath(); //TODO later make a sanity check for all parms
            if (currentPath != null){
                String outputFileName = currentObj.getOutputFilePath() + "file" + i + ".jpg";
                try {
                    new GraphData(currentObj.getParsedLines(), outputFileName); // Call GraphData
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
            }

        }
    }
}