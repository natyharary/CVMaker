package com.gmail.dotharary.naty.CVMakerGradle;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JLabel welcomeLabel, inputLabel;
    private JButton open;
    private JButton generate;
    private JTextField filePath;

    public MainMenuPanel() {
        welcomeLabel = new JLabel("Welcome to CVMaker! Making your CVs easier since 2019.");
        inputLabel = new JLabel("Please input source .mpt files to generate CV graphs from:");

        // Button for opening file
        open = new JButton("Open file");
        open.addActionListener(new OpenButtonListener());

        // Button for generating graph
        generate = new JButton("Generate");
        generate.addActionListener(new GenerateButtonListener());

        add(welcomeLabel);
        add(inputLabel);
        add(open);
        add(generate);

        setPreferredSize(new Dimension(750, 200));
    }
}