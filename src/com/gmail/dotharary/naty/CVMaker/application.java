package com.gmail.dotharary.naty.CVMaker;

/* ===============================================================================
CVMaker is a graphing app for creating Cyclic Voltammetry graphs out of txt files.
Graphing is done with the open-license GRAL Java Graphing library.

Methods:
userInput - gets filename, path and cycle number from user
readFile - reads the file content into data structure
parseData - parses the file
=============================================================================== */

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.graphics.Orientation;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LogarithmicRenderer2D;
import de.erichseifert.gral.plots.lines.DiscreteLineRenderer2D;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.plots.points.SizeablePointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class application {

    private static final Color COLOR1 = ;

    public static void main(String[] args) {
        String[] inputArgs = userInput();
        ArrayList readLines = readFile(inputArgs[1], inputArgs[0]);
        ArrayList returnedLists = parseData(readLines, inputArgs[2]);
        graphData(returnedLists);
        /*graphData(returnedLists).showInFrame();*/
    }

    // TODO add options to add multiple files
    /*inputArgs is containing the data for user input. It is returned by this method after calling to continue to the next ones.
     It does not support multiple files yet, but it will*/
    public static String[] userInput() {
        Scanner reader = new Scanner(System.in); //use the scanner utility for user input

        // Initializing strings and list
        String addmore = "Y";
        String[] inputArgs = new String[3]; // InputArgs will contain the variables inputted by the users

        // Enter user input loop. This will loop until user inputs done as filename
        while (!addmore.equals("N")) {
            // Take user input for first filename. This will loop until Done or done is entered
            System.out.println("Please input file name and then Enter:\n(example: for Nitrogen.txt, type Nitrogen) to add to graph, or Done to continue:");
            /*inputArgs[0] = reader.nextLine();*/ //TODO UNDO THIS LATER
            inputArgs[0] = "test.txt";

            // Take file path from the user, for the filename specified
            System.out.println("Please input the path for the file " + inputArgs[0] + " and then Enter\n" +
                    "(example: for file C:\\MyFolder\\data\\Nitrogen.txt, type C:\\MyFolder\\Results)");
            /*inputArgs[1] = reader.nextLine();*/ // TODO UNDO TIS LATER
            inputArgs[1] = "C:\\CVMaker_data";

            System.out.println("For the file: " + inputArgs[1] + "\\" + inputArgs[0] + ",\nPlease input cycle # to be used");
            /*inputArgs[2] = (reader.nextLine());*/ //TODO UNDO TIS LATER
            inputArgs[2] = "1";

            // TODO add option for multiple cycle choice
            System.out.println("Do you want to add more files? (Y/N)");
            /*addmore = reader.nextLine();*/ //TODO UNDO TIS LATER
            addmore = "N";
        }
        return inputArgs;
    }

    // Reads the file specified by the user
    public static ArrayList<String> readFile(String path, String filename) {
        ArrayList<String> readLines = new ArrayList<String>();
        String file = path + "\\" + filename;
        Charset charset = Charset.forName("ISO-8859-1"); // an ASCII encoding that doesnt throw IOerrors
        // TODO why try?
        try (BufferedReader reader = (BufferedReader) Files.newBufferedReader(Paths.get(file), charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return readLines;
    }

    public static ArrayList<ArrayList<Double>> parseData(ArrayList readLines, String inputtedUserCycle) {
        // Defining ArrayLists for data
        ArrayList<Double> voltageList = new ArrayList<>();
        ArrayList<Double> currentList = new ArrayList<>();
        ArrayList<Double> cycleList = new ArrayList<>();
        // Transforming inputtedUserCycle from String to int to userCycle
        // TODO find a smarter way to transform user input from string to integer. Maybe to make the array contain int and string?...
        int userCycle = Integer.parseInt(inputtedUserCycle);

        // TODO change the counter later to filter out according to the "mode ox/red" parameters line
        for (int counter = 56; counter < readLines.size(); counter++) {
            /*            String currentLine = (String) readLines.get(counter); //currentLine holds the current line now read from the readLines ArrayList*/

            ArrayList<String> parsedLine = new ArrayList<>();

            String currentLine = (String) readLines.get(counter);
            for (String str : currentLine.split("\t")) {
                parsedLine.add(str); //Parsing the currentLine into an ArrayList called parsedLine
            }

            // Convert the scientific notation number of a cycle into an integer, which will be used as lineCycle in the if statement
            int lineCycle = new BigDecimal(parsedLine.get(9)).intValue();

            // Select only the lines which contain the cycle the user chose, which is userCycle
            if (lineCycle == userCycle) {


                /* // TODO THIS IS AN OLD PIECE OF CODE, BACK WHEN I USED int AS THE DATA TYPE OF THE DATA SERIES
                *//* Adding Voltage to voltageList first
                This is a bit hairy: I had to convert bigDecimal -> double -> int, because the number is in scientific notation
                (requires bigDecimal) and converting bigDecimal to int directly gives 0 value*//*
                BigDecimal decimalVolt = new BigDecimal(parsedLine.get(7));
                double doubleVoltage = decimalVolt.doubleValue();
                int intVoltage = (int) doubleVoltage;
                voltageList.add(intVoltage);
                */

                //Assigning variables out of parsedLine to ArrayLists called voltageList, currentList and cycleList,
                double doubleVoltage = Double.parseDouble(parsedLine.get(7));
                voltageList.add(doubleVoltage);

                double doubleCurrent = Double.parseDouble(parsedLine.get(8));;
                currentList.add(doubleCurrent);

                // Cycle is just an integer, but it is assigned as double since I cant return arrayLists of mixed data types
                double doubleCycle = Double.parseDouble(parsedLine.get(9));
                cycleList.add(doubleCycle);
            }
        }

        // Creating an ArrayList called returnedLists, to return all 3 ArrayLists containing the parsed data
        ArrayList<ArrayList<Double>> returnedLists = new ArrayList<>(3);
        returnedLists.add(voltageList);
        returnedLists.add(currentList);
        returnedLists.add(cycleList);

        return returnedLists;
    }

    public static void graphData(ArrayList returnedLists) {
        // Generate data
        DataTable data = new DataTable(Double.class, Double.class);

        ArrayList<Double> voltageList = (ArrayList<Double>) returnedLists.get(0);
        ArrayList<Double> currentList = (ArrayList<Double>) returnedLists.get(1);

        int max = voltageList.size();

        for (int i = 0; i < max; i++) {
            data.add(voltageList.get(i), currentList.get(i));
        }

        // Create data series
        // TODO MULTIPLE SERIES OPTION TBA
        DataSeries firstPlot = new DataSeries(data, 0, 1);

        // Create new xy-plot
        XYPlot plot = new XYPlot(firstPlot);

        // TODO COPIED FROM SIMPLEXYPLOT, LETS SEE WHAT GOES NOW
        /// Format plot
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        plot.setBackground(Color.WHITE);
        plot.getTitle().setText(getDescription());

        // Format plot area
        plot.getPlotArea().setBackground(new RadialGradientPaint(
                new Point2D.Double(0.5, 0.5),
                0.75f,
                new float[] { 0.6f, 0.8f, 1.0f },
                new Color[] { new Color(0, 0, 0, 0), new Color(0, 0, 0, 32), new Color(0, 0, 0, 128) }
        ));
        plot.getPlotArea().setBorderStroke(null);

        // Format axes
        AxisRenderer axisRendererX = new LogarithmicRenderer2D();
        AxisRenderer axisRendererY = plot.getAxisRenderer(XYPlot.AXIS_Y);
        axisRendererX.setLabel(new de.erichseifert.gral.graphics.Label("Logarithmic axis"));
        plot.setAxisRenderer(XYPlot.AXIS_X, axisRendererX);
        // Custom tick labels
        Map<Double, String> labels = new HashMap<>();
        labels.put(2.0, "Two");
        labels.put(1.5, "OnePointFive");
        axisRendererX.setCustomTicks(labels);
        // Custom stroke for the x-axis
        BasicStroke stroke = new BasicStroke(2f);
        axisRendererX.setShapeStroke(stroke);
        de.erichseifert.gral.graphics.Label linearAxisLabel = new Label("Linear axis");
        linearAxisLabel.setRotation(90);
        axisRendererY.setLabel(linearAxisLabel);
        // Change intersection point of Y axis
        axisRendererY.setIntersection(1.0);
        // Change tick spacing
        axisRendererX.setTickSpacing(2.0);

        // Format rendering of data points
        PointRenderer sizeablePointRenderer = new SizeablePointRenderer();
        sizeablePointRenderer.setColor(GraphicsUtils.deriveDarker(COLOR1));
        plot.setPointRenderers(firstPlot, sizeablePointRenderer);
        PointRenderer defaultPointRenderer = new DefaultPointRenderer2D();
        defaultPointRenderer.setErrorVisible(true);

        // Format data lines
        DiscreteLineRenderer2D discreteRenderer = new DiscreteLineRenderer2D();
        discreteRenderer.setColor(COLOR1);
        discreteRenderer.setStroke(new BasicStroke(
                3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                10.0f, new float[] {3f, 6f}, 0.0f));
        plot.setLineRenderers(firstPlot, discreteRenderer);
        // Custom gaps for points
        discreteRenderer.setGap(2.0);
        discreteRenderer.setGapRounded(true);
        // Custom ascending
        discreteRenderer.setAscentDirection(Orientation.VERTICAL);
        discreteRenderer.setAscendingPoint(0.5);

        // Add plot to Swing component
        add(new InteractivePanel(plot), BorderLayout.CENTER);
    }

    public String getTitle() {
        return "x-y plot";
    }

    public static String getDescription() {
        return "Styled x-y plot with example data";
    }
}