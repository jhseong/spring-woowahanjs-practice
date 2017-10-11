package com.example.springwoowahanjspractice.controller;

import com.example.springwoowahanjspractice.config.ServerInfoProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.debug(String.format("[domain] = [%s]", serverInfoProperties.getDomain()));
        return serverInfoProperties.getDomain();
    }
}
