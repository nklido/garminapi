package com.nklido.garminapi.services;

import com.nklido.garminapi.converters.GarminActivityResponseConverter;
import com.nklido.garminapi.responses.ActivityResponse;
import com.nklido.garminapi.responses.GarminActivitiesResponse;
import com.nklido.garminapi.responses.GarminActivityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class GarminService {

    private final String garminBaseUrl = "https://connect.garmin.com";

    private static final Logger logger = LoggerFactory.getLogger(GarminService.class);

    private static final String ACTIVITIES_ENDPOINT = "/activitylist-service/activities/ed5a9f4a-43fa-4601-a367-4e4c11bb9d4b";

    private final GarminActivityResponseConverter garminActivityResponseConverter;

    public GarminService(GarminActivityResponseConverter garminActivityResponseConverter) {
        this.garminActivityResponseConverter = garminActivityResponseConverter;
    }

    public ResponseEntity<String> getActivitiesRaw() {

        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(garminBaseUrl + ACTIVITIES_ENDPOINT)
                .queryParam("start", "0")
                .queryParam("limit", "5")
                .toUriString();


        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:121.0) Gecko/20100101 Firefox/121.0");
        headers.set("Accept", "application/json, text/plain, */*");
        headers.set("Accept-Language", "en-US,en;q=0.5");
        headers.set("X-App-Ver", "4.73.5.0");
        headers.set("Di-Backend", "connectapi.garmin.com");
        headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImRpLW9hdXRoLXNpZ25lci1wcm9kLTIwMjQtcTEifQ.eyJzY29wZSI6WyJBVFBfUkVBRCIsIkFUUF9XUklURSIsIkNPTU1VTklUWV9DT1VSU0VfUkVBRCIsIkNPTU1VTklUWV9DT1VSU0VfV1JJVEUiLCJDT05ORUNUX1JFQUQiLCJDT05ORUNUX1dSSVRFIiwiRFRfQ0xJRU5UX0FOQUxZVElDU19XUklURSIsIkdBUk1JTlBBWV9SRUFEIiwiR0FSTUlOUEFZX1dSSVRFIiwiR0NPRkZFUl9SRUFEIiwiR0NPRkZFUl9XUklURSIsIkdIU19TQU1EIiwiR0hTX1VQTE9BRCIsIkdPTEZfQVBJX1JFQUQiLCJHT0xGX0FQSV9XUklURSIsIklOU0lHSFRTX1JFQUQiLCJJTlNJR0hUU19XUklURSIsIk9NVF9DQU1QQUlHTl9SRUFEIiwiT01UX1NVQlNDUklQVElPTl9SRUFEIiwiUFJPRFVDVF9TRUFSQ0hfUkVBRCJdLCJpc3MiOiJodHRwczovL2RpYXV0aC5nYXJtaW4uY29tIiwicmV2b2NhdGlvbl9lbGlnaWJpbGl0eSI6WyJHTE9CQUxfU0lHTk9VVCJdLCJjbGllbnRfdHlwZSI6IlVOREVGSU5FRCIsImV4cCI6MTczMDY0NTk1MywiaWF0IjoxNzMwNTU3ODU0LCJnYXJtaW5fZ3VpZCI6IjgwYWU0M2RiLWFhMTMtNGM4Zi05MDBlLTQ4ZDIwMTNlYzA0OCIsImp0aSI6IjlmZDQ0ZmFhLWRiNzAtNGM5OS05NDI2LTU0NTE4YzNkM2ZiOCIsImNsaWVudF9pZCI6IkdBUk1JTl9DT05ORUNUX01PQklMRV9BTkRST0lEX0RJIn0.AefCH1fH2SmlN1_ylE7s1u_aNYl5uQE9QW-0Fc9ZyH41xc4Ihkc9PfpoeNbFvLWuDybl4PyWbWlCVEWy_6jl-uMgzHoyv7dI95U97cSI815zMa0RVUVPHNpHpr3HlL9lHNMqfR1CXXQ-O2nyNVbddXeAXRk0-1o7Dem6fVXWH247BbcnkEtFMRugCMjtiKfO0VLzLW-UA9x_sUjQRL-kwHZJ2XT4CbFtmYVlentOZ4YJWe_GRnqe3hwsf60GpRBzbVrDURP8v-p5qJd3-AQ6XWq8A1_6fXIng90sWNsZFOohnrkVdcUrFLlMx2e9TEs0NfvdQ-haBhs544s8l-T0UQ");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        logResponse(responseEntity);

        return responseEntity;

    }

    private void logResponse(ResponseEntity<?> responseEntity) {
        logger.info("Response Status Code: {}", responseEntity.getStatusCode());
        logger.info("Response Headers: {}", responseEntity.getHeaders());

        logger.info("Response Body: {}", responseEntity.getBody());
    }

    public List<ActivityResponse> getActivities(){
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(garminBaseUrl + ACTIVITIES_ENDPOINT)
                .queryParam("start", "0")
                .queryParam("limit", "5")
                .toUriString();


        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:121.0) Gecko/20100101 Firefox/121.0");
        headers.set("Accept", "application/json, text/plain, */*");
        headers.set("Accept-Language", "en-US,en;q=0.5");
        headers.set("X-App-Ver", "4.73.5.0");
        headers.set("Di-Backend", "connectapi.garmin.com");
        headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImRpLW9hdXRoLXNpZ25lci1wcm9kLTIwMjQtcTEifQ.eyJzY29wZSI6WyJBVFBfUkVBRCIsIkFUUF9XUklURSIsIkNPTU1VTklUWV9DT1VSU0VfUkVBRCIsIkNPTU1VTklUWV9DT1VSU0VfV1JJVEUiLCJDT05ORUNUX1JFQUQiLCJDT05ORUNUX1dSSVRFIiwiRFRfQ0xJRU5UX0FOQUxZVElDU19XUklURSIsIkdBUk1JTlBBWV9SRUFEIiwiR0FSTUlOUEFZX1dSSVRFIiwiR0NPRkZFUl9SRUFEIiwiR0NPRkZFUl9XUklURSIsIkdIU19TQU1EIiwiR0hTX1VQTE9BRCIsIkdPTEZfQVBJX1JFQUQiLCJHT0xGX0FQSV9XUklURSIsIklOU0lHSFRTX1JFQUQiLCJJTlNJR0hUU19XUklURSIsIk9NVF9DQU1QQUlHTl9SRUFEIiwiT01UX1NVQlNDUklQVElPTl9SRUFEIiwiUFJPRFVDVF9TRUFSQ0hfUkVBRCJdLCJpc3MiOiJodHRwczovL2RpYXV0aC5nYXJtaW4uY29tIiwicmV2b2NhdGlvbl9lbGlnaWJpbGl0eSI6WyJHTE9CQUxfU0lHTk9VVCJdLCJjbGllbnRfdHlwZSI6IlVOREVGSU5FRCIsImV4cCI6MTczMDY0NTk1MywiaWF0IjoxNzMwNTU3ODU0LCJnYXJtaW5fZ3VpZCI6IjgwYWU0M2RiLWFhMTMtNGM4Zi05MDBlLTQ4ZDIwMTNlYzA0OCIsImp0aSI6IjlmZDQ0ZmFhLWRiNzAtNGM5OS05NDI2LTU0NTE4YzNkM2ZiOCIsImNsaWVudF9pZCI6IkdBUk1JTl9DT05ORUNUX01PQklMRV9BTkRST0lEX0RJIn0.AefCH1fH2SmlN1_ylE7s1u_aNYl5uQE9QW-0Fc9ZyH41xc4Ihkc9PfpoeNbFvLWuDybl4PyWbWlCVEWy_6jl-uMgzHoyv7dI95U97cSI815zMa0RVUVPHNpHpr3HlL9lHNMqfR1CXXQ-O2nyNVbddXeAXRk0-1o7Dem6fVXWH247BbcnkEtFMRugCMjtiKfO0VLzLW-UA9x_sUjQRL-kwHZJ2XT4CbFtmYVlentOZ4YJWe_GRnqe3hwsf60GpRBzbVrDURP8v-p5qJd3-AQ6XWq8A1_6fXIng90sWNsZFOohnrkVdcUrFLlMx2e9TEs0NfvdQ-haBhs544s8l-T0UQ");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);

        ResponseEntity<GarminActivitiesResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, GarminActivitiesResponse.class);

        GarminActivitiesResponse garminActivitiesResponse = responseEntity.getBody();

        return garminActivityResponseConverter.convert(garminActivitiesResponse);
    }
}
