package com.nklido.garminapi.adapter.garmin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityDto {
    @JsonProperty("activityId")
    private long activityId;

    @JsonProperty("activityName")
    private String activityName;

    @JsonProperty("startTimeLocal")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeLocal;

    @JsonProperty("startTimeGMT")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeGMT;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("elapsedDuration")
    private Double elapsedDuration;

    @JsonProperty("movingDuration")
    private Double movingDuration;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("averageSpeed")
    private Double averageSpeed;

    @JsonProperty("activityType")
    private ActivityTypeDto activityType;

    @JsonProperty("averageHR")
    private Double averageHR;

    @JsonProperty("maxHR")
    private Double maxHR;

    @JsonProperty("hrTimeInZone_1")
    private Double hrTimeInZone1;

    @JsonProperty("hrTimeInZone_2")
    private Double hrTimeInZone2;

    @JsonProperty("hrTimeInZone_3")
    private Double hrTimeInZone3;

    @JsonProperty("hrTimeInZone_4")
    private Double hrTimeInZone4;

    @JsonProperty("hrTimeInZone_5")
    private Double hrTimeInZone5;

    @JsonProperty("aerobicTrainingEffect")
    private Double aerobicTrainingEffect;

    @JsonProperty("anaerobicTrainingEffect")
    private Double anaerobicTrainingEffect;

    @JsonProperty("trainingEffectLabel")
    private String trainingEffectLabel;

    @JsonProperty("activityTrainingLoad")
    private Double activityTrainingLoad;

    @JsonProperty("averageRunningCadenceInStepsPerMinute")
    private Double averageRunningCadenceInStepsPerMinute;

    @JsonProperty("maxRunningCadenceInStepsPerMinute")
    private Double maxRunningCadenceInStepsPerMinute;

    @JsonProperty("avgStrideLength")
    private Double avgStrideLength;

    @JsonProperty("steps")
    private Integer steps;

    @JsonProperty("pr")
    private Boolean pr;
}
