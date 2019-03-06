package com.gmail.dotharary.naty.CVMaker;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Listener for opening input file button
class openButtonListener implements ActionListener {
    public String inputFilePath;

    public void actionPerformed(ActionEvent event) {
        String tempFilePath = fileChooserWindow();
        inputFilePath = tempFilePath;
        //TODO FIND A WAY TO RETURN THE STRING FROM HERE! tempFilePath gets the string but I cant return it to the panel
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

/*    public String getFilePath(){
        String tempFilePath;
        inputFilePath = tempFilePath;
        return inputFilePath;
    }*/
}

