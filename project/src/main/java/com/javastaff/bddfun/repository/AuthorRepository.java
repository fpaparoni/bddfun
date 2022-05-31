package com.javastaff.bddfun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javastaff.bddfun.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String>{

}
