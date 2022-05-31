package com.javastaff.bddfun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javastaff.bddfun.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{

	List<Post> findByAuthorUsername(String authorId);
}
