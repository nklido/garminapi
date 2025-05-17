package com.nklido.garminapi.services;

import com.nklido.garminapi.core.model.Activity;
import com.nklido.garminapi.core.model.DayActivities;
import com.nklido.garminapi.core.model.WeeklyLog;
import com.nklido.garminapi.core.services.GarminService;
import com.nklido.garminapi.util.PaceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TrainingLogService {

    private static final DateTimeFormatter MONTH_DAY_FMT = DateTimeFormatter.ofPattern("MMM d");
    private static final DateTimeFormatter DAY_FMT       = DateTimeFormatter.ofPattern("d");

    @Autowired
    private GarminService garminService;

    @Autowired
    private Clock clock;

    public List<WeeklyLog> getWeeklyLog(String displayName, Integer start, Integer limit) {
        List<WeeklyLog> summaries = new ArrayList<>();
        List<Activity> activities = garminService.getActivities(displayName, 0,30);

        ZonedDateTime currentWeekStart = getCurrentWeekStart();
        ZonedDateTime currentWeekEnd = getCurrentWeekEnd();

        for (int i = start; i < start + limit; i++) {
            ZonedDateTime weekStart = currentWeekStart.minusWeeks(i);
            ZonedDateTime weekEnd = currentWeekEnd.minusWeeks(i);


            List<Activity> activitiesInWeek = activities.stream()
                .filter( activity -> {
                    LocalDateTime t = activity.getStartTimeGMT();
                    return !t.isBefore(weekStart.toLocalDateTime()) && !t.isAfter(weekEnd.toLocalDateTime());
                })
                .toList();

            List<DayActivities> dailyActivities = new ArrayList<>();
            for (String name : DayActivities.dayNames) {
                dailyActivities.add(new DayActivities(name));
            }

            for (Activity activity : activitiesInWeek) {
                DayOfWeek dow = activity.getStartTimeLocal()
                                   .getDayOfWeek();
                int index = dow.getValue() - 1;
                dailyActivities.get(index).addActivity(activity);
            }


            Double totalWeekDuration = activitiesInWeek.stream()
                    .mapToDouble(Activity::getDuration)
                    .sum();

            Double totalDistance = activitiesInWeek.stream()
                    .mapToDouble(Activity::getDistance)
                    .sum();

            WeeklyLog weeklyLog = new WeeklyLog();
            weeklyLog.setName(getWeeklyLogName(weekStart, weekEnd));
            weeklyLog.setStartDate(weekStart.toLocalDateTime());
            weeklyLog.setEndDate(weekEnd.toLocalDateTime());
            weeklyLog.setDailyActivities(dailyActivities);
            weeklyLog.setTotalDistance(totalDistance);
            weeklyLog.setAverageHeartRate(getWeeklyAverageHeartRate(activitiesInWeek, totalWeekDuration));
            weeklyLog.setAveragePace(getWeeklyAveragePace(activitiesInWeek, totalWeekDuration));
            weeklyLog.setZones(getWeeklyZonePercentage(activitiesInWeek, totalWeekDuration));
            summaries.add(weeklyLog);
        }

        return summaries;
    }

    public Double getWeeklyAverageHeartRate(List<Activity> activities, Double totalWeekDuration) {

        if (totalWeekDuration <= 0) {
            return 0.0;
        }

        Double totalWeightedHeartRate = activities.stream()
                .mapToDouble(activity -> activity.getDuration() * activity.getAverageHR())
                .sum();

        return totalWeightedHeartRate / totalWeekDuration;
    }

    public String getWeeklyAveragePace(List<Activity> activities, Double totalWeekDuration) {
        if (totalWeekDuration <= 0) {
            return null;
        }

        Double totalWeightedSpeed = activities.stream()
                .mapToDouble(activity -> activity.getDuration() * activity.getAverageSpeed())
                .sum();

        return PaceConverter.speedToPace(totalWeightedSpeed / totalWeekDuration);
    }

    public List<Double> getWeeklyZonePercentage(List<Activity> activities, Double totalWeekDuration) {

        Double[] zones = new Double[5];
        Arrays.fill(zones, 0.0);

        if (totalWeekDuration <= 0) {
            return Arrays.asList(zones);
        }


        for (Activity activity: activities) {
            zones[0] += activity.getHrTimeInZone1() != null ? activity.getHrTimeInZone1() : 0.0;
            zones[1] += activity.getHrTimeInZone2() != null ? activity.getHrTimeInZone2() : 0.0;
            zones[2] += activity.getHrTimeInZone3() != null ? activity.getHrTimeInZone3() : 0.0;
            zones[3] += activity.getHrTimeInZone4() != null ? activity.getHrTimeInZone4() : 0.0;
            zones[4] += activity.getHrTimeInZone5() != null ? activity.getHrTimeInZone5() : 0.0;
        }

        return Arrays.stream(zones)
                .map(timeInZone -> (double) Math.round((timeInZone / totalWeekDuration) * 100))
                .toList();
    }


    public String getWeeklyLogName(ZonedDateTime weekStart, ZonedDateTime weekEnd) {
            String startStr = MONTH_DAY_FMT.format(weekStart);
            String endStr   = (weekStart.getMonth() == weekEnd.getMonth())
                              ? DAY_FMT.format(weekEnd)
                              : MONTH_DAY_FMT.format(weekEnd);
            return startStr + " - " + endStr + " /" + weekEnd.getYear();
    }

    private ZonedDateTime getCurrentWeekStart() {
        return ZonedDateTime.now(clock)
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .truncatedTo(ChronoUnit.DAYS);
    }

    private ZonedDateTime getCurrentWeekEnd() {
        return ZonedDateTime.now(clock)
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            .with(LocalTime.MAX);
    }
}
