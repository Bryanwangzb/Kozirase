package com.kozirase.app;

public class HeartRate {
    // variable name same as in heart rate data
    private String dateTime;
    private Value value;

    public static class Value {
        private int bpm;
        private int confidence;

        public Value(int bpm, int confidence) {
            this.bpm = bpm;
            this.confidence = confidence;
        }
    }

    public HeartRate(String dateTime, Value value) {
        this.dateTime = dateTime;
        this.value = value;
    }
}
