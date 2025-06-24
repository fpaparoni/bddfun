package com.javastaff.bddfun.test.glue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseScenario {
	@Autowired
	protected CucumberContextHolder cucumberContextHolder;

	@BeforeAll
	public static void setUpRestAssured() {
		RestAssured.port = 8088;
	}
}
