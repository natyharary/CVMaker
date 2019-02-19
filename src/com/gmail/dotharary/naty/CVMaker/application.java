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
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class application {

    public static void main(String[] args){
/*        String[] inputArgs = userInput();
        readFile(inputArgs[1], inputArgs[0]);*/
        parseData();
    }

    // TODO add options to add multiple files
    /*inputArgs is containing the data for user input. It is returned by this method after calling to continue to the next ones.
     It does not support multiple files yet, but it will*/
        public static String[] userInput (){
            Scanner reader = new Scanner(System.in); //use the scanner utility for user input

            // Initializing strings and list
            String addmore = "Y";
            String filename = null;
            String path;
            String[] inputArgs = new String[2];

            // Enter user input loop. This will loop until user inputs done as filename
            while (!addmore.equals("N")) {
                // Take user input for first filename. This will loop until Done or done is entered
                System.out.println("Please input file name and then Enter:\n(example: for Nitrogen.txt, type Nitrogen) to add to graph, or Done to continue:");
                inputArgs[0] = reader.nextLine();

                // Take file path from the user, for the filename specified
                System.out.println("Please input the path for the file " + filename + " and then Enter\n" +
                        "(example: for file C:\\MyFolder\\data\\Nitrogen.txt, type C:\\MyFolder\\Results)");
                inputArgs[1] = reader.nextLine();

                /*System.out.println("For the file:" + path + "\\" + filename + ",\nPlease input cycle # to be used");*/
                /*int cycle = reader.nextInt();*/
                /*System.out.println("You have chosen cycle #" + cycle + ".");*/
//            TODO add option for multiple cycle choice

                System.out.println("Do you want to add more files? (Y/N)");
                addmore = reader.nextLine();


            }
            return inputArgs;
        }

        // Reads the file specified by the user
        public static void readFile(String path, String filename) {
            String file = path + "\\" + filename;
            Charset charset = Charset.forName("ISO-8859-1"); // an ASCII encoding that doesnt throw IOerrors
            // TODO why try?
            try (BufferedReader reader = (BufferedReader) Files.newBufferedReader(Paths.get(file), charset)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

        public static void parseData() {
            String line1 = "2\t0\t0\t1\t0\t0.000000000000000E+000\t-5.1828343E-001\t-5.1848990E-001\t-2.778122961899498E-006\t1.000000000000000E+000\t0.0000000E+000\t1.4404287E-009";
            String line2 = "2\t0\t0\t1\t0\t1.109999971959041E-002\t-5.1938158E-001\t-5.1937181E-001\t-2.206588085165682E-004\t1.000000000000000E+000\t-2.7420837E-009\t1.1460396E-007";


            //TODO CONTINUE HERE!!!!!!!!!! now I need to learn about parsing inside streams. maybe not with map, but an array...
            Stream.of(line1, line2)
                    .map(line -> line.split("\t"))
                    .forEach(System.out::println);
        }
}
