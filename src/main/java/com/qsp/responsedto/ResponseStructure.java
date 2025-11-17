package com.qsp.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseStructure {
	private Integer statuscode;
	private Boolean status;
	private String type;
	private Object payload;
}
