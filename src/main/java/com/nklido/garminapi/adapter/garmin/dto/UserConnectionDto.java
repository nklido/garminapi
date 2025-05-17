package com.nklido.garminapi.adapter.garmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserConnectionDto {

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("profileImageUrlLarge")
    private String profileImageUrlLarge;

    @JsonProperty("userLevel")
    private Integer userLevel;
}