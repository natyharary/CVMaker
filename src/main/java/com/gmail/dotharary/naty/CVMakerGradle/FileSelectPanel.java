package com.gmail.dotharary.naty.CVMakerGradle;

import javax.swing.*;
import java.awt.*;

public class FileSelectPanel extends JPanel {
    private JLabel welcomeLabel, inputLabel;
    private JButton open1;
    private JButton open2;
    private JButton open3;
    private JButton open4;
    private JButton open5;
    private JButton generate;
    private JTextField filePath;

    public FileSelectPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        welcomeLabel = new JLabel("Welcome to CVMaker! Making your CVs easier since 2019.");
        inputLabel = new JLabel("Please input source .mpt files to generate CV graphs from:");

        // Buttons for opening file
        open1 = new JButton("Open file 1");
        open1.addActionListener(new OpenButtonListener());
        open2 = new JButton("Open file 2");
        open2.addActionListener(new OpenButtonListener());
        open3 = new JButton("Open file 3");
        open3.addActionListener(new OpenButtonListener());
        open4 = new JButton("Open file 4");
        open4.addActionListener(new OpenButtonListener());
        open5 = new JButton("Open file 5");
        open5.addActionListener(new OpenButtonListener());

        // Button for generating graph
        generate = new JButton("Generate");
        generate.addActionListener(new GenerateButtonListener());

        add(welcomeLabel);
        add(inputLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(open1);
        add(open2);
        add(open3);
        add(open4);
        add(open5);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(generate);

        setPreferredSize(new Dimension(750, 200));
    }
}