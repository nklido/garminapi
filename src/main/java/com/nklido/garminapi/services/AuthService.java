package com.nklido.garminapi.services;

import com.nklido.garminapi.security.GarminSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final RestTemplate authRestTemplate;

    @Value("${auth.api-url}")
    private String authApiUrl;

    public AuthService(RestTemplate authRestTemplate) {
        this.authRestTemplate = authRestTemplate;
    }

    public String login(String username, String password){

        String credentials = username + ":" + password;
        String token = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        // Set headers for form submission
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("token", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<Map> response;
        try {
              response = authRestTemplate.postForEntity(
                    authApiUrl + "/login",
                    request,
                    Map.class
            );
        } catch (HttpStatusCodeException exception) {
            logger.error(exception.getMessage());
            return null;
        }

         if (response.getBody() == null) {
             return null;
         }

        Object sessionId = response.getBody().get("session_id");
        return sessionId != null ? sessionId.toString() : null;
    }

    public GarminSession getSession(String sessionId) {

        try {
             ResponseEntity<GarminSession> response = authRestTemplate.getForEntity(
                    authApiUrl + "/token?session_id=" + sessionId,
                     GarminSession.class
            );

            return response.getBody();
        } catch (HttpStatusCodeException exception) {
            logger.error(exception.getMessage());
            return null;
        }

    }
}
