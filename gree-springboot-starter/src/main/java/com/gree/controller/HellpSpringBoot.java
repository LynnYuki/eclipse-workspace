package com.gree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellpSpringBoot {
	@RequestMapping("/hello")
	public Object hello(){
		return "hello springboot";
	}
}