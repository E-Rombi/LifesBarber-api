package br.com.barber.integration.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.barber.integration.exception.RegisterNotFoundException;

@RestControllerAdvice
public class ExceptionHandler  {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(RegisterNotFoundException.class)
	public ResponseEntity<?> handler(RegisterNotFoundException e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorDto(e.getMessage()));
	}
}
