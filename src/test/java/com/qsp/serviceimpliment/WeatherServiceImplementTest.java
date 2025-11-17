package com.qsp.serviceimpliment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qsp.modelmapper.WeatherMapper;
import com.qsp.repository.WeatherRepository;
import com.qsp.serviceimplement.WeatherServiceImplement;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceImplementTest {
	
	@Mock
	private WeatherRepository weatherrepo;
	
	@Mock
	private WeatherMapper weathermappper;
	
	@InjectMocks
	private WeatherServiceImplement weatherService;
	@Test
	public void addWeatherTest() {
		System.out.println("First mock test");
	}
	
	@Test
	public void updateWeatherTest() {
		System.out.println("Second mock test");
	}

}
