package br.com.barber.integration.controller.dto.validacao;

import java.util.List;

public class ErrorFormDto extends ErrorDto {

	
	private List<Campo> campos;
	
	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	public static class Campo {
		
		private String campo;
		private String erro;
		
		public Campo(String campo, String erro) {
			this.campo = campo;
			this.erro = erro;
		}

		public String getCampo() {
			return campo;
		}
		
		public void setCampo(String campo) {
			this.campo = campo;
		}
		
		public String getErro() {
			return erro;
		}
		
		public void setErro(String erro) {
			this.erro = erro;
		}
	
	}
	
}
