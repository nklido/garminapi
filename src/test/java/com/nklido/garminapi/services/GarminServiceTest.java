package com.nklido.garminapi.services;

import com.nklido.garminapi.models.ActivityType;
import com.nklido.garminapi.responses.ActivityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockMvc
class GarminServiceTest {

    @Autowired
    private GarminService garminService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(new RestTemplate());
    }

    @Test
    void getActivitiesTest() throws IOException {

        String mockResponseJson = new String(Files.readAllBytes(Paths.get("src/test/resources/activities.json")));

        mockServer.expect(requestTo(matchesPattern("https://connect.garmin.com/activitylist-service/activities.*")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockResponseJson, MediaType.APPLICATION_JSON));

        List<ActivityResponse> activities = garminService.getActivities();

        assertEquals("Dortmund - Cool down", activities.get(0).getName());
        assertEquals(ActivityType.RUNNING, activities.get(0).getType());
    }
}