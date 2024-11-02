package com.nklido.garminapi.responses;

import com.nklido.garminapi.models.ActivityType;
import lombok.Data;

@Data
public class ActivityResponse {

    private long id;

    private String name;

    /**
     * Activity dDuration in meters per second
     */
    private double duration;

    /**
     * Activity distance in meters
     */
    private double distance;

    private ActivityType type;

    public void setType(String type){
        this.type = ActivityType.fromString(type);
    }

}