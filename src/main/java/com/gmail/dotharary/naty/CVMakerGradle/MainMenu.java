package com.gmail.dotharary.naty.CVMakerGradle;
import javax.swing.*;
import java.util.ArrayList;

// TODO consider using the intellij GUI designer to make a better menu

/*This will be the main GUI of the software, and will allow the user to customize the title of the X axis,
choose files through a filechooser JSelector*/
public class MainMenu {

    // Instantiate DataForGraphing objects
    //TODO make a list that contains those objects instead of instantiating each hard-coded
    static ArrayList<DataForGraphing> DataForGraphingList = new ArrayList<DataForGraphing>();
    static DataForGraphing data0 = new DataForGraphing();
    static DataForGraphing data1 = new DataForGraphing();
    static DataForGraphing data2 = new DataForGraphing();
    static DataForGraphing data3 = new DataForGraphing();
    static DataForGraphing data4 = new DataForGraphing();

    public static void main(String[] args) {
        // Add all DataForGraphing objects to the list
        DataForGraphingList.add(data0);
        DataForGraphingList.add(data1);
        DataForGraphingList.add(data2);
        DataForGraphingList.add(data3);
        DataForGraphingList.add(data4);

        JFrame frame = new JFrame("CVMaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenuPanel panel = new MainMenuPanel();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
