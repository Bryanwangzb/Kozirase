package com.kozirase.app;

public class HeartRate {
    // variable name same as in heart rate data
    private String dateTime;
    private Value value;

    public class Value {
        private int bpm;
        private int confidence;

        public Value(int bpm, int confidence) {
            this.bpm = bpm;
            this.confidence = confidence;
        }

        public int getBpm() {
            return bpm;
        }

        public int getConfidence() {
            return confidence;
        }
    }

    public HeartRate(String dateTime, Value value) {
        this.dateTime = dateTime;
        this.value = value;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Value getValue() {
        return value;
    }
}
