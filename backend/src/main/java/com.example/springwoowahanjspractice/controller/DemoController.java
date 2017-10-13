package com.example.springwoowahanjspractice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class DemoController {

	@GetMapping("/v1/hello")
	public String hello() {
		return "hello";
	}
}
