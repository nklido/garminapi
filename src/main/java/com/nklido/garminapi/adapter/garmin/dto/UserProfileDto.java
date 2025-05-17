package com.nklido.garminapi.adapter.garmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("profileId")
    private long profileId;

    @JsonProperty("garminGUID")
    private String garminGuid;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("profileImageType")
    private String profileImageType;

    @JsonProperty("profileImageUrlLarge")
    private String profileImageUrlLarge;

    @JsonProperty("hasPremiumSocialIcon")
    private boolean hasPremiumSocialIcon;

    @JsonProperty("location")
    private String location;
}
