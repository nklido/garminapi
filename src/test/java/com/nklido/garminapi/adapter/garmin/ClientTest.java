package com.nklido.garminapi.adapter.garmin;

import com.nklido.garminapi.adapter.garmin.dto.ActivityDto;
import com.nklido.garminapi.adapter.garmin.dto.ActivityTypeDto;
import com.nklido.garminapi.adapter.garmin.dto.UserConnectionDto;
import com.nklido.garminapi.adapter.garmin.dto.UserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockMvc
class ClientTest {


    @Autowired
    @Qualifier("garminRestTemplate")
    private RestTemplate garminRestTemplate;

    @Autowired
    private Client client;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.bindTo(garminRestTemplate).build();
    }

    @Test
    void getActivitiesTest() throws IOException {
        setUpMockServer("activitylist-service/activities.*", "activities.json");
        List<ActivityDto> activities = client.getActivities("example-display-name",0,10).getActivityList();
        assertThat(activities.size()).isEqualTo(1);
        ActivityDto activityDto = activities.get(0);
        assertThat(activityDto.getActivityId()).isEqualTo(57902022152L);
        assertThat(activityDto.getActivityName()).isEqualTo("Athens Running");
        assertThat(activityDto.getStartTimeLocal()).isEqualTo(LocalDateTime.of(2025,5,3,6,22,28));
        assertThat(activityDto.getStartTimeGMT()).isEqualTo(LocalDateTime.of(2025,5,3,4,22,28));
        mockServer.verify();
    }


    @Test
    void getActivityTypesTest() throws IOException {
        setUpMockServer("activity-service/activity/activityTypes","activity-types.json");
        List<ActivityTypeDto> activityTypes = client.getActivityTypes();
        assertThat(activityTypes.size()).isEqualTo(2);
        ActivityTypeDto activityTypeDto = activityTypes.get(0);
        assertThat(activityTypeDto.getTypeKey()).isEqualTo("running");
        mockServer.verify();
    }


    @Test
    void getUserConnections() throws IOException {
        setUpMockServer("connection-service/connection/connections.*","user-connections.json");
        List<UserConnectionDto> userConnectionDtos = client.getUserConnections("example-display-name")
                .getUserConnections();

        assertThat(userConnectionDtos.size()).isEqualTo(2);
        UserConnectionDto userConnectionDto = userConnectionDtos.get(0);
        assertThat(userConnectionDto.getFullName()).isEqualTo("John Doe");
        mockServer.verify();
    }


    @Test
    void getUserProfile() throws IOException {
        setUpMockServer("userprofile-service/socialProfile.*","user-profile.json");
        UserProfileDto userProfileDto = client.getUser("example-display-name");

        assertThat(userProfileDto).isNotNull();
        assertThat(userProfileDto.getId()).isEqualTo(5555555);
        assertThat(userProfileDto.getProfileId()).isEqualTo(454545454);
        assertThat(userProfileDto.getGarminGuid()).isEqualTo("1c906c81-1dd1-4df8-be26-6ab0985373e4");
        assertThat(userProfileDto.getDisplayName()).isEqualTo("82e7c2d9-449e-44fb-8087-c571a5678f95");
        assertThat(userProfileDto.getFullName()).isEqualTo("John doe");
        assertThat(userProfileDto.getUserName()).isEqualTo("****");
        assertThat(userProfileDto.getLocation()).isEqualTo("Athens");
        mockServer.verify();
    }


    private void setUpMockServer(String pattern, String responsePath) throws IOException {

        String mockResponseJson = new String(Files.readAllBytes(Paths.get("src/test/resources/" + responsePath)));

        mockServer.expect(requestTo(matchesPattern("https://connect\\.garmin\\.com/" + pattern)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockResponseJson, MediaType.APPLICATION_JSON));
    }
}