package com.example.springwoowahanjspractice.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gatekeeper.server")
@Data
public class ServerInfoProperties {

    private String domain;
}
