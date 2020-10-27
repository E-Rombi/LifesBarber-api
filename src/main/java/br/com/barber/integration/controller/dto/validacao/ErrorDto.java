package br.com.barber.integration.controller.dto.validacao;

import java.time.LocalDateTime;

public class ErrorDto {
	
	private Integer status;
	private String message;
	private LocalDateTime datahora;
	
	public ErrorDto() {	}

	public ErrorDto(Integer status, String message, LocalDateTime datahora) {
		this.status = status;
		this.message = message;
		this.datahora = datahora;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}
}
