package com.javastaff.bddfun.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javastaff.bddfun.model.Post;
import com.javastaff.bddfun.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("posts")
public class PostResource {
	@Autowired
	private PostService postService;
	
	@GetMapping("/{postId}")
	@Operation(summary = "Get post details")
    public @ResponseBody ResponseEntity<Post> detail(
    		@Parameter(name="postId", description = "Post id", required = true) 
			@PathVariable("postId") Long postId) {
        return new ResponseEntity<>(postService.get(postId), HttpStatus.OK);
    }
	
	@GetMapping
	@Operation(summary = "Get all posts")
    public @ResponseBody ResponseEntity<List<Post>> getAll(
    		@Parameter(name = "authorId", description = "authorId", required = false) 
    		@RequestParam(value="authorId", required = false) String authorId) {
        return new ResponseEntity<>(postService.getAll(authorId), HttpStatus.OK);
    }
	
	@PostMapping
	@Operation(summary = "Save new post")
    public @ResponseBody ResponseEntity<Post> save(@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }
}
