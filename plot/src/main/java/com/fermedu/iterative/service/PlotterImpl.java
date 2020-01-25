package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private PlottingDataProvider dataProvider;

    private JFreeChart createLineChartFromDataset(XYSeriesCollection dataset,String title) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title, // chart title
                "time (min)", // x axis label
                "OD", // y axis label
                dataset,
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );

        return chart;

    }

    private JFreeChart createScatterChartFromDataset(XYSeriesCollection dataset,String title) {
        final JFreeChart chart = ChartFactory.createScatterPlot(
                title, // chart title
                "time (min)", // x axis label
                "OD", // y axis label
                dataset,
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );

        return chart;

    }

    private List<JFreeChart> createLineChart(SampleData observed, SampleData predicted) {

        XYSeries observedSeries = new XYSeries(observed.getYName().concat(" observed"));
        observedSeries.setDescription(observed.getYName().concat(" observed"));
        XYSeries predictedSeries = new XYSeries(predicted.getYName().concat(" predicted"));
        predictedSeries.setDescription(predicted.getYName().concat(" predicted"));
        for (int index = 0; index < observed.getXValueList().size(); index++) {
            double xObserved = observed.getXValueList().get(index).doubleValue();
            double xPredicted = predicted.getXValueList().get(index).doubleValue();
            double yObserved = observed.getYValueList().get(index).doubleValue();
            double yPredicted = predicted.getYValueList().get(index).doubleValue();

            observedSeries.add(xObserved, yObserved);
            predictedSeries.add(xPredicted, yPredicted);
        }

        XYSeriesCollection observedDataset = new XYSeriesCollection();
        observedDataset.addSeries(observedSeries);


        XYSeriesCollection predictedDataset = new XYSeriesCollection();
        predictedDataset.addSeries(predictedSeries);
        // todo

        return chart;
    }

    @Override
    public void plotBothObservedPredicted(String sampleName) {
        final SampleData observed = dataProvider.getObservedData(sampleName);
        final SampleData predicted = dataProvider.getPredictedData(sampleName);

//todo
        JFreeChart chart

    }
}
