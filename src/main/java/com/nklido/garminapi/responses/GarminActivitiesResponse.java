package com.nklido.garminapi.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GarminActivitiesResponse {

    @JsonProperty("activityList")
    private List<GarminActivityResponse> activityList = new ArrayList<>();

    public List<GarminActivityResponse> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<GarminActivityResponse> activityList) {
        this.activityList = activityList;
    }
}
