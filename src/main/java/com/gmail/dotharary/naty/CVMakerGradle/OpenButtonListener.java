package com.gmail.dotharary.naty.CVMakerGradle;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

// Listener for opening input file button
public class OpenButtonListener implements ActionListener {
    static ArrayList<String> paths = new ArrayList<String>();

    public void actionPerformed(ActionEvent event) {
        String inputFilePath = fileChooserWindow();
        addPath(inputFilePath);
    }

    public String fileChooserWindow() {
        // Uses filechooser to help users choose input files
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnOpen = jfc.showOpenDialog(null);

        if (returnOpen == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String inputFilePath = selectedFile.getAbsolutePath();
            return inputFilePath;
        }
        return null;
    }

    // Adding path chosen by user in fileChooserWindow method,
    public ArrayList<String> addPath(String inputFilePath) {
        paths.add(inputFilePath);
        return paths;
    }
}