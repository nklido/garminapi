package com.nklido.garminapi.adapter.garmin.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTypeDtoTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void test_deserialization() throws JsonProcessingException {
        String json = """
                {
                  "typeId": 1,
                  "typeKey": "running",
                  "parentTypeId": 17,
                  "isHidden": false,
                  "restricted": false,
                  "trimmable": true
                }
                """;

        ActivityTypeDto dto = mapper.readValue(json, ActivityTypeDto.class);

        assertEquals(1, dto.getTypeId());
        assertEquals("running", dto.getTypeKey());
        assertEquals(17, dto.getParentTypeId());
        assertEquals(false, dto.getIsHidden());
        assertEquals(false, dto.getRestricted());
        assertEquals(true, dto.getTrimmable());
    }
}