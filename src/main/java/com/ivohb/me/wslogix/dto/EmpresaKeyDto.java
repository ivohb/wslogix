package com.ivohb.me.wslogix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import com.ivohb.me.wslogix.validation.EmpresaKeyValidation;

@EmpresaKeyValidation
public class EmpresaKeyDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "campo_obrigatorio")
	@CNPJ(message = "cnpj_invalido")
	private String cnpj;

	@NotNull(message = "campo_obrigatorio")
	@Length(min=1, max=36, message="tamanho_campo_invalido")
	private String chave;

	public EmpresaKeyDto() {}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	
}

