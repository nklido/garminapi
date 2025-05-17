package com.nklido.garminapi.mapper;

import com.nklido.garminapi.adapter.garmin.dto.ActivityDto;
import com.nklido.garminapi.core.model.Activity;
import com.nklido.garminapi.core.model.EffortLabel;
import com.nklido.garminapi.util.PaceConverter;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    public static Activity toDomain(ActivityDto activityDto) {
        Activity activity = new Activity();

        activity.setId(activityDto.getActivityId());
        activity.setName(activityDto.getActivityName());
        activity.setStartTimeGMT(activityDto.getStartTimeGMT());
        activity.setStartTimeLocal(activityDto.getStartTimeLocal());
        activity.setDuration(activityDto.getDuration());
        activity.setDistance(activityDto.getDistance());
        activity.setAverageSpeed(activityDto.getAverageSpeed());
        activity.setActivityType(ActivityTypeMapper.toDomain(activityDto.getActivityType()));
        activity.setAverageHR(activityDto.getAverageHR());

        activity.setHrTimeInZone1(activityDto.getHrTimeInZone1());
        activity.setHrTimeInZone2(activityDto.getHrTimeInZone2());
        activity.setHrTimeInZone3(activityDto.getHrTimeInZone3());
        activity.setHrTimeInZone4(activityDto.getHrTimeInZone4());
        activity.setHrTimeInZone5(activityDto.getHrTimeInZone5());

        activity.setAerobicTrainingEffect(activityDto.getAerobicTrainingEffect());
        activity.setAnaerobicTrainingEffect(activityDto.getAnaerobicTrainingEffect());
        activity.setTrainingEffectLabel(activityDto.getTrainingEffectLabel());
        activity.setActivityTrainingLoad(activityDto.getActivityTrainingLoad());

        activity.setAverageRunningCadenceInStepsPerMinute(
                activityDto.getAverageRunningCadenceInStepsPerMinute()
        );

        activity.setMaxRunningCadenceInStepsPerMinute(
                activityDto.getMaxRunningCadenceInStepsPerMinute()
        );

        activity.setAverageStrideLength(activityDto.getAvgStrideLength());
        activity.setSteps(activityDto.getSteps());
        activity.setIsPersonalBest(activityDto.getPr());

        setComputedProperties(activity);

        return activity;
    }

    private static void setComputedProperties(Activity activity) {
        activity.setEffortLabel(EffortLabel.get(activity));
        activity.setAveragePace(PaceConverter.speedToPace(activity.getAverageSpeed()));
    }
}
