package com.macgarcia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AREA")
public class Area {

	@Id
	@Column(name = "CODIGO", nullable = false, unique = true)
	private String codigo;

	@Column(name = "Nome", nullable = false)
	private String nome;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
