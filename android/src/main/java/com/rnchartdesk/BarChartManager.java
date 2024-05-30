package com.rnchartdesk;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarChartManager extends SimpleViewManager<BarChart> {

    public static final String REACT_CLASS = "BarChart";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected BarChart createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new BarChart(themedReactContext);
    }

    @ReactProp(name = "description")
    public void setDescription(BarChart barChart, String descriptionText) {
        Description description = new Description();
        description.setText(descriptionText);
        barChart.setDescription(description);

        barChart.invalidate();
    }

    // Receive, parse and set data for grouped bar chart
    @ReactProp(name = "data")
    public void setData(BarChart barChart, String encodedData) throws Exception {
        // Parse JSON encoded data into array of type BarChardDataObject
        ObjectMapper objectMapper = new ObjectMapper();
        BarChartData data = objectMapper.readValue(encodedData, BarChartData.class);

        // Loop through data
        List<BarEntry> dataValuesList = new ArrayList<>();

        for(int i = 0; i < data.getValues().length; i++) {
            dataValuesList.add(new BarEntry(i, data.getValues()[i]));
        }

        // Get Label
        BarDataSet barDataSet = new BarDataSet(dataValuesList, null);
        String dataSetColor = data.getColor();
        String dataSetLabel = data.getLabel();

        if (dataSetLabel != null) {
//            dataSetLabel = "Dataset";
            barDataSet.setLabel(dataSetLabel);
        }
        if (dataSetColor == null) {
            dataSetColor = "#000000";
        }

        barDataSet.setColor(Color.parseColor(dataSetColor));

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        barData.setBarWidth(.2f);

        // Add data to barChart
        barChart.setData(barData);

        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);

        // Set legend to the top of the chart
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        // Get the XAxis and set its position to the bottom
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Get the right-side YAxis and disable labels
        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setDrawLabels(false);

        // Refresh barChartView
        barChart.invalidate();
    }

    @ReactProp(name = "xAxisLabels")
    public void setXAxisLabels(BarChart barChart, String labels) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] labelsArray = objectMapper.readValue(labels, String[].class);

        // Get the XAxis and set custom labels using a ValueFormatter
        XAxis xAxis = barChart.getXAxis();

        // Loop through labels to create customLabels array
        List<String> xAxisLabels = new ArrayList<>(Arrays.asList(labelsArray));

        // Custom X-axis labels
        barChart.getXAxis().setValueFormatter(new CustomXAxisValueFormatter(xAxisLabels));

        xAxis.setGranularity(1f);

        xAxis.setAxisMinimum(-.400f);

        barChart.invalidate();
    }

    @ReactProp(name = "roundValues")
    public void setRoundValues(BarChart barChart, boolean roundValues) {
        XAxis xAxis = barChart.getXAxis();

        List<IBarDataSet> dataSets = barChart.getBarData().getDataSets();

        for (IBarDataSet dataSet: dataSets) {
            dataSet.setValueFormatter(new RoundValueFormatter(roundValues));
        }

        if (roundValues) {
            // Set custom value formatter to remove decimal places
            xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                  return String.valueOf((int) value);
                }
            });
        }

        barChart.invalidate();
    }
}
