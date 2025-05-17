package com.nklido.garminapi.adapter.garmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActivityResponseDto {

    @JsonProperty("activityList")
    private List<ActivityDto> activityList = new ArrayList<>();
}
