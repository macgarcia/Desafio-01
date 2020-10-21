package com.macgarcia.model;

import java.io.Serializable;
import java.util.List;

public class InfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Funcionario> funcionarios;
	private List<Area> areas;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

}
