package com.qsp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.modelmapper.ResponseEntityMapper;
import com.qsp.modelmapper.ResponseStructuremodelMapper;
import com.qsp.responsedto.ResponseStructure;
import com.qsp.util.UserType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "/user" , description = "API related to users")
public class UserController {
	
	private final ResponseStructuremodelMapper responseStructuremapper;
	
	private final ResponseEntityMapper responseEntityMapper;
	
	@GetMapping("/registerpre")
	@Operation(description = "return all user type before add new user")
	public ResponseEntity<ResponseStructure> userRegistrationPreprocessing() {
		String[] payload=UserType.getAllTypes();
		ResponseStructure structure=responseStructuremapper
				.mapToResponseStructure(HttpStatus.OK,"array",payload);
		return responseEntityMapper.getResponseEntity(structure,HttpStatus.OK);
	}
}
