package com.nklido.garminapi.fixtures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nklido.garminapi.adapter.garmin.dto.ActivityDto;
import com.nklido.garminapi.adapter.garmin.dto.ActivityResponseDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ActivityDtoFactory {


    public static ActivityResponseDto getList() throws IOException {
        String activities = new String(Files.readAllBytes(Paths.get("src/test/resources/activities.json")));
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .readValue(activities, ActivityResponseDto.class);
    }

    public static String toJson(ActivityDto activityDto) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(activityDto);
        } catch (JsonProcessingException e) {
            return "";
        }
        return json;
    }
}
