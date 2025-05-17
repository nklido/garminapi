package com.nklido.garminapi.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WeeklyLog {

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private List<DayActivities> dailyActivities;

    private Double totalDistance;

    private String totalTime;

    private Double averageHeartRate;

    private String averagePace;

    private List<Double> zones;
}
