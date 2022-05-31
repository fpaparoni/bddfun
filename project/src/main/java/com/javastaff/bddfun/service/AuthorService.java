package com.javastaff.bddfun.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.javastaff.bddfun.model.Author;
import com.javastaff.bddfun.repository.AuthorRepository;

@Service
@Validated
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;

	public Author get(String authorId) {
		Optional<Author> author=authorRepository.findById(authorId);
		if (author.isPresent()) {
			System.out.println("Cercando "+authorId+" ho trovato "+author.get().getUsername());
			return author.get();
		} else {
			throw new RuntimeException();
		}
	}

	public List<Author> getAll() {
		return authorRepository.findAll();
	}
	
	public Author save(@Valid Author author) {
		return authorRepository.save(author);
	}
}
