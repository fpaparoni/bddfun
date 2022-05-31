package com.javastaff.bddfun.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
