package com.nklido.garminapi.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserConnection {

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("profileImageUrl")
    private String profileImageUrl;

    @JsonProperty("userLevel")
    private Integer userLevel;
}

