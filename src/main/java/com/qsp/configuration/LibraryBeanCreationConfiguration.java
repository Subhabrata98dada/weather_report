package com.qsp.configuration;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryBeanCreationConfiguration {

	@Bean("random")
	public Random createRandomObject() {
		return new Random();
	}
}
