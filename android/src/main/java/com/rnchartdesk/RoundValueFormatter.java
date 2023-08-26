package com.rnchartdesk;

import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class RoundValueFormatter extends ValueFormatter {
    boolean roundValue;

    public RoundValueFormatter(boolean roundValue) {
        this.roundValue = roundValue;
    }

    @Override
    public String getFormattedValue(float value) {
        if (this.roundValue) {
            // Format the value without decimals
            return String.valueOf((int) value);
        } else {
            // Format the value without decimals
            return String.valueOf(value);
        }
    }
}
