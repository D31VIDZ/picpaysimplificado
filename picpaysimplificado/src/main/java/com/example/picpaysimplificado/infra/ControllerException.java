package com.example.picpaysimplificado.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.picpaysimplificado.dots.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerException {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity threatDuplication(DataIntegrityViolationException exception) {
		ExceptionDTO newException = new ExceptionDTO("Usuario já cadastrado", "400");
		return ResponseEntity.badRequest().body(newException);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threat404(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity threatGeneral(Exception exception) {
		ExceptionDTO newException = new ExceptionDTO(exception.getMessage(), "500");
		return ResponseEntity.internalServerError().body(newException);
	}
	
}
