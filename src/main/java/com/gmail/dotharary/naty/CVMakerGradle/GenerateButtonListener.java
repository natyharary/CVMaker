package com.gmail.dotharary.naty.CVMakerGradle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static com.gmail.dotharary.naty.CVMakerGradle.AcquireData.*;
import static com.gmail.dotharary.naty.CVMakerGradle.MainMenu.data1;

public class GenerateButtonListener implements ActionListener {

    // When clicking on generate JButton, methods from the AcquireData class are called to read the file,
    // parse data and generate a graph
    public void actionPerformed(ActionEvent event) {

        // Set cycle number
        //TODO make it user input and not hard-coded
        data1.setCycleNumber("1");

        // Call methods in AcquireData. Get variables from class before calling each method
        String inputFile = data1.getInputFilePath();
        AcquireData.readFile(inputFile);

        ArrayList readLines = data1.getReadLines();
        String cycle = data1.getCycleNumber();
        AcquireData.parseData(readLines, cycle);

        try {
            new GraphData(data1.getParsedLines());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
