package com.qsp.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SaveWeatherEvent {
	private String city;
}
