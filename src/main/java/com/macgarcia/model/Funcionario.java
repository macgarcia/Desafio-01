package com.macgarcia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "SOBRENOME")
	private String sobrenome;

	@Column(name = "SALARIO")
	private Float salario;

	@ManyToOne
	@JoinColumn(name = "AREA_ID")
	private Area areaObj;

	@Transient
	private String area;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Area getAreaObj() {
		return areaObj;
	}

	public void setAreaObj(Area areaObj) {
		this.areaObj = areaObj;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}