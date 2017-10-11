package com.example.springwoowahanjspractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInOutController {

    @GetMapping(value = "/signin/callback")
    public String signinCallback(@RequestParam("sessionId") String sessionId,
                                 @RequestParam("returnUrl") String returnUrl,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        Cookie userCookie = new Cookie("gk-session-id", sessionId);
        response.addCookie(userCookie);
        return "redirect:" + returnUrl;
    }
}
