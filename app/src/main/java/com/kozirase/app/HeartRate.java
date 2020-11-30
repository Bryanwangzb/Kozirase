package com.kozirase.app;

public class HeartRate {
    // variable name same as in heart rate data
    private String dateTime;
    private Value value;

    public class Value {
        private int bpm;
        private int confidence;
    }

}
