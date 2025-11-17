package com.qsp.service;

import java.util.List;
import java.util.Map;

import com.qsp.entity.WeatherReport;
import com.qsp.requestdto.WeatherReportUpdateRequestDto;
import com.qsp.requestdto.WeatherSaveDto;

public interface WeatherService {
	String saveCityWeatherInfoService(WeatherSaveDto dto);

	WeatherSaveDto getWeatherByIdService(Integer id);

	List<WeatherReport> getAllIndiaWeatherService();

	Map<String, WeatherSaveDto> doUpdateWeatherReport(Integer id, WeatherReportUpdateRequestDto reqdto);

	public String deleteWeatherService(Integer id);
	
	List<WeatherReport> getWeatherPageService(Integer pagenumber,Integer pagesize);
}
