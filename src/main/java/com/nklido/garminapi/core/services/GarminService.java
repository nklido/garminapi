package com.nklido.garminapi.core.services;

import com.nklido.garminapi.adapter.garmin.Client;
import com.nklido.garminapi.core.model.Activity;
import com.nklido.garminapi.core.model.ActivityType;
import com.nklido.garminapi.core.model.UserConnection;
import com.nklido.garminapi.core.model.UserProfile;
import com.nklido.garminapi.mapper.ActivityMapper;
import com.nklido.garminapi.mapper.ActivityTypeMapper;
import com.nklido.garminapi.mapper.UserConnectionMapper;
import com.nklido.garminapi.mapper.UserProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarminService {

    private static final Logger logger = LoggerFactory.getLogger(GarminService.class);

    @Autowired
    private Client garminClient;

    public List<Activity> getActivities(String displayName, Integer start, Integer limit) {
        return garminClient.getActivities(displayName, start, limit)
                .getActivityList()
                .stream()
                .map(ActivityMapper::toDomain)
                .toList();
    }

    public List<ActivityType> getActivityTypes() {
        return garminClient.getActivityTypes().stream().map(ActivityTypeMapper::toDomain).toList();
    }

    public UserProfile getUser(String displayName) {
        return UserProfileMapper.toDomain(garminClient.getUser(displayName));
    }

    public List<UserConnection> getUserConnections(String displayName) {
        return garminClient.getUserConnections(displayName).getUserConnections()
                .stream()
                .map(UserConnectionMapper::toDomain)
                .toList();
    }
}
