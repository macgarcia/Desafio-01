package com.macgarcia.service;

import java.util.stream.Collectors;

import javax.inject.Singleton;

@Singleton
public class StartProcessQ1 extends StartProcessModel {

	public String questao1() {
		var dados = buscarFuncionarios();

		selecionarMaiores(dados);
		selecionarMenores(dados);
		selecionarMedia(dados);

		msg = maiores.stream().map(e -> "global_max|" + e.getNome() + " " + e.getSobrenome() + "|" + e.getSalario())
				.collect(Collectors.joining("\n"));

		msg = msg + "\n"
				+ menores.stream().map(e -> "global_min|" + e.getNome() + " " + e.getSobrenome() + "|" + e.getSalario())
						.collect(Collectors.joining("\n"));

		msg = msg + "\n" + "global_avg|" + media;

		return msg;
	}
}
