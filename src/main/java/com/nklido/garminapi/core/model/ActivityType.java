package com.nklido.garminapi.core.model;

import lombok.Data;

@Data
public class ActivityType {

    private long id;

    private String typeKey;

    private long parentId;
}
