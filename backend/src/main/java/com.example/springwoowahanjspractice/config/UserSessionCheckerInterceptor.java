package com.example.springwoowahanjspractice.config;

import in.woowa.platform.gatekeeper.client.GateKeeperLoginCheckService;
import in.woowa.platform.gatekeeper.client.utils.WebUtils;
import in.woowahan.gatekeeper.commons.GKSession;
import in.woowahan.gatekeeper.commons.userdetails.BasicUserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserSessionCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    private GateKeeperLoginCheckService loginCheckService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().startsWith("/error")) {
            String sessionId = getSessionId(request);
            BasicUserProfile basicUserProfile = loginCheckService.getSignIn(request, response, sessionId);
            if (Objects.isNull(basicUserProfile)) {
                return false;
            }

            request.setAttribute("gk_user", basicUserProfile);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private String getSessionId(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, GKSession.SESSION_ID);
        return cookie != null ? cookie.getValue() : null;
    }
}
