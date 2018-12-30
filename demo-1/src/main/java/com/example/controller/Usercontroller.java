package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.LeeJSONResult;
import com.example.pojo.User;

//@Controller
@RestController
@RequestMapping("/user")
public class Usercontroller {
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser() {
		User user = new User();
		user.setName("Mark");
		user.setAge(18);
		user.setBirthday(new java.util.Date());
		user.setPassword("123456");
		return user;
	}
	
	@RequestMapping("/getUserJson")
//	@ResponseBody
	public LeeJSONResult getLeeJSONResult() {
		User user = new User();
		user.setName("Mark");
		user.setAge(18);
		user.setBirthday(new java.util.Date());
		user.setPassword("123456");
		return LeeJSONResult.ok(user);
	}
}
