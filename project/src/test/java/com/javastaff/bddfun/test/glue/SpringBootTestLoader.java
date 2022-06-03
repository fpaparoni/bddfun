package com.javastaff.bddfun.test.glue;

import javax.sql.DataSource;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("bddtest")
public class SpringBootTestLoader {
	static PostgreSQLContainer postgresContainer;

	/**
	 * Initial database setup
	 */
	@BeforeAll
	public static void setup() {
		System.out.println("starting DB");
		DockerImageName myImage = DockerImageName.parse("bddfun-database").asCompatibleSubstituteFor("postgres");
		postgresContainer = new PostgreSQLContainer(myImage)
			       .withDatabaseName("bddfun")
			       .withUsername("postgres")
			       .withPassword("postgres");
		postgresContainer.start();
		System.out.println(postgresContainer.getJdbcUrl());
	}

	/**
	 * Datasource dynamic configuration
	 *
	 */
	@TestConfiguration
	static class PostgresTestConfiguration {
		@Bean
		DataSource dataSource() {
			HikariConfig hikariConfig = new HikariConfig();
			hikariConfig.setJdbcUrl(postgresContainer.getJdbcUrl());
			hikariConfig.setUsername(postgresContainer.getUsername());
			hikariConfig.setPassword(postgresContainer.getPassword());
			return new HikariDataSource(hikariConfig);
		}
	}

	/**
	 * Shutdown
	 */
	@AfterAll
	public static void tearDown() {
		System.out.println("closing DB connection");
		postgresContainer.stop();
	}
}