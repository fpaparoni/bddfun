package com.javastaff.bddfun.test.glue;

import org.springframework.stereotype.Component;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CucumberContextHolder {
	private Response response;
}
