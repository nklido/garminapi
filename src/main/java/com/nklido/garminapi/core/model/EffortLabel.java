package com.nklido.garminapi.core.model;

import java.util.Arrays;
import java.util.List;

public class EffortLabel {

    public final static String RECOVERY = "recovery";
    public final static String EASY = "easy";
    public final static String STEADY = "steady";
    public final static String THRESHOLD = "threshold";
    public final static String LONG = "long";
    public final static String TEMPO = "tempo";
    public final static String FAST = "fast";
    public final static String RACE = "race";
    public final static String NONE = "";


    public static String get(Activity activity) {

        // race
        if (anyContains(List.of("race"), activity.getName())) {
            return RACE;
        }

        // threshold
        if (anyContains(Arrays.asList("threshold", "progressive"), activity.getName())) {
            return THRESHOLD;
        }

        // long or more than 70'
        if (anyContains(List.of("long"), activity.getName()) || activity.getDuration() >= 4200) {
            return LONG;
        }

        // tempo
        if (anyContains(List.of("tempo", "marathon pace", "hm pace"), activity.getName())) {
            return TEMPO;
        }

        // reps
        if (anyContains(List.of("fast", "reps", "speed", "200s"), activity.getName())) {
            return TEMPO;
        }

        // steady
        if (anyContains(List.of("steady", "mid", "medium"), activity.getName())) {
            return STEADY;
        }

        // easy
        if (anyContains(List.of("easy", "base"), activity.getName())) {
            return EASY;
        }

        // recovery
        if (anyContains(List.of("recovery", "wu", "cd", "w.u.", "c.d.", "warm up", "cool down"), activity.getName())) {
            return RECOVERY;
        }

        String trainingLabelEffect = activity.getTrainingEffectLabel();
        if (trainingLabelEffect == null)
            return NONE;

        return switch (trainingLabelEffect) {
            case "AEROBIC_BASE"       -> EffortLabel.EASY;
            case "VO2MAX"             -> EffortLabel.RACE;
            case "TEMPO"              -> EffortLabel.TEMPO;
            case "RECOVERY"           -> EffortLabel.RECOVERY;
            case "LACTATE_THRESHOLD"  -> EffortLabel.THRESHOLD;
            case "ANAEROBIC_CAPACITY" -> EffortLabel.FAST;
            default                   -> EffortLabel.NONE;
        };
    }

    private static Boolean anyContains(List<String> strings, String needle) {
        return strings
                .stream()
                .anyMatch(s -> needle.toLowerCase().contains(s));
    }
}
