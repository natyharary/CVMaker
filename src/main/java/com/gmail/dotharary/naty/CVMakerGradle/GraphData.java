package com.gmail.dotharary.naty.CVMakerGradle;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LinearRenderer2D;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.plots.points.SizeablePointRenderer;
import de.erichseifert.gral.util.GraphicsUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphData extends com.gmail.dotharary.naty.CVMakerGradle.AbstractPanel {

    public GraphData(ArrayList returnedLists, String outputFileName) throws IOException {
        byte[] outputStream = writeJpg(returnedLists);
        generateJpg(outputStream, outputFileName);
    }

    public byte[] writeJpg(ArrayList returnedLists) throws IOException {
        // Generate data
        DataTable data = new DataTable(Double.class, Double.class);

        ArrayList<Double> voltageList = (ArrayList<Double>) returnedLists.get(0);
        ArrayList<Double> currentList = (ArrayList<Double>) returnedLists.get(1);

        int max = voltageList.size();

        // Populate data with voltageList and currentList
        for (int i = 0; i < max; i++) {
            data.add(voltageList.get(i), currentList.get(i));
        }

        // Instantiate a dataseries called mainSeries with two columns
        DataSeries mainSeries = new DataSeries(data, 0, 1);

        // Instantiate an XYplot with the series mainSeries
        XYPlot plot = new XYPlot(mainSeries);

        // Format plot
        plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        plot.setBackground(Color.WHITE);
        plot.getTitle().setText("CV plot");
        plot.getPlotArea().setBorderStroke(null);

        // Format axes
        AxisRenderer axisRendererX = new LinearRenderer2D();
        AxisRenderer axisRendererY = plot.getAxisRenderer(XYPlot.AXIS_Y);
        axisRendererX.setLabel(new de.erichseifert.gral.graphics.Label("Potential (V vs. Ref.)")); //TODO MAKE THIS INTERACTIVE SELECTION
        plot.setAxisRenderer(XYPlot.AXIS_X, axisRendererX);
        // Custom tick labels
        Map<Double, String> labels = new HashMap<>();
        axisRendererX.setCustomTicks(labels);
        // Custom stroke for the x-axis
        BasicStroke stroke = new BasicStroke(2f);
        axisRendererX.setShapeStroke(stroke);
        de.erichseifert.gral.graphics.Label currentAxisLabel = new Label("Current (mA)"); //TODO MAKE THIS INTERACTIVE SELECTION
        currentAxisLabel.setRotation(90);
        axisRendererY.setLabel(currentAxisLabel);
        // Change intersection point of Y axis
        axisRendererY.setIntersection(1.0);
        // Change tick spacing
        axisRendererX.setTickSpacing(2.0);

        // Format rendering of data points //TODO TWO COLORS MIGHT BE REDUNDANT, LOOK IT UP
        PointRenderer sizeablePointRenderer = new SizeablePointRenderer();
        sizeablePointRenderer.setColor(GraphicsUtils.deriveDarker(COLOR1));
        plot.setPointRenderers(mainSeries, sizeablePointRenderer);
        PointRenderer defaultPointRenderer = new DefaultPointRenderer2D();
        defaultPointRenderer.setColor(GraphicsUtils.deriveDarker(COLOR2));
        defaultPointRenderer.setErrorVisible(true);
        defaultPointRenderer.setErrorColor(COLOR2);

/*
        //TODO THIS SECTION FROM HERE ->>>>>>
        //Format data lines
        DiscreteLineRenderer2D discreteRenderer = new DiscreteLineRenderer2D();
        discreteRenderer.setColor(COLOR1);
        discreteRenderer.setStroke(new BasicStroke(
                3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                10.0f, new float[]{3f, 6f}, 0.0f));
        plot.setLineRenderers(mainSeries, discreteRenderer);
        // Custom gaps for points
        discreteRenderer.setGap(2.0);
        discreteRenderer.setGapRounded(true);
        // Custom ascending
        discreteRenderer.setAscentDirection(Orientation.VERTICAL);
        discreteRenderer.setAscendingPoint(0.5);
        //TODO TO HERE ->>>>> adds a lot more running time*/

        // Formatting JPG file
        BufferedImage bImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bImage.getGraphics();
        DrawingContext context = new DrawingContext(g2d);
        /*        XYPlot plot = getPlot();*/
        plot.draw(context);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //Create ByteArrayOutputStream named baos
        DrawableWriter imgWriter = DrawableWriterFactory.getInstance().get("image/jpeg"); //Uses GRAL's image writer
        imgWriter.write(plot, baos, 800, 600);
        baos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

    // Generating a JPG file
    public void generateJpg(byte[] outputStream, String outputFileName){
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(outputFileName));
            out.write(outputStream);
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        /*finally {
            if (out != null) out.close();
        }*/
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}