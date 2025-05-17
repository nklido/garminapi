package com.nklido.garminapi.controllers;

import com.nklido.garminapi.core.model.UserConnection;
import com.nklido.garminapi.core.model.UserProfile;
import com.nklido.garminapi.core.services.GarminService;
import com.nklido.garminapi.security.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private GarminService garminService;

    @Autowired
    private RequestContext requestContext;

    @GetMapping(value = "/user")
    public ResponseEntity<UserProfile> getUser() {
        String displayName = requestContext.getUser().getDisplayName();
        return ResponseEntity.ok(garminService.getUser(displayName));
    }

    @GetMapping(value = "/user/connections")
    public ResponseEntity<List<UserConnection>> getUserConnections() {
        String displayName = requestContext.getUser().getDisplayName();
        return ResponseEntity.ok(garminService.getUserConnections(displayName));
    }
}
