package com.nklido.garminapi.services;

import com.nklido.garminapi.security.GarminSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static com.nklido.garminapi.fixtures.GarminSessionFactory.getValidSession;
import static com.nklido.garminapi.fixtures.GarminSessionFactory.toJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockMvc
class AuthServiceTest {


    private AuthService authService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        RestTemplate rt = new RestTemplate();
        authService = new AuthService(rt);
        mockServer = MockRestServiceServer.bindTo(rt).build();
    }


    @Test
    void test_login() {

        String json = """
                {
                    "session_id": "c946f5d1-43c2-4d46-a42e-01ccac82e9ad"
                }
                """;

        mockServer.expect(requestTo(matchesPattern(".*login")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        String sessionId = authService.login("john.doe@example.com","secret");

        assertThat(sessionId).isEqualTo("c946f5d1-43c2-4d46-a42e-01ccac82e9ad");

        mockServer.verify();
    }

    @Test
    void test_login_failed() {

        mockServer.expect(requestTo(matchesPattern(".*login")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.FORBIDDEN));

        String sessionId = authService.login("john.doe@example.com","secret");

        assertThat(sessionId).isEqualTo(null);
        mockServer.verify();
    }

    @Test
    void test_get_valid_session() {
        GarminSession mockSession = getValidSession();

        mockServer.expect(requestTo(matchesPattern(".*token.*")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess((toJson(mockSession)), MediaType.APPLICATION_JSON));

        GarminSession session = authService.getSession("example-session-id");
        assertThat(session).isNotNull();
        assertThat(session.getToken().getAccessToken()).isEqualTo(mockSession.getToken().getAccessToken());
        mockServer.verify();
    }

    @Test
    void test_get_invalid_session() {
        mockServer.expect(requestTo(matchesPattern(".*token.*")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.FORBIDDEN));

        GarminSession session = authService.getSession("example-session-id");
        assertThat(session).isNull();
        mockServer.verify();
    }
}