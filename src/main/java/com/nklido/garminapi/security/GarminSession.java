package com.nklido.garminapi.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GarminSession {

    @JsonProperty("user")
    private User user;

    @JsonProperty("token")
    private Token token;

    @Data
    public static class User {
        @JsonProperty("id")
        private long id;

        @JsonProperty("user_name")
        private String userName;

        @JsonProperty("full_name")
        private String fullName;

        @JsonProperty("display_name")
        private String displayName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Token {
        @JsonProperty("access_token")
        private String accessToken;
    }
}