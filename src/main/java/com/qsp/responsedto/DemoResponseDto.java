package com.qsp.responsedto;

import java.util.Collection;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DemoResponseDto {
	int intdata;
	String stringdata;
	Collection<Integer> coolectiondata;
	Map<String, String> mapdata;
}
