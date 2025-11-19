package com.qsp.service;

import org.springframework.http.ResponseEntity;

import com.qsp.responsedto.ResponseStructure;

public interface InternationalWeatherCallService {
	ResponseEntity<ResponseStructure> getCityWeather(String city);
	ResponseEntity<ResponseStructure> getAllCityWeather();
}
