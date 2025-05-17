package com.nklido.garminapi.security;

import com.nklido.garminapi.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final RequestContext ctx;
    private final AuthService authService;

    public AuthenticationInterceptor(RequestContext ctx, AuthService authService) {
        this.ctx = ctx;
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("X-SESSION-ID");
        if (sessionId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        GarminSession garminSession = authService.getSession(sessionId);

        if (garminSession == null || garminSession.getToken() == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        ctx.setBearerToken(garminSession.getToken().getAccessToken());
        ctx.setUser(garminSession.getUser());
        return true;
    }
}
