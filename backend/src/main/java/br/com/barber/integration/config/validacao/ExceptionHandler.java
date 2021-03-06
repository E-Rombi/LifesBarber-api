package br.com.barber.integration.config.validacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.barber.integration.controller.dto.validacao.ErrorDto;
import br.com.barber.integration.controller.dto.validacao.ErrorFormDto;
import br.com.barber.integration.controller.dto.validacao.ErrorFormDto.Campo;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorDto> handleNoSuchElementException(NoSuchElementException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND.value())
				.body(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Registro não encontrado.", LocalDateTime.now()));
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<ErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST.value())
				.body(new ErrorDto(HttpStatus.BAD_REQUEST.value(), "CPF já cadastrado.", LocalDateTime.now()));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();

			campos.add(new ErrorFormDto.Campo(nome, mensagem));
			
		}
		
		ErrorFormDto errorDto = new ErrorFormDto();
		errorDto.setStatus(status.value());
		errorDto.setMessage("Um ou mais campos estão inválidos.");
		errorDto.setDatahora(LocalDateTime.now());
		errorDto.setCampos(campos);
		
		return super.handleExceptionInternal(ex, errorDto, headers, status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ResponseEntity<ErrorDto> body = ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado !", LocalDateTime.now()));
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
		
	}
}
