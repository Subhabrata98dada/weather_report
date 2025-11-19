package com.qsp.listiner;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.qsp.event.SaveWeatherEvent;

@Component
public class AuditListiner {
	
	@EventListener()
	public void handleSaveWeatherEvent(SaveWeatherEvent event) {
		System.out.println(event.getCity()+" weather data saved");
	}
}
