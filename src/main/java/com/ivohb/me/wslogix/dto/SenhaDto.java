package com.ivohb.me.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ivohb.me.wslogix.validation.Senha;

@Senha
public class SenhaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=15, message="tamanho_campo_invalido")
	private String novaSenha;
	
	@NotNull(message = "campo_obrigatorio")
	@Length(min=3, max=15, message="tamanho_campo_invalido")	
	private String confirmaSenha;
	
	public SenhaDto() { }

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

		
}
