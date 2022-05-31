package com.javastaff.bddfun.test.glue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Then;

public class CommonSteps {
	
	@Autowired
	private CucumberContextHolder cucumberContext;
	
	@Then("I receive a correct response")
	public void i_receive_a_correct_response() {
	    System.out.println(cucumberContext.getResponse().asPrettyString());
		assertEquals(200, cucumberContext.getResponse().getStatusCode());
	}
	
	@Then("I receive an error")
	public void i_receive_an_error() {
		System.out.println(cucumberContext.getResponse().asPrettyString());
		assertNotEquals(200, cucumberContext.getResponse().getStatusCode());
	}
}
