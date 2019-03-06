package com.gmail.dotharary.naty.CVMaker;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainMenuPanel extends JPanel {
    private JLabel welcomeLabel, inputLabel;
    private JButton open;
    private JButton save;
    private JTextField filePath;

    public MainMenuPanel() {
        welcomeLabel = new JLabel("Welcome to CVMaker! Making your CVs easier since 2019.");
        inputLabel = new JLabel("Please input source .mpt files to generate CV graphs from:");

        // Define buttons for opening file and saving the location of the output
        open = new JButton("Open file");
        open.addActionListener(new openButtonListener());
        save = new JButton("Save location");
        save.addActionListener(new saveButtonListener());

        // Define labels to display selected open and save paths
        JLabel labelOpenPath = new JLabel("Input file:");
        JLabel labelSavePath = new JLabel("Save location:");

        filePath = new JTextField(10);

        add(welcomeLabel);
        add(inputLabel);
        add(open);
        add(save);
        add(labelOpenPath);
        add(labelSavePath);

        setPreferredSize(new Dimension(750, 200));
    }
}


// Listener for saving location button TODO MAKE THIS CHOOSE DIRECTORIES ONLY
class saveButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        fileChooserWindow();
    }

    public String fileChooserWindow() {
        // Uses filechooser to help users choose input files
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnSave = jfc.showSaveDialog(null);

        if (returnSave == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String saveFilePath = selectedFile.getAbsolutePath();
            return saveFilePath;
        }
        return null;
    }
}