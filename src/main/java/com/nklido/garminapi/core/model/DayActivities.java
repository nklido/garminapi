package com.nklido.garminapi.core.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DayActivities {

    public static String[] dayNames = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    public String day;

    List<Activity> activities;

    public DayActivities(String day) {
        this.day = day;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
