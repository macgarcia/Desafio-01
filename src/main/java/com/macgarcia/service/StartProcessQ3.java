package com.macgarcia.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import com.macgarcia.model.Area;
import com.macgarcia.model.Funcionario;

@Singleton
public class StartProcessQ3 extends StartProcessModel {

	private Map<String, List<Funcionario>> funcionarioPorArea;
	private Area areaAtual;
	private Integer maior, menor;
	private boolean first = true;
	private String msgMaior, msgMenor;

	public String questao3() {
		var dados = buscarFuncionarios();
		this.separarPorArea(dados);
		this.contarFuncionarios();
		this.first = true;
		this.formatarMensagem();
		return msg;
	}

	private void separarPorArea(List<Funcionario> list) {
		funcionarioPorArea = list.stream().collect(Collectors.groupingBy(e -> e.getAreaObj().getCodigo()));
	}

	private void contarFuncionarios() {
		for (var key : funcionarioPorArea.entrySet()) {
			int count = 0;
			areaAtual = buscarArea(key.getKey().toString());
			count = key.getValue().size();
			this.montarMensagem(count);
		}
	}

	private void montarMensagem(int count) {
		if (this.first) {
			this.msgMenor = "least_employees|" + areaAtual.getNome() + "|" + count;
			this.msgMaior = "most_employees|" + areaAtual.getNome() + "|" + count;
			this.maior = count;
			this.menor = count;
			this.first = false;
			return;
		}
		
		if (count < this.menor) {
			this.msgMenor = "least_employees|"+ areaAtual.getNome() + "|" + count;
			this.menor = count;
		} else if (count == this.menor) {
			this.msgMenor = this.msgMenor + "\n" + "least_employees|"+ areaAtual.getNome() + "|" + count;
		}
		
		if (count > this.maior) {
			this.msgMaior = "most_employees|"+ areaAtual.getNome() + "|" + count;
			this.maior = count;
		} else if (count == this.maior) {
			this.msgMaior = this.msgMaior + "\n" + "most_employees|"+ areaAtual.getNome() + "|" + count;
		}
	}
	
	private void formatarMensagem() {
		msg = this.msgMenor + "\n" + this.msgMaior;
		msg = msg.replace("null", "").trim();
	}

}
