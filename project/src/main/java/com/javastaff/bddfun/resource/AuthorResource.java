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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javastaff.bddfun.model.Author;
import com.javastaff.bddfun.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("authors")
public class AuthorResource {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/{authorId}/")
	@Operation(summary = "Get author details")
    public @ResponseBody ResponseEntity<Author> detail(
    		@Parameter(name="authorId", description = "Author id", required = true) 
			@PathVariable("authorId") String authorId) {
		System.out.println("Leggo "+authorId);
        return new ResponseEntity<>(authorService.get(authorId), HttpStatus.OK);
    }
	
	@GetMapping
	@Operation(summary = "Get all authors")
    public @ResponseBody ResponseEntity<List<Author>> getAll() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }
	
	@PostMapping
	@Operation(summary = "Save new author")
    public @ResponseBody ResponseEntity<Author> save(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.save(author), HttpStatus.OK);
    }

}
