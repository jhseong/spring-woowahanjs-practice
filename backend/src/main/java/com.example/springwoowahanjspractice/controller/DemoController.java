package com.example.springwoowahanjspractice.controller;

import com.example.springwoowahanjspractice.config.ServerInfoProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    private ServerInfoProperties serverInfoProperties;

    @GetMapping("/v1/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/v1/domain-info")
    public String domianInfo() {
        return serverInfoProperties.getDomain();
    }
}
