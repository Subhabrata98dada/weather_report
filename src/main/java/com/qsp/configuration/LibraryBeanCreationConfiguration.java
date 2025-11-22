package com.qsp.configuration;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.qsp.requestdto.ClientSaveDto;

@Configuration
public class LibraryBeanCreationConfiguration {

	@Bean("random")
	public Random createRandomObject() {
		return new Random();
	}

	@Bean("resttemplate")
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

	@Bean("otpholder")
	public Map<String, Object[]> createOtpHolder() {
		return new ConcurrentHashMap<String, Object[]>();
	}

	@Bean("createclientdto")
	public Map<String, ClientSaveDto> createClientDto() {
		return new ConcurrentHashMap<String, ClientSaveDto>();
	}
}
