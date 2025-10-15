package com.klef.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WatchapiSpringbootApplication extends SpringBootServletInitializer
{

	public static void main(String[] args) 
	{
		SpringApplication.run(WatchapiSpringbootApplication.class, args);
		System.out.println("Hello....!!!!");
	}

}
