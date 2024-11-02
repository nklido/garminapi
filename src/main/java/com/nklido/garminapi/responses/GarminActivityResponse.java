package com.nklido.garminapi.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GarminActivityResponse {

    @JsonProperty("activityId")
    private long activityId;

    @JsonProperty("activityName")
    private String activityName;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("activityType")
    private ActivityType activityType;

    @Data
    public static class ActivityType {
        @JsonProperty("typeKey")
        private String typeKey;
    }

}
