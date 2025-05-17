package com.nklido.garminapi.mapper;

import com.nklido.garminapi.adapter.garmin.dto.UserConnectionDto;
import com.nklido.garminapi.core.model.UserConnection;
import org.springframework.stereotype.Component;

@Component
public class UserConnectionMapper {

    public static UserConnection toDomain(UserConnectionDto userConnectionDto) {
        UserConnection userConnection = new UserConnection();
        userConnection.setUserId(userConnectionDto.getUserId());
        userConnection.setDisplayName(userConnectionDto.getDisplayName());
        userConnection.setFullName(userConnectionDto.getFullName());
        userConnection.setLocation(userConnectionDto.getLocation());
        userConnection.setProfileImageUrl(userConnectionDto.getProfileImageUrlLarge());
        userConnection.setUserLevel(userConnectionDto.getUserLevel());
        return userConnection;
    }
}