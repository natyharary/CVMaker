package com.gmail.dotharary.naty.CVMakerGradle;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.gmail.dotharary.naty.CVMakerGradle.AcquireData.*;

// TODO consider using the intellij GUI designer to make a better menu

/*This will be the main GUI of the software, and will allow the user to customize the title of the X axis,
choose files through a filechooser JSelector*/

public class MainMenu {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("CVMaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenuPanel panel = new MainMenuPanel();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        AcquireData myAcquireData = new AcquireData();

    }
}
