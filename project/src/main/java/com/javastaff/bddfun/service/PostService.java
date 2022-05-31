package com.javastaff.bddfun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javastaff.bddfun.exception.ResourceNotFoundException;
import com.javastaff.bddfun.model.Post;
import com.javastaff.bddfun.repository.AuthorRepository;
import com.javastaff.bddfun.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Post get(Long postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post "+postId));
	}

	public List<Post> getAll(String authorId) {
		if (authorId!=null) {
			return postRepository.findByAuthorUsername(authorId);
		} else {
			return postRepository.findAll();
		}	
	}

	public Post save(Post post) {
		var author=authorRepository.findById(post.getAuthor().getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("Post "+post.getAuthor().getUsername()));
		post.setAuthor(author);
		return postRepository.save(post);
	}

}
