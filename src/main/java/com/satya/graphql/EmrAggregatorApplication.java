package com.satya.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.satya.graphql.${lec}")
@EnableR2dbcRepositories(basePackages = "com.satya.graphql.${lec}")
public class EmrAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmrAggregatorApplication.class, args);
	}

}
