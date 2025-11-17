package com.qsp.util;

public enum WeatherType {
	COLD,HOT,MEDIUM,CLOUDY,SUNNY,SMOG,SNOWY,RAINY,STORM,WINDY;
	public static String getweatherType(int ordinal) {
		
		switch(ordinal) {
		case 0:
			return COLD.name().toLowerCase();
		case 1:
			return HOT.name().toLowerCase();
		case 2:
			return MEDIUM.name().toLowerCase();
		case 3:
			return CLOUDY.name().toLowerCase();
		case 4:
			return SUNNY.name().toLowerCase();
		case 5:
			return SMOG.name().toLowerCase();
		case 6:
			return SNOWY.name().toLowerCase();
		case 7:
			return RAINY.name().toLowerCase();
		case 8:
			return STORM.name().toLowerCase();
		default:
			return WINDY.name().toLowerCase();
			
		}
	}
}
