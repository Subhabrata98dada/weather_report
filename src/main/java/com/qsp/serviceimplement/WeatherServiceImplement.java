package com.qsp.serviceimplement;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qsp.entity.WeatherReport;
import com.qsp.event.SaveWeatherEvent;
import com.qsp.modelmapper.WeatherMapper;
import com.qsp.repository.WeatherRepository;
import com.qsp.requestdto.WeatherReportUpdateRequestDto;
import com.qsp.requestdto.WeatherSaveDto;
import com.qsp.service.WeatherService;
import com.qsp.util.CacheUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherServiceImplement implements WeatherService {

	
	private final WeatherRepository weatherrepo;
	
	private final WeatherMapper weathermapper;
	
	private final CacheUtil cacheutil;
	
	private final ApplicationEventPublisher publisher;

	@Override
	@Transactional
	public String saveCityWeatherInfoService(WeatherSaveDto dto) {
		WeatherReport report = weathermapper.saveWeatherDTOtoWeather(dto,new WeatherReport());
		report=weatherrepo.save(report);
		publisher.publishEvent(new SaveWeatherEvent(dto.getCity()));
		return dto.toString()+" report saved with id :" + report.getId();
	}

	@Override
	@Cacheable(value = "fetch" , key = "#id")
	public WeatherSaveDto getWeatherByIdService(Integer id) {
		Optional<WeatherReport> report=weatherrepo.findById(id);
		if(report.isEmpty())
			throw new NoSuchElementException("Data not found with id "+id);
		WeatherSaveDto response=weathermapper.weatherToWeatherSaveDto(report.get(),
				new WeatherSaveDto());
		return response;
	}

	@Override
	public List<WeatherReport> getAllIndiaWeatherService() {
		return weatherrepo.findAll();
	}

	@Override
	@Transactional
	public Map<String,WeatherSaveDto> doUpdateWeatherReport(Integer id, WeatherReportUpdateRequestDto reqdto) {
		Optional<WeatherReport> report=weatherrepo.findById(id);
		if(report.isEmpty()) 
			throw new NoSuchElementException("Data not found with id "+id);
		WeatherReport reportObject=report.get();
		WeatherSaveDto olddata=weathermapper.
				weatherToWeatherSaveDto(reportObject,new WeatherSaveDto());
		WeatherSaveDto newdata=new 
		WeatherSaveDto(reportObject.getCity(),reqdto.getTemperature(),reqdto.getDescription());
		reportObject.setTemperature(reqdto.getTemperature());
		reportObject.setWeathertype(reqdto.getDescription());
		weatherrepo.save(reportObject);
		cacheutil.updateCacheWhileUpdateWeather(id, newdata);
		return Map.of("olddata",olddata,"newdata",newdata);
	}

	@Override
	@Transactional
	@CacheEvict(value = "fetch",key = "#id")
	public String deleteWeatherService(Integer id) {
		if(!weatherrepo.existsById(id)) return "data not found with id "+id;
		weatherrepo.deleteById(id);
		return "Mention data deleted from database";
	}

	@Override
	public List<WeatherReport> getWeatherPageService(Integer pagenumber, Integer pagesize) {
		Pageable pageable=PageRequest.of(pagenumber,pagesize);
		return weatherrepo.findAll(pageable).getContent();
	}
	
}
