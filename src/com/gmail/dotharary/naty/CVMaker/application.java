package com.gmail.dotharary.naty.CVMaker;

/* ===============================================================================
CVMaker is a graphing app for creating Cyclic Voltammetry graphs out of txt files.
Graphing is done with the open-license GRAL Java Graphing library.

Methods:
userInput - gets filename, path and cycle number from user
readFile - reads the file content into data structure
parseData - parses the file
=============================================================================== */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class application {

    public static void main(String[] args){
        userInput();
        readFile();
        parseData();
    }

        public static void userInput (){
            Scanner reader = new Scanner(System.in); //use the scanner utility for user input

            // Initializing strings and list
            String addmore = "Y";
            String filename;

            // TODO add options for interacting with the whole list, for now it is only the first item
            List fileList = new ArrayList();

            // Enter user input loop. This will loop until user inputs done as filename
            while (!addmore.equals("N")) {
                // Take user input for first filename. This will loop until Done or done is entered
                System.out.println("Please input file name and then Enter:\n(example: for Nitrogen.txt, type Nitrogen) to add to graph, or Done to continue:");
                filename = reader.nextLine();
                fileList.add(filename);

                // Take file path from the user, for the filename specified
                System.out.println("Please input the path for the file " + filename + " and then Enter\n" +
                        "(example: for file C:\\MyFolder\\data\\Nitrogen.txt, type C:\\MyFolder\\Results)");
                String path = reader.nextLine();

                System.out.println("For the file:" + path + "\\" + filename + ",\nPlease input cycle # to be used");
                int cycle = reader.nextInt();
                System.out.println("You have chosen cycle #" + cycle + ".");
//            TODO add option for multiple cycle choice

                System.out.println("Do you want to add more files? (Y/N)");
                addmore = reader.nextLine();


                // TODO consider moving this to somewhere else
                file = path + "." + filename;
            }
        }

        // TODO CONTINUE FROM HERE!!!! why is filename not public?...
        public static void readFile() {
            System.out.println("file is " + file);
            Charset charset = Charset.forName("US-ASCII");
            try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

        public static void parseData() {

    }
}
