package com.rnchartdesk;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineChartManager extends SimpleViewManager<LineChart> {

    public static final String REACT_CLASS = "LineChart";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected LineChart createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new LineChart(themedReactContext);
    }

    @ReactProp(name = "description")
    public void setDescription(LineChart lineChart, String descriptionText) {
        Description description = new Description();
        description.setText(descriptionText);
        lineChart.setDescription(description);

        lineChart.invalidate();
    }

    // Receive, parse and set data for grouped line chart
    @ReactProp(name = "data")
    public void setData(LineChart lineChart, String encodedData) throws Exception {
        // Parse JSON encoded data into array of type LineChardDataObject
        ObjectMapper objectMapper = new ObjectMapper();
        LineChartData data = objectMapper.readValue(encodedData, LineChartData.class);

        // Loop through data
        List<Entry> dataValuesList = new ArrayList<>();

        for(int i = 0; i < data.getValues().length; i++) {
            dataValuesList.add(new Entry(i, data.getValues()[i]));
        }

        // Get Label
        LineDataSet lineDataSet = new LineDataSet(dataValuesList, null);
        String dataSetColor = data.getColor();
        String dataSetLabel = data.getLabel();

        if (dataSetLabel == null) {
            dataSetLabel = "Dataset";
        }
        if (dataSetColor == null) {
            dataSetColor = "#000000";
        }

        lineDataSet.setLabel(dataSetLabel);
        lineDataSet.setColor(Color.parseColor(dataSetColor));
        lineDataSet.setCircleHoleColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.BLACK);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

//        lineData.setValueFormatter();

        // Add data to lineChart
        lineChart.setData(lineData);

        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);

        // Set legend to the top of the chart
        Legend legend = lineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        // Get the XAxis and set its position to the bottom
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Get the right-side YAxis and disable labels
        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setDrawLabels(false);

        // Refresh lineChartView
        lineChart.invalidate();
    }

    @ReactProp(name = "xAxisLabels")
    public void setXAxisLabels(LineChart lineChart, String labels) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] labelsArray = objectMapper.readValue(labels, String[].class);

        // Get the XAxis and set custom labels using a ValueFormatter
        XAxis xAxis = lineChart.getXAxis();

        // Loop through labels to create customLabels array
        List<String> xAxisLabels = new ArrayList<>(Arrays.asList(labelsArray));

        xAxis.setValueFormatter(new CustomXAxisValueFormatter(xAxisLabels));

        xAxis.setGranularity(1f);

        xAxis.setAxisMinimum(-.4f);

        lineChart.invalidate();
    }

    @ReactProp(name = "roundValues")
    public void setRoundValues(LineChart lineChart, boolean roundValues) {
        XAxis xAxis = lineChart.getXAxis();

        List<ILineDataSet> dataSets = lineChart.getLineData().getDataSets();

        for (ILineDataSet dataSet: dataSets) {
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

        lineChart.invalidate();
    }
}