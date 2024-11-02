package com.nklido.garminapi.converters;

import com.nklido.garminapi.responses.ActivityResponse;
import com.nklido.garminapi.responses.GarminActivitiesResponse;
import com.nklido.garminapi.responses.GarminActivityResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GarminActivityResponseConverter {

    public List<ActivityResponse> convert(GarminActivitiesResponse garminActivitiesResponse) {

        List<ActivityResponse> activities = new ArrayList<>();

        if (garminActivitiesResponse == null) {
            return activities;
        }

        for (GarminActivityResponse garminActivityResponse: garminActivitiesResponse.getActivityList()) {
            ActivityResponse activityResponse = new ActivityResponse();

            activityResponse.setId(garminActivityResponse.getActivityId());
            activityResponse.setName(garminActivityResponse.getActivityName());
            activityResponse.setDuration(garminActivityResponse.getDuration());
            activityResponse.setDistance(garminActivityResponse.getDistance());
            activityResponse.setType(garminActivityResponse.getActivityType().getTypeKey());

            activities.add(activityResponse);
        }

        return activities;
    }
}
