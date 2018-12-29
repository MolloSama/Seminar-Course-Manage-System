package com.bkg.coursemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bkg.coursemanager")
public class CoursemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursemanagerApplication.class, args);
	}
}
