package com.kozirase.app;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public class ClaimsXAxisValueFormatter extends ValueFormatter {
    private List<String> values;

    public ClaimsXAxisValueFormatter(List<String> values) {
        this.values = values;
    }

    /**
     * Called when drawing any label, used to change numbers into formatted strings.
     *
     * @param value float to be formatted
     * @return formatted string label
     */

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        if (index < 0 || index >= values.size()) {
            return "";
        }
        if (index <= 15) {
            return "15:00";
        }
        if (index > 15 && index <= 30) {
            return "16:00";
        }
        if(index > 30 && index <=45){
            return "17:00";
        }
        return "3";
    }
}
