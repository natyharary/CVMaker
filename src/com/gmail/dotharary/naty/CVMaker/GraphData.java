package com.gmail.dotharary.naty.CVMaker;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.graphics.Orientation;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LinearRenderer2D;
import de.erichseifert.gral.plots.lines.DiscreteLineRenderer2D;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.plots.points.SizeablePointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphData extends AbstractPanel {

    public GraphData(ArrayList returnedLists) {
        getData(returnedLists);
    }

    public void getData(ArrayList returnedLists) {
        // Generate data
        DataTable data = new DataTable(Double.class, Double.class);

        ArrayList<Double> voltageList = (ArrayList<Double>) returnedLists.get(0);
        ArrayList<Double> currentList = (ArrayList<Double>) returnedLists.get(1);

        int max = voltageList.size();

        // Populate data with voltageList and currentList TODO IM NOT SURE THIS WORKS YET
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
        plot.getTitle().setText("CV plot"); //TODO this might not be neccessary


        // Format plot area //TODO THIS IS FOR A DISPLAY
        plot.getPlotArea().setBackground(new RadialGradientPaint(
                new Point2D.Double(0.5, 0.5),
                0.75f,
                new float[]{0.6f, 0.8f, 1.0f},
                new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 32), new Color(0, 0, 0, 128)}
        ));
        plot.getPlotArea().setBorderStroke(null);

        // Format axes
        AxisRenderer axisRendererX = new LinearRenderer2D();
        AxisRenderer axisRendererY = plot.getAxisRenderer(XYPlot.AXIS_Y);
        axisRendererX.setLabel(new de.erichseifert.gral.graphics.Label("Voltage"));
        plot.setAxisRenderer(XYPlot.AXIS_X, axisRendererX);
        // Custom tick labels
        Map<Double, String> labels = new HashMap<>();
        axisRendererX.setCustomTicks(labels);
        // Custom stroke for the x-axis
        BasicStroke stroke = new BasicStroke(2f);
        axisRendererX.setShapeStroke(stroke);
        de.erichseifert.gral.graphics.Label currentAxisLabel = new Label("Current");
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

        // Format data lines
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

        // Add plot to Swing component
        add(new InteractivePanel(plot), BorderLayout.CENTER);

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