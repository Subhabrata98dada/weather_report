package com.qsp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.modelmapper.ResponseEntityMapper;
import com.qsp.modelmapper.ResponseStructuremodelMapper;
import com.qsp.requestdto.ClientSaveDto;
import com.qsp.responsedto.ResponseStructure;
import com.qsp.service.ClientService;
import com.qsp.util.ClientType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "/client" , description = "API related to users")
public class ClientController {
	
	private final ResponseStructuremodelMapper responseStructuremapper;
	
	private final ResponseEntityMapper responseEntityMapper;
	
	private final ClientService clientService;
	
	@GetMapping("/registerpre")
	@Operation(description = "return all user type before add new user")
	public ResponseEntity<ResponseStructure> userRegistrationPreprocessing() {
		String[] payload=ClientType.getAllTypes();
		ResponseStructure structure=responseStructuremapper
				.mapToResponseStructure(HttpStatus.OK,"array",payload);
		return responseEntityMapper.getResponseEntity(structure,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseStructure> veryfyClient(@RequestBody ClientSaveDto dto) {
		
		return responseEntityMapper.getResponseEntity(responseStructuremapper
				.mapToResponseStructure(HttpStatus.OK,"String"
				, clientService.sendOtpRegister(dto))
				, HttpStatus.OK);
	}
	
	@PostMapping("/{email}/{otp}")
	public ResponseEntity<ResponseStructure> saveClient
	                                          (@PathVariable("email") String email
			                    ,@PathVariable("otp") String otp) {
		ResponseStructure structure=responseStructuremapper
				.mapToResponseStructure(HttpStatus.CREATED,"String"
				, clientService.validateOtpRegister(email, otp));
		return responseEntityMapper.getResponseEntity(structure,HttpStatus.CREATED);
	}
}
