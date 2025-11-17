package com.qsp.exceptionhandling;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qsp.modelmapper.ResponseEntityMapper;
import com.qsp.modelmapper.ResponseStructuremodelMapper;
import com.qsp.responsedto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private ResponseStructuremodelMapper reponseStructuremapper;
	
	@Autowired
	private ResponseEntityMapper responseEntityMapper;
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ResponseStructure> handleNoSuchElementException
	                                                 (NoSuchElementException ex){
		ResponseStructure structure=reponseStructuremapper.
				mapToResponseStructure(HttpStatus.NOT_FOUND,"string",ex.getMessage());
		structure.setStatus(false);
		return responseEntityMapper.getResponseEntity(structure,HttpStatus.NOT_FOUND);
	}
}
