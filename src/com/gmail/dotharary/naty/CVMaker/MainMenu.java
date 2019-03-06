package com.gmail.dotharary.naty.CVMaker;

import javax.swing.*;

// TODO consider using the intellij GUI designer to make a better menu

/*This will be the main GUI of the software, and will allow the user to customize the title of the X axis,
choose files through a filechooser JSelector*/

public class MainMenu {

    public static void main(String[] args){
        JFrame frame = new JFrame("CVMaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenuPanel panel = new MainMenuPanel();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
