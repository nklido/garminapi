package com.nklido.garminapi.adapter.garmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ActivityTypeDto {

    @JsonProperty("typeId")
    private long typeId;

    @JsonProperty("typeKey")
    private String typeKey;

    @JsonProperty("parentTypeId")
    private long parentTypeId;

    @JsonProperty("isHidden")
    private Boolean isHidden;

    @JsonProperty("restricted")
    private Boolean restricted;

    @JsonProperty("trimmable")
    private Boolean trimmable;
}
