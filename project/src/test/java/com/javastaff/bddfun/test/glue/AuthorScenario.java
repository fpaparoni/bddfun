package com.javastaff.bddfun.test.glue;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.springframework.beans.factory.annotation.Autowired;

import com.javastaff.bddfun.model.Author;
import com.javastaff.bddfun.repository.AuthorRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

public class AuthorScenario extends BaseScenario{

	private String AUTHOR_ENDPOINT = "http://localhost:8088/authors";

	private Author.AuthorBuilder authorBuilder = Author.builder();

	@Autowired
	private AuthorRepository authorRepository;

	@Given("a username {string}")
	public void a_username(String username) {
		authorBuilder.username(username);
	}

	@Given("an email {string}")
	public void an_email(String email) {
		authorBuilder.email(email);
	}

	@Given("a bio {string}")
	public void a_bio(String bio) {
		authorBuilder.bio(bio);
	}

	@When("I submit this information to save a new user")
	public void i_submit_this_information_to_save_a_new_user() {
		String path = AUTHOR_ENDPOINT + "/";
		cucumberContextHolder.setResponse(given().body(authorBuilder.build()).contentType(ContentType.JSON).accept(ContentType.JSON).when()
				.post(path));
		// Reset for next build
		authorBuilder = Author.builder();
	}

	@Given("a system with the author with username {string}")
	public void a_system_with_the_author_with_username(String username) {
		if (authorRepository.findById(username).isEmpty()) {
			Author author = Author.builder().username(username).bio("a bio").email("email").build();
			authorRepository.save(author);
		}
	}

	@Given("a system without the author with username {string}")
	public void a_system_without_the_author_with_username(String username) {
		if (authorRepository.findById(username).isPresent()) {
			authorRepository.deleteById(username);
		}
	}

	@When("I read the author with username {string}")
	public void i_read_the_author_with_username(String username) {
		String path = AUTHOR_ENDPOINT + "/" + username+"/";
		cucumberContextHolder.setResponse(when().get(path));
	}
}
