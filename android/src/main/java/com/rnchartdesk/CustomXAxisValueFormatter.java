package com.rnchartdesk;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public class CustomXAxisValueFormatter extends ValueFormatter {
    private final List<String> labels;

    public CustomXAxisValueFormatter(List<String> labels) {
//        Log.d("labellla",  "Got here again: " + labels.toString());
        this.labels = labels;
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        if (index >= 0 && index < labels.size()) {
//            Log.d("labellla",  index + " - " + labels.get(index));
            return labels.get(index);
        }
        return ""; // Return empty string for out-of-bounds indices
    }
}
