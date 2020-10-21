package com.macgarcia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.macgarcia.model.Area;
import com.macgarcia.model.Funcionario;

public abstract class StartProcessModel {
	
	@PersistenceContext
	private EntityManager manager;

	protected List<Funcionario> maiores = new ArrayList<>();
	protected List<Funcionario> menores = new ArrayList<>();
	protected Float media;
	protected String msg = null;

	protected void selecionarMaiores(List<Funcionario> dados) {
		maiores.clear();
		boolean first = true;
		int index;
		for (Funcionario f : dados) {
			if (first) {
				maiores.add(f);
				first = false;
				continue;
			}
			
			index = 0;
			for (Funcionario f2 : maiores) {
				if (f.getSalario() > f2.getSalario()) {
					maiores.set(index, f);
				}
				if (f.getSalario().equals(f2.getSalario())) {
					maiores.add(f);
					break;
				}
				index++;
			}
		}
	}

	protected void selecionarMenores(List<Funcionario> dados) {
		menores.clear();
		boolean first = true;
		int index;
		for (Funcionario f : dados) {
			if (first) {
				menores.add(f);
				first = false;
				continue;
			}

			index = 0;
			for (Funcionario f2 : menores) {
				if (f.getSalario() < f2.getSalario()) {
					menores.set(index, f);
				}
				if (f.getSalario().equals(f2.getSalario())) {
					menores.add(f);
					break;
				}
				index++;
			}
		}
	}

	protected void selecionarMedia(List<Funcionario> dados) {
		this.media = 0f;
		int i = dados.size();
		float count = 0;
		for (Funcionario f : dados) {
			count += f.getSalario();
		}
		this.media = count / i;
	}
	
	protected List<Funcionario> buscarFuncionarios() {
		String sql = "select f from Funcionario f";
		TypedQuery<Funcionario> query = manager.createQuery(sql, Funcionario.class);
		return query.getResultList();
	}
	
	protected Area buscarArea(String codigo) {
		String sql = "select a from Area a where a.codigo = :codigo";
		TypedQuery<Area> query = manager.createQuery(sql, Area.class);
		query.setParameter("codigo", codigo);
		return query.getSingleResult();
	}

}
