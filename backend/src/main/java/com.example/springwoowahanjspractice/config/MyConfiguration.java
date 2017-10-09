package com.example.springwoowahanjspractice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServerInfoProperties.class)
public class MyConfiguration {
}
