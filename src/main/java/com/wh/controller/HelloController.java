package com.wh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.HelloService;

@Controller
public class HelloController {
	
	@Autowired
	HelloService helloService;
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		
		return helloService.sayHello("tomcat");
	}
	
	
	@RequestMapping("/suc")
	public String success() {
		return "success";
	}
}
