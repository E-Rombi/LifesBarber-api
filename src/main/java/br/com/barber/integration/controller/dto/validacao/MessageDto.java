package br.com.barber.integration.controller.dto.validacao;

public class MessageDto {

	private String mensagem;

	public MessageDto() {	}

	public MessageDto(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
