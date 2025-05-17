package com.nklido.garminapi.mapper;

import com.nklido.garminapi.adapter.garmin.dto.UserProfileDto;
import com.nklido.garminapi.core.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public static UserProfile toDomain(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDto.getId());
        userProfile.setDisplayName(userProfileDto.getDisplayName());
        userProfile.setUserName(userProfileDto.getUserName());
        userProfile.setLocation(userProfileDto.getLocation());
        userProfile.setProfileImageUrl(userProfileDto.getProfileImageUrlLarge());
        userProfile.setFullName(userProfileDto.getFullName());
        userProfile.setProfileImageType(userProfileDto.getProfileImageType());
        return userProfile;
    }
}
