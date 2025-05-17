package com.nklido.garminapi.util;

public class PaceConverter {

    private PaceConverter() {}

    private static final double METERS_PER_KILOMETER = 1000.0;

    public static String speedToPace(double speed) {

        if (speed <= 0) {
            return "0:00";
        }

        double totalSeconds = METERS_PER_KILOMETER / speed;

        int totalSecRounded = (int) Math.round(totalSeconds);

        int minutes = totalSecRounded / 60;
        int seconds = totalSecRounded % 60;

        return String.format("%d:%02d", minutes, seconds);
    }

}
