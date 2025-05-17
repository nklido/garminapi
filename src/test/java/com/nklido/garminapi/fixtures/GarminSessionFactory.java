package com.nklido.garminapi.fixtures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nklido.garminapi.security.GarminSession;

public class GarminSessionFactory {
    public static GarminSession getValidSession() {
        GarminSession session = new GarminSession();
        GarminSession.User user = new GarminSession.User();
        user.setId(1234);
        user.setDisplayName("example-display-name");
        user.setFullName("John Doe");
        user.setUserName("john.doe@example.com");
        session.setToken(new GarminSession.Token("ZXhhbXBsZS1iZWFyZXItdG9rZW4="));
        session.setUser(user);
        return session;
    }

    public static String toJson(GarminSession session) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(session);
        } catch (JsonProcessingException e) {
            return "";
        }
        return json;
    }
}
