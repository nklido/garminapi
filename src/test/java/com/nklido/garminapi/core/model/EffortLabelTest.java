package com.nklido.garminapi.core.model;

import com.nklido.garminapi.fixtures.ActivityDtoFactory;
import com.nklido.garminapi.mapper.ActivityMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

class EffortLabelTest {


    @ParameterizedTest(name = "{index} ⇒ {1}")
    @MethodSource("activityProvider")
    void testGetEffortLabel(Activity activity, String expected) {
        assertEquals(expected, EffortLabel.get(activity));
    }


    private static Stream<Arguments> activityProvider() throws IOException {
        return Stream.of(
            of(mockActivity("race run",        2700, null), EffortLabel.RACE),
            of(mockActivity("threshold run",   100,  null), EffortLabel.THRESHOLD),
            of(mockActivity("long easy run",   5000, null), EffortLabel.LONG),
            of(mockActivity("long run",        100,  null), EffortLabel.LONG),
            of(mockActivity("tempo blast",     100,  null), EffortLabel.TEMPO),
            of(mockActivity("speed reps",      100,  null), EffortLabel.TEMPO),
            of(mockActivity("mid pace",        100,  null), EffortLabel.STEADY),
            of(mockActivity("base miles",      100,  null), EffortLabel.EASY),
            of(mockActivity("cool down",       100,  null), EffortLabel.RECOVERY),

            of(mockActivity("random jog",      100, "AEROBIC_BASE"),       EffortLabel.EASY),
            of(mockActivity("random jog",      100, "VO2MAX"),             EffortLabel.RACE),
            of(mockActivity("random jog",      100, "TEMPO"),              EffortLabel.TEMPO),
            of(mockActivity("random jog",      100, "RECOVERY"),           EffortLabel.RECOVERY),
            of(mockActivity("random jog",      100, "ANAEROBIC_CAPACITY"), EffortLabel.FAST),
            of(mockActivity("random jog",      100, "LACTATE_THRESHOLD"),  EffortLabel.THRESHOLD),

            of(mockActivity("nothing here",100, null),                  EffortLabel.NONE),
            of(mockActivity("nothing here",100, "UNKNOWN_LABEL"),       EffortLabel.NONE)
        );
    }

    private static Activity mockActivity(String name, double duration, String trainingEffectLabel) throws IOException {
        Activity activity = ActivityDtoFactory.getList().getActivityList()
                .stream()
                .map(ActivityMapper::toDomain)
                .toList()
                .get(0);

        activity.setName(name);
        activity.setDuration(duration);
        activity.setTrainingEffectLabel(trainingEffectLabel);
        return activity;
    }

}

