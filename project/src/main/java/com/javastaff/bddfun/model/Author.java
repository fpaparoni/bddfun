package com.javastaff.bddfun.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Author {
	@Id
	@NotNull
	private String username;
	@Column
	private String email;
	@Column
	private String bio;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Post> posts;
}
