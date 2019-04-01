package com.gmail.dotharary.naty.CVMakerGradle;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.gmail.dotharary.naty.CVMakerGradle.MainMenu.data0;

// Listener for opening input file button
public class OpenButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        String inputFilePath = fileChooserWindow();
        data0.setInputFilePath(inputFilePath); //Add inputFilePath to data object
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
}