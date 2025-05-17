package com.nklido.garminapi.services;

import com.nklido.garminapi.core.model.Activity;
import com.nklido.garminapi.core.model.WeeklyLog;
import com.nklido.garminapi.core.services.GarminService;
import com.nklido.garminapi.fixtures.ActivityDtoFactory;
import com.nklido.garminapi.mapper.ActivityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class GarminServiceTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @MockBean
    private GarminService garminService;

    @Autowired
    private TrainingLogService trainingLogService;


    @TestConfiguration
    static class FixedClockConfig {

        @Bean
        @Primary
        public Clock fixedClock() {
            Instant fixedInstant = LocalDateTime.of(2025,5,3,7,0)
                                                .atZone(ZoneId.of("Europe/Athens"))
                                                .toInstant();
            return Clock.fixed(fixedInstant, ZoneId.of("Europe/Athens"));
        }
    }


    @Test
    void testGetActivities() throws IOException {
        List<Activity> activities = ActivityDtoFactory.getList()
                        .getActivityList().stream().map(ActivityMapper::toDomain)
                        .toList();

        when(garminService.getActivities(eq("example-display-name"), anyInt(), anyInt())).thenReturn(activities);


        List<WeeklyLog> trainingLog = trainingLogService.getWeeklyLog("example-display-name", 0, 5);

        assertThat(trainingLog.size()).isEqualTo(5);

        WeeklyLog weeklyLog = trainingLog.get(0);
        assertThat(weeklyLog.getDailyActivities().size()).isEqualTo(7);

        List<Activity> weeklyActivities = weeklyLog.getDailyActivities().stream().flatMap(s -> s.getActivities().stream()).toList();
        assertThat(weeklyActivities.size()).isEqualTo(1);

        assertThat(weeklyActivities.get(0).getStartTimeLocal()).isBetween(
                weeklyLog.getStartDate(),
                weeklyLog.getEndDate()
        );

        assertThat(weeklyLog.getStartDate().format(DATE_FORMAT)).isEqualTo("2025-04-28 00:00");
        assertThat(weeklyLog.getEndDate().format(DATE_FORMAT)).isEqualTo("2025-05-04 23:59");
    }

}