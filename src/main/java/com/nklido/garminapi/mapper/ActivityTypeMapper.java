package com.nklido.garminapi.mapper;

import com.nklido.garminapi.adapter.garmin.dto.ActivityTypeDto;
import com.nklido.garminapi.core.model.ActivityType;
import org.springframework.stereotype.Component;

@Component
public class ActivityTypeMapper {

    public static ActivityType toDomain(ActivityTypeDto activityTypeDto) {
        ActivityType activityType = new ActivityType();
        activityType.setId(activityTypeDto.getTypeId());
        activityType.setParentId(activityTypeDto.getParentTypeId());
        activityType.setTypeKey(activityTypeDto.getTypeKey());
        return activityType;
    }
}
