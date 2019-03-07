package com.gmail.dotharary.naty.CVMaker;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

// Listener for opening input file button
public class openButtonListener implements ActionListener {
    public String inputFilePath;

    public void actionPerformed(ActionEvent event) {
        String tempFilePath = fileChooserWindow();
        /*inputFilePath = tempFilePath;*/
        PathContainer.addPath(tempFilePath);
    }

    public String fileChooserWindow() {
        // Uses filechooser to help users choose input files
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnOpen = jfc.showOpenDialog(null);

        if (returnOpen == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String tempFilePath = selectedFile.getAbsolutePath();
            return tempFilePath;
        }
        return null;
    }

    ///////// Diff file
    public static class PathContainer {
        public static ArrayList<String> paths;

        public static ArrayList<String> addPath(String tempFilePath) {
            paths.add(tempFilePath);
            return paths; //TODO I WANT TO RETURN THE ABSOLUTEPATH FROM HERE
        }
    }

/*    public String getFilePath(){
        String tempFilePath;
        inputFilePath = tempFilePath;
        return inputFilePath;
    }*/
}

