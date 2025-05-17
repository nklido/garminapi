package com.nklido.garminapi.security;

import com.nklido.garminapi.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.nklido.garminapi.fixtures.GarminSessionFactory.getValidSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationInterceptorTest {

    private AuthenticationInterceptor authenticationInterceptor;
    private AuthService authService;
    private RequestContext requestContext;

    @BeforeEach
    void setUp() {
        authService = mock(AuthService.class);
        requestContext = new RequestContext();
        authenticationInterceptor = new AuthenticationInterceptor(requestContext, authService);
    }

    @Test
    void test_session_id_missing() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        boolean result = authenticationInterceptor.preHandle(req, res, new Object());
        assertThat(result).isEqualTo(false);
        assertThat(res.getStatus()).isEqualTo(401);
    }

    @Test
    void test_invalid_session_id() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.addHeader("X-SESSION-ID", "invalid-session-id");

        when(authService.getSession("invalid-session-id")).thenReturn(null);

        boolean result = authenticationInterceptor.preHandle(req, res, new Object());
        assertThat(result).isEqualTo(false);
        assertThat(res.getStatus()).isEqualTo(401);
    }

    @Test
    void test_valid_session() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.addHeader("X-SESSION-ID", "valid-session-id");


        GarminSession session = getValidSession();
        when(authService.getSession("valid-session-id")).thenReturn(session);

        boolean result = authenticationInterceptor.preHandle(req, res, new Object());
        assertThat(result).isEqualTo(true);
        assertThat(res.getStatus()).isEqualTo(200);

        assertThat(requestContext).isNotNull();
        assertThat(requestContext.getUser().getDisplayName()).isEqualTo(session.getUser().getDisplayName());
        assertThat(requestContext.getBearerToken()).isEqualTo(session.getToken().getAccessToken());
    }
}