package com.nklido.garminapi.controllers;

import com.nklido.garminapi.core.model.WeeklyLog;
import com.nklido.garminapi.security.RequestContext;
import com.nklido.garminapi.services.TrainingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingLogController {

    @Autowired
    private TrainingLogService trainingLogService;

    @Autowired
    private RequestContext requestContext;

    @GetMapping("/training-log")
    public ResponseEntity<List<WeeklyLog>> activities(
        @RequestParam(value = "start", defaultValue = "0") Integer start,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        String displayName = requestContext.getUser().getDisplayName();
        return ResponseEntity.ok(trainingLogService.getWeeklyLog(displayName, start, limit));
    }
}
