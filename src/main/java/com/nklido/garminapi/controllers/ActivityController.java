package com.nklido.garminapi.controllers;

import com.nklido.garminapi.core.model.Activity;
import com.nklido.garminapi.core.model.ActivityType;
import com.nklido.garminapi.core.services.GarminService;
import com.nklido.garminapi.security.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {

    private final GarminService garminService;

    @Autowired
    private RequestContext requestContext;

    public ActivityController(GarminService garminService){
        this.garminService = garminService;
    }


    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> activities(
        @RequestParam(value = "start", defaultValue = "0") Integer start,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        String displayName = requestContext.getUser().getDisplayName();

        return ResponseEntity.ok(garminService.getActivities(displayName, start, limit));
    }

    @GetMapping("/activity-types")
    public ResponseEntity<List<ActivityType>> getActivityTypes() {
        return ResponseEntity.ok(garminService.getActivityTypes());
    }
}
