package com.nklido.garminapi.models;

public enum ActivityType {
    RUNNING,
    WALKING;


    public static ActivityType fromString(String activity) {
        return ActivityType.valueOf(activity.toUpperCase());
    }

}

