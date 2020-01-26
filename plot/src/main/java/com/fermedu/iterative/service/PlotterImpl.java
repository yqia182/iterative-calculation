package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.geom.Rectangle2D;
import java.io.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-25 23:49
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class PlotterImpl implements Plotter {

    private final static String CHART_TITLE_SUFFIX = " Sample - Growth Curve";

    @Autowired
    private PlottingDataProvider dataProvider;

    /***
     * @Description put the scatter (observed) and line (predicted) charts together to the same chart
     * @Params * @param observed
     * @param predicted
     * @Return org.jfree.chart.JFreeChart
     **/
    private JFreeChart createCombinedChart(SampleData observed, SampleData predicted) {

        // Create a single plot containing both the scatter and line
        XYPlot plot = new XYPlot();

        /** create two collections */
        XYSeries observedSeries = new XYSeries(observed.getYname().concat(" observed"));
        observedSeries.setDescription(observed.getYname().concat(" observed"));
        XYSeries predictedSeries = new XYSeries(predicted.getYname().concat(" predicted"));
        predictedSeries.setDescription(predicted.getYname().concat(" predicted"));
        for (int index = 0; index < observed.getXValueList().size(); index++) {
            double xObserved = observed.getXValueList().get(index).doubleValue();
            double xPredicted = predicted.getXValueList().get(index).doubleValue();
            double yObserved = observed.getYValueList().get(index).doubleValue();
            double yPredicted = predicted.getYValueList().get(index).doubleValue();

            observedSeries.add(xObserved, yObserved);
            predictedSeries.add(xPredicted, yPredicted);
        }

        /** scatter */
        XYSeriesCollection observedDataset = new XYSeriesCollection();
        observedDataset.addSeries(observedSeries);
//        XYItemRenderer observedScatterRenderer = new XYLineAndShapeRenderer(false, true);    // Shapes only
        XYItemRenderer observedScatterRenderer = new XYDotRenderer();    // Shapes only
        ValueAxis scatterDomain = new NumberAxis("time(min)");
        ValueAxis scatterRange = new NumberAxis("Optical Density");
        // Set the scatter data, renderer, and axis into plot
        plot.setDataset(0, observedDataset);
        plot.setRenderer(0, observedScatterRenderer);
        plot.setDomainAxis(0, scatterDomain);
        plot.setRangeAxis(0, scatterRange);
        // Map the scatter to the first Domain and first Range
        plot.mapDatasetToDomainAxis(0, 0);
        plot.mapDatasetToRangeAxis(0, 0);

        /** line chart */
        XYSeriesCollection predictedDataset = new XYSeriesCollection();
        predictedDataset.addSeries(predictedSeries);
        XYItemRenderer predictedLineRenderer = new XYLineAndShapeRenderer(true, false);    // Lines only
        // Set the line data, renderer, and axis into plot
        plot.setDataset(1, predictedDataset);
        plot.setRenderer(1, predictedLineRenderer);
        // Map the line to the second Domain and second Range
        plot.mapDatasetToDomainAxis(1, 0);
        plot.mapDatasetToRangeAxis(1, 0);
        // Create the chart with the plot and a legend
        JFreeChart chart = new JFreeChart(
                observed.getYname().concat(CHART_TITLE_SUFFIX), JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        // Map the line to the FIRST Domain and second Range

        return chart;
    }

    private void streamOutSvg(JFreeChart chart,String sampleName) {
        try {

            DOMImplementation domImpl = GenericDOMImplementation
                    .getDOMImplementation();
            // Create an instance of org.w3c.dom.Document
            Document document = domImpl.createDocument(null, "svg", null);
            // Create an instance of the SVG Generator
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
            // set the precision to avoid a null pointer exception in Batik 1.5
            svgGenerator.getGeneratorContext().setPrecision(6);
            // Ask the chart to render into the SVG Graphics2D implementation
            chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, 400, 300), null);
            // Finally, stream out SVG to a file using UTF-8 character to
            // byte encoding
            boolean useCSS = true;
            Writer out = null;
            out = new OutputStreamWriter(new FileOutputStream(new File(
                    sampleName.concat(".svg"))), "UTF-8");

            svgGenerator.stream(out, useCSS);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SVGGraphics2DIOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void plotBothObservedPredicted(String sampleName) {
        final SampleData observed = dataProvider.getObservedData(sampleName);
        final SampleData predicted = dataProvider.getPredictedData(sampleName);

        final JFreeChart chart = this.createCombinedChart(observed, predicted);
        this.streamOutSvg(chart,sampleName);

    }
}
