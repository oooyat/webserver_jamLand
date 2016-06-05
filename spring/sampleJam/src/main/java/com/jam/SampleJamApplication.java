package com.jam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.jam.controller.Controller;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class SampleJamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleJamApplication.class, args);
	}
}
//you should designate JDBC URL : jdbc:h2:mem:testdb