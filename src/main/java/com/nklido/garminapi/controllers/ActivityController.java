package com.nklido.garminapi.controllers;

import com.nklido.garminapi.responses.ActivityResponse;
import com.nklido.garminapi.responses.GarminActivitiesResponse;
import com.nklido.garminapi.services.GarminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {

    private final GarminService garminService;

    public ActivityController(GarminService garminService){
        this.garminService = garminService;
    }


    @GetMapping("/activities")
    public ResponseEntity<List<ActivityResponse>> get() {
        return garminService.getActivities();
    }

    @GetMapping("/activitiesRaw")
    public ResponseEntity<String> getRaw() {
        return garminService.getActivitiesRaw();
    }
}
