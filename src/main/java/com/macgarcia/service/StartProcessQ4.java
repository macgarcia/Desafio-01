package com.macgarcia.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.macgarcia.model.Funcionario;

@Singleton
public class StartProcessQ4 extends StartProcessModel {

	@PersistenceContext
	EntityManager manager;

	private Map<String, List<Funcionario>> dadosSeparados;

	public String questao4() {
		var dados = buscarFuncionarios();
		this.separaDadosPorSobrenome(dados);
		this.processar();
		return msg;

	}

	private void separaDadosPorSobrenome(List<Funcionario> list) {
		dadosSeparados = list.stream().collect(Collectors.groupingBy(e -> e.getSobrenome()));
	}

	private void processar() {
		for (var key : dadosSeparados.entrySet()) {
			int count = 0;
			count = key.getValue().size();
			if (count > 1) {
				selecionarMaiores(key.getValue());
				this.montarMensagem();
			}
		}
		msg = msg.replace("null", "").trim();
	}

	private void montarMensagem() {
		msg = msg + "\n" + maiores.stream().map(e -> "last_name_max|" + e.getSobrenome() + "|" + e.getNome() + " " + e.getSobrenome()
				+ "|" + e.getSalario()).collect(Collectors.joining("\n"));
	}

}
