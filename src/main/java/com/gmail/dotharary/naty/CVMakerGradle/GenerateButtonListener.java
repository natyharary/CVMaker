package com.gmail.dotharary.naty.CVMakerGradle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static com.gmail.dotharary.naty.CVMakerGradle.AcquireData.*;
import static com.gmail.dotharary.naty.CVMakerGradle.AcquireData.callGraphData;

public class GenerateButtonListener implements ActionListener {

    // When clicking on generate JButton, methods from the AcquireData class are called to read the file,
    // parse data and generate a graph
    public void actionPerformed(ActionEvent event) {

        // TODO temporarily here. inputArgs[0] specifies which cycle to use
        String[] inputArgs = new String[3];
        inputArgs[0] = "1";

        // Get filePath for first file from paths list at OpenButtonListener
        String filePath = OpenButtonListener.paths.get(0);
        ArrayList readLines = readFile(filePath);
        ArrayList returnedLists = parseData(readLines, inputArgs[0]);
        try {
            callGraphData(returnedLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
