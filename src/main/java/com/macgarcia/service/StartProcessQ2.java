package com.macgarcia.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import com.macgarcia.model.Area;
import com.macgarcia.model.Funcionario;

@Singleton
public class StartProcessQ2 extends StartProcessModel {

//	@PersistenceContext
//	EntityManager manager;

	private Map<String, List<Funcionario>> funcionarioPorArea;
	private Area areaAtual;

	public String questao2() {
		msg = "";
		var dados = buscarFuncionarios();
		this.separarPorArea(dados);
		this.processar();
		return msg;
	}

	private void separarPorArea(List<Funcionario> dados) {
		this.funcionarioPorArea = dados.stream().collect(Collectors.groupingBy(e -> e.getAreaObj().getCodigo()));
	}

	private void processar() {
		for (var key : funcionarioPorArea.entrySet()) {
			areaAtual = buscarArea(key.getKey().toString());
			var list = key.getValue();
			selecionarMaiores(list);
			selecionarMenores(list);
			selecionarMedia(list);
			this.montarMensagem();
		}
	}

	private void montarMensagem() {
		msg = msg + "\n" + maiores.stream().map(e -> "area_max|" + this.areaAtual.getNome() + "|" + e.getNome() + " "
				+ e.getSobrenome() + "|" + e.getSalario()).collect(Collectors.joining("\n"));

		msg = msg + "\n" + menores.stream().map(e -> "area_min|" + this.areaAtual.getNome() + "|" + e.getNome() + " "
				+ e.getSobrenome() + "|" + e.getSalario()).collect(Collectors.joining("\n"));

		msg = msg + "\n" + "area_avg|" + this.areaAtual.getNome() + "|" + media;
		
		msg = msg.replace("null", "").trim();
	}

}
