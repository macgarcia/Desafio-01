package com.macgarcia.service;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.macgarcia.model.Area;
import com.macgarcia.model.Funcionario;

@Singleton
public class StratService {
	
	@PersistenceContext
	EntityManager manager;

	@Transactional
	public boolean salvarAreas(List<Area> areas) {
		try {
			areas.forEach(a -> manager.persist(a));
			return true;
		} catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Transactional
	public boolean salvarFuncionarios(List<Funcionario> funcionarios) {
		try {
			for (Funcionario f : funcionarios) {
				f.setAreaObj(this.buscarArea(f.getArea()));
				manager.persist(f);
			}
			return true;
		} catch(Exception e) {
			e.getMessage();
			return false;
		}
	}
	
	private Area buscarArea(String codigo) {
		String sql = "select a from Area a where a.codigo = :codigo";
		TypedQuery<Area> query = manager.createQuery(sql, Area.class);
		query.setParameter("codigo", codigo);
		return query.getSingleResult();
	}
	
	@Transactional
	public List<Area> buscarAreas() {
		String sql = "select a from Area a";
		TypedQuery<Area> query = manager.createQuery(sql, Area.class);
		return query.getResultList();
	}

	@Transactional
	public List<Funcionario> buscarFuncionarios() {
		String sql = "select f from Funcionario f";
		TypedQuery<Funcionario> query = manager.createQuery(sql, Funcionario.class);
		return query.getResultList();
	}

}
