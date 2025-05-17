package com.nklido.garminapi.config;

import com.nklido.garminapi.security.RequestContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;

@Configuration
public class GarminClientConfig {

    @Bean(name = "garminRestTemplate")
    public RestTemplate restTemplate(RequestContext ctx){
        RestTemplate requestTemplate = new RestTemplate();
        requestTemplate.getInterceptors().add((request, body, execution) -> {
           String token = ctx.getBearerToken();
           if (token != null) {
               request.getHeaders().setBearerAuth(token);
           }
           return execution.execute(request, body);
        });

        requestTemplate.getInterceptors().add((request, body, execution) -> {
            HttpHeaders headers = request.getHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            headers.setAccept(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
            headers.setAcceptLanguage(Locale.LanguageRange.parse("en-US,en;q=0.5"));
            headers.set("X-App-Ver", "4.73.5.0");
            headers.set("Di-Backend", "connectapi.garmin.com");
            return execution.execute(request, body);
        });

        return requestTemplate;
    }


}
