package com.nklido.garminapi.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfile {

    @JsonProperty("id")
    private long id;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("profileImageType")
    private String profileImageType;

    @JsonProperty("profileImageUrl")
    private String profileImageUrl;

    @JsonProperty("location")
    private String location;
}