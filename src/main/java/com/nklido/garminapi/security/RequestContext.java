package com.nklido.garminapi.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@RequestScope
public class RequestContext {
    private String bearerToken;

    private GarminSession.User user;
}