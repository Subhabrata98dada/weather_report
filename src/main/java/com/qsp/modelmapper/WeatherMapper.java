package com.qsp.modelmapper;

import org.springframework.stereotype.Component;

import com.qsp.entity.WeatherReport;
import com.qsp.requestdto.WeatherSaveDto;

@Component
public final class WeatherMapper {
	
	public WeatherReport saveWeatherDTOtoWeather(WeatherSaveDto dto,WeatherReport report) {
		report.setCity(dto.getCity());
		report.setTemperature(dto.getTemperature());
		report.setWeathertype(dto.getWeathertype());
		return report;
	}
	
	public WeatherSaveDto weatherToWeatherSaveDto
	                        (WeatherReport weather,WeatherSaveDto dto) {
		dto.setTemperature(weather.getTemperature());
		dto.setCity(weather.getCity());
		dto.setWeathertype(weather.getWeathertype());
		return dto;
	}
}
