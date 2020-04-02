package com.ivohb.me.wslogix.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "wsducum")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cod_empresa", length = 2, unique = true, nullable = false)
	private String codEmpresa;

	@Column(name = "num_docum", nullable = false)
	private Integer numDocum;

	@Column(name = "cod_nivel", length = 2, nullable = false)
	private String codNivel;

	@Column(name = "cod_hierarquia", nullable = false)
	private Integer codHierarq;

	@Column(name = "usuario_aprovacao", length = 15)
	private String userAprovacao;

	@Column(name = "data_aprovacao")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date datAprovacao;
	
	@Column(name = "hora_aprovacao")
	private String horAprovacao;

	public Documento() { }
	
	public Documento(Integer id, Integer numDocum) {
		super();
		this.id = id;
		this.numDocum = numDocum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public Integer getNumDocum() {
		return numDocum;
	}

	public void setNumDocum(Integer numDocum) {
		this.numDocum = numDocum;
	}

	public String getCodNivel() {
		return codNivel;
	}

	public void setCodNivel(String codNivel) {
		this.codNivel = codNivel;
	}

	public Integer getCodHierarq() {
		return codHierarq;
	}

	public void setCodHierarq(Integer codHierarq) {
		this.codHierarq = codHierarq;
	}

	public String getUserAprovacao() {
		return userAprovacao;
	}

	public void setUserAprovacao(String userAprovacao) {
		this.userAprovacao = userAprovacao;
	}

	public Date getDatAprovacao() {
		return datAprovacao;
	}

	public void setDatAprovacao(Date datAprovacao) {
		this.datAprovacao = datAprovacao;
	}

	public String getHorAprovacao() {
		return horAprovacao;
	}

	public void setHorAprovacao(String horAprovacao) {
		this.horAprovacao = horAprovacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
