package com.javastaff.bddfun.test.glue;

import javax.sql.DataSource;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
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
	 * Setup iniziale del database
	 */
	@BeforeAll
	public static void setup() {
		DockerImageName myImage = DockerImageName.parse("bddfundb").asCompatibleSubstituteFor("postgres");
		postgresContainer = new PostgreSQLContainer(myImage)
			       .withDatabaseName("bddfun")
			       .withUsername("postgres")
			       .withPassword("postgres");
				//new PostgreSQLContainer<>(
				//DockerImageName.parse("bddfundb").asCompatibleSubstituteFor("postgres"));
		postgresContainer.start();
		System.out.println(postgresContainer.getJdbcUrl());
	}

	/**
	 * Configurazione dinamica per il datasource
	 *
	 */
	@TestConfiguration
	static class OracleTestConfiguration {
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
	 * Spegnimento database
	 */
	@AfterAll
	public static void tearDown() {
		System.out.println("closing DB connection");
		postgresContainer.stop();
	}
}