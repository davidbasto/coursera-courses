package com.coursera.cursooo.componentepontuacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntegracaoPlacarArmazenamentoTest {

	private Placar placar;
	private static final String NOME_ARQUIVO_PONTUACAO = "arquivo_pontuacao.txt";
	
	List<String> linhasArquivo = 
			Arrays.asList(  "david|estrela|10",
							"joao|estrela|20",
							"maria|estrela|10",
							"david|moeda|10",
							"maria|moeda|20",
							"joao|topico|10",
							"david|estrela|30",
							"david|estrela|10",
							"joao|moeda|30",
							"joao|topico|30");
	
	@Before
	public void inicializaArquivoEAtributosParaTeste() throws IOException {
		Files.delete(Paths.get(NOME_ARQUIVO_PONTUACAO));
		
		Files.write(Paths.get(NOME_ARQUIVO_PONTUACAO), linhasArquivo, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		
		this.placar = new Placar(new ArmazenamentoEmArquivo(NOME_ARQUIVO_PONTUACAO));
	}
	
	@Test
	public void registraPontuacaoParaUsuarioTest() {
		
		String usuario = "david";
		String tipoPontuacao = "estrela";
		Integer quantidadePontos = 15;
		
		placar.registraPontuacaoParaUsuario(usuario, tipoPontuacao, quantidadePontos);
		
		Pontuacao pontuacao = null;
		
		for (Pontuacao pontuacaoUsuario : placar.recuperaPontuacoesDoUsuario(usuario)) {
			if (pontuacaoUsuario.getTipoPontuacao().equals(tipoPontuacao)) {
				pontuacao = pontuacaoUsuario;
			}
		}
		
		assertNotNull(pontuacao);
		
		assertEquals(new Integer(65), pontuacao.getQuantidadePontos());
	}
	
	@Test
	public void retornaTodosOsPontosDeUsuarioTest() {
		
		Set<Pontuacao> pontuacaoDavidEsperado = new HashSet<>(Arrays.asList(
				new Pontuacao("david", "estrela", 50),
				new Pontuacao("david", "moeda", 10)));
		
		Set<Pontuacao> pontuacaoJoaoEsperado = new HashSet<>(Arrays.asList(
				new Pontuacao("joao", "estrela", 20),
				new Pontuacao("joao", "moeda", 30),
				new Pontuacao("joao", "topico", 40)));
		
		Set<Pontuacao> pontuacaoMariaEsperado = new HashSet<>(Arrays.asList(
				new Pontuacao("maria", "estrela", 10),
				new Pontuacao("maria", "moeda", 20)));
		
		assertEquals(pontuacaoDavidEsperado, placar.recuperaPontuacoesDoUsuario("david"));

		assertEquals(pontuacaoJoaoEsperado, placar.recuperaPontuacoesDoUsuario("joao"));
		
		assertEquals(pontuacaoMariaEsperado, placar.recuperaPontuacoesDoUsuario("maria"));
	}
	
	@Test
	public void rankingDeUsuariosPorPontoTest() {
		
		List<Pontuacao> rankinkEstrelaEsperado = Arrays.asList(
				new Pontuacao("david", "estrela", 50),
				new Pontuacao("joao", "estrela", 20),
				new Pontuacao("maria", "estrela", 10));
		
		List<Pontuacao> rankinkMoedaEsperado = Arrays.asList(
				new Pontuacao("joao", "moeda", 30),
				new Pontuacao("maria", "moeda", 20),
				new Pontuacao("david", "moeda", 10));
		
		List<Pontuacao> rankinkTopicoEsperado = Arrays.asList(
				new Pontuacao("joao", "topico", 40));
		
		assertEquals(rankinkEstrelaEsperado, placar.recuperaRankinkPorTipoPontuacao("estrela"));

		assertEquals(rankinkMoedaEsperado, placar.recuperaRankinkPorTipoPontuacao("moeda"));

		assertEquals(rankinkTopicoEsperado, placar.recuperaRankinkPorTipoPontuacao("topico"));
	}

}
