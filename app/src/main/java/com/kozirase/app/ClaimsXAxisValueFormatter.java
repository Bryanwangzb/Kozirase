package com.kozirase.app;

import android.widget.Toast;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

import static java.lang.Math.round;

public class ClaimsXAxisValueFormatter extends ValueFormatter {
    private final List<String> values;
    private List<Integer> xAxisTimeList;

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

        // Todo: zoom in problem need updating.
        int index = (int) value;

        int timeStep = Math.round(((float) values.size()) / 24);

        int xAxisInitialTime = 0;
        if (index < 0 || index >= values.size()) {
            return "";
        } else if (index >= 0 && index < timeStep) {
            return String.valueOf(xAxisInitialTime); // 15
        } else if (index >= timeStep && index < timeStep * 2) {
            return String.valueOf(xAxisInitialTime + 1); // 16
        } else if (index >= timeStep * 2 && index < timeStep * 3) {
            return String.valueOf(xAxisInitialTime + 2);
        } else if (index >= timeStep * 3 && index < timeStep * 4) {
            return String.valueOf(xAxisInitialTime + 3);
        } else if (index >= timeStep * 4 && index < timeStep * 5) {
            return String.valueOf(xAxisInitialTime + 4);
        } else if (index >= timeStep * 5 && index < timeStep * 6) {
            return String.valueOf(xAxisInitialTime + 5);
        } else if (index >= timeStep * 6 && index < timeStep * 7) {
            return String.valueOf(xAxisInitialTime + 6);
        } else if (index >= timeStep * 7 && index < timeStep * 8) {
            return String.valueOf(xAxisInitialTime + 7);
        } else if (index >= timeStep * 8 && index < timeStep * 9) {
            return String.valueOf(xAxisInitialTime + 8);
        } else if (index >= timeStep * 9 && index < timeStep * 10) {
            return String.valueOf(xAxisInitialTime + 9 - MathConstants.TIME_BIAS); //24
        } else if (index >= timeStep * 10 && index < timeStep * 11) {
            return String.valueOf(xAxisInitialTime + 10 - MathConstants.TIME_BIAS); //1
        } else if (index >= timeStep * 11 && index < timeStep * 12) {
            return String.valueOf(xAxisInitialTime + 11 - MathConstants.TIME_BIAS); //2
        } else if (index >= timeStep * 12 && index < timeStep * 13) {
            return String.valueOf(xAxisInitialTime + 12 - MathConstants.TIME_BIAS); //3
        } else if (index >= timeStep * 13 && index < timeStep * 14) {
            return String.valueOf(xAxisInitialTime + 13 - MathConstants.TIME_BIAS); //4
        } else if (index >= timeStep * 14 && index < timeStep * 15) {
            return String.valueOf(xAxisInitialTime + 14 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 15 && index < timeStep * 16) {
            return String.valueOf(xAxisInitialTime + 15 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 16 && index < timeStep * 17) {
            return String.valueOf(xAxisInitialTime + 16 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 17 && index < timeStep * 18) {
            return String.valueOf(xAxisInitialTime + 17 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 18 && index < timeStep * 19) {
            return String.valueOf(xAxisInitialTime + 18 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 19 && index < timeStep * 20) {
            return String.valueOf(xAxisInitialTime + 19 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 20 && index < timeStep * 21) {
            return String.valueOf(xAxisInitialTime + 20 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 21 && index < timeStep * 22) {
            return String.valueOf(xAxisInitialTime + 21 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 22 && index < timeStep * 23) {
            return String.valueOf(xAxisInitialTime + 22 - MathConstants.TIME_BIAS);
        } else if (index >= timeStep * 23 && index < timeStep * 24) {
            return String.valueOf(xAxisInitialTime + 23 - MathConstants.TIME_BIAS);
        }
        return String.valueOf(xAxisInitialTime);
    }

}
