package com.coursera.cursooo.componentepontuacao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Placar {

	private Armazenamento armazenamento;

	public Placar(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registraPontuacaoParaUsuario(String usuario, String tipoPontuacao, Integer quantidadePontos) {
		armazenamento.registraPontuacaoDeUsuario(usuario, tipoPontuacao, quantidadePontos);
	}

	public Set<Pontuacao> recuperaPontuacoesDoUsuario(String usuario) {
		
		Set<Pontuacao> conjuntoPontuacoes = new HashSet<>();
		
		armazenamento.recuperaTiposPontoPorUsuario(usuario)
				.stream()
				.map(p -> new Pontuacao(usuario, p, armazenamento.recuperaPontuacaoDeUsuario(usuario, p)))
				.filter(pont -> pont.getQuantidadePontos() > 0)
				.forEach(conjuntoPontuacoes::add);
		
		return conjuntoPontuacoes;
	}

	public List<Pontuacao> recuperaRankinkPorTipoPontuacao(String tipoPonto) {
		
		List<Pontuacao> listaRanking = armazenamento.recuperaUsuariosPorTipoPonto(tipoPonto)
				.stream()
				.map(p -> new Pontuacao(p, tipoPonto, armazenamento.recuperaPontuacaoDeUsuario(p, tipoPonto)))
				.collect(Collectors.toList());
		
		listaRanking.sort((p1, p2) -> p1.getQuantidadePontos().compareTo(p2.getQuantidadePontos()) * -1);
		
		return listaRanking;
		
	}

}
