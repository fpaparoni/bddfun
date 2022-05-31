package com.javastaff.bddfun.test.glue;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javastaff.bddfun.model.Author;
import com.javastaff.bddfun.model.Post;
import com.javastaff.bddfun.repository.AuthorRepository;
import com.javastaff.bddfun.repository.PostRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

public class PostScenario extends BaseScenario {

	private String POST_ENDPOINT = "http://localhost:8088/posts";

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PostRepository postRepository;

	private Post.PostBuilder postBuilder = Post.builder();
	
	@Given("an author with username {string} already saved")
	public void alreadySavedAuthor(String username) {
		if (authorRepository.findById(username).isEmpty()) {
			Author author = Author.builder().username(username).build();
			authorRepository.save(author);
		}
	}
	
	@Given("a post by author with username {string} already saved")
	public void alreadySavedPost(String username) {
		if (postRepository.findByAuthorUsername(username).isEmpty()) {
			Author author=authorRepository.getById(username);
			Post post=Post.builder().author(author).body("body").title("title").build();
			postRepository.save(post);
		}
	}

	@Given("the title {string}")
	public void the_title(String title) {
		postBuilder.title(title);
	}

	@Given("the body {string}")
	public void the_body(String body) {
		postBuilder.body(body);
	}

	@Given("the author with username {string}")
	public void the_author_with_username(String username) {
		Author author=Author.builder().username(username).build();
		postBuilder.author(author);
	}

	@When("I submit this post to save it")
	public void i_submit_this_post_to_save_it() {
		String path = POST_ENDPOINT + "/";
		cucumberContextHolder.setResponse(given().body(postBuilder.build()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).when().post(path));
		// Reset for next build
		postBuilder = Post.builder();
	}

	@When("I read the posts by author {string}")
	public void i_read_the_posts_by_author(String username) {
		String path = POST_ENDPOINT;
		cucumberContextHolder.setResponse(given().param("authorId", username).when().get(path));
	}

	@Then("all the posts are by {string}")
	public void all_the_posts_are_by(String username) {
		List<Post> postList = cucumberContextHolder.getResponse().body().jsonPath().getList(".", Post.class);
		postList
			.stream()
			.allMatch(post -> post.getAuthor().getUsername().equals(username));
	}

}