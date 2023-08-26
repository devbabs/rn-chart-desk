package com.rnchartdesk;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartManager extends SimpleViewManager<PieChart> {

    public static final String REACT_CLASS = "PieChart";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected PieChart createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new PieChart(themedReactContext);
    }

    @ReactProp(name = "description")
    public void setDescription(PieChart pieChart, String descriptionText) {
        Description description = new Description();
        description.setText(descriptionText);
        pieChart.setDescription(description);

        pieChart.invalidate();
    }

    // Receive, parse and set data for grouped pie chart
    @ReactProp(name = "data")
    public void setData(PieChart pieChart, String encodedData) throws Exception {
        // Parse JSON encoded data into array of type PieChardDataObject
        ObjectMapper objectMapper = new ObjectMapper();
        PieChartData[] data = objectMapper.readValue(encodedData, PieChartData[].class);

        // Create data entries
        List<PieEntry> entries = new ArrayList<>();

        for(int i = 0; i < data.length; i++) {
            PieEntry pieEntry = new PieEntry(data[i].getValue(), data[i].getLabel());
            entries.add(pieEntry);
        }

        // Create a data set and customize it
        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // Combine data sets into PieData
        PieData pieData = new PieData(dataSet);

        pieChart.setDrawEntryLabels(false);

        // Assign data to the chart
        pieChart.setData(pieData);

        // Set legend to the top of the chart
        Legend legend = pieChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        // Refresh pieChartView
        pieChart.invalidate();
    }

    @ReactProp(name = "roundValues")
    public void setRoundValues(PieChart pieChart, boolean roundValues) {
        IPieDataSet dataSet = pieChart.getData().getDataSet();

        dataSet.setValueFormatter(new RoundValueFormatter(roundValues));

        pieChart.invalidate();
    }
}