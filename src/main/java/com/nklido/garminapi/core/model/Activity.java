package com.nklido.garminapi.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Activity {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("startTimeLocal")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeLocal;

    @JsonProperty("startTimeGMT")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeGMT;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("averageSpeed")
    private Double averageSpeed;

    @JsonProperty("averagePace")
    private String averagePace;

    @JsonProperty("activityType")
    private ActivityType activityType;

    @JsonProperty("averageHR")
    private Double averageHR;

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

    @JsonProperty("effortLabel")
    private String effortLabel;

    @JsonProperty("activityTrainingLoad")
    private Double activityTrainingLoad;

    @JsonProperty("averageRunningCadenceInStepsPerMinute")
    private Double averageRunningCadenceInStepsPerMinute;

    @JsonProperty("maxRunningCadenceInStepsPerMinute")
    private Double maxRunningCadenceInStepsPerMinute;

    @JsonProperty("averageStrideLength")
    private Double averageStrideLength;

    @JsonProperty("steps")
    private Integer steps;

    @JsonProperty("isPersonalBest")
    private Boolean isPersonalBest;
}
