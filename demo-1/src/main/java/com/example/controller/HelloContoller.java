package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.LeeJSONResult;
import com.example.pojo.Resource;
@RestController
public class HelloContoller {
	
	@Autowired
	private Resource resource;
	
		@RequestMapping("/hello")
		public Object hello() {
			return "hello ~";
		}
		
		@RequestMapping("/getResource")
		public LeeJSONResult getResource() {
			Resource bean =new Resource();
			BeanUtils.copyProperties(resource, bean);
			return LeeJSONResult.ok(bean);
		}
}
