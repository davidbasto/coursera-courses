package com.coursera.cursooo.componentepontuacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.coursera.cursooo.componentepontuacao.mocks.MockArmazenamento;

public class PlacarTest {
	
	private MockArmazenamento armazenamento;
	private Placar placar;

	@Before
	public void criaPlacarEArmazenamento() {
		armazenamento = new MockArmazenamento();
		
		placar = new Placar(armazenamento);
	}

	@Test
	public void registraPontuacaoParaUsuarioTest() {
		
		String usuario = "david";
		String tipoPontuacao = "estrela";
		
		placar.registraPontuacaoParaUsuario(usuario , tipoPontuacao , 10);
		
		armazenamento.verificaExecucaoRegistroDePontuacao();
		armazenamento.verificaParametrosRegistro(usuario, tipoPontuacao, 10);
	}
	
	@Test
	public void retornaTodosOsPontosDeUsuarioTest() {
		
		Pontuacao pontuacaoEstrela = new Pontuacao("david", "estrela", 30);

		Pontuacao pontuacaoMoeda = new Pontuacao("david", "moeda", 20);
		
		Set<Pontuacao> conjuntoPontuacoes = placar.recuperaPontuacoesDoUsuario("david");
		
		assertEquals(2, conjuntoPontuacoes.size());
		
		assertTrue(conjuntoPontuacoes.contains(pontuacaoEstrela));
		assertTrue(conjuntoPontuacoes.contains(pontuacaoMoeda));
		
		armazenamento.verificaChamadaRecuperaTiposPontoPorUsuario();
		armazenamento.verificaChamadaRecuperaPontuacaoDeUsuario();
	}
	
	@Test
	public void rankingDeUsuariosPorPontoTest() {
		
		String tipoPonto = "estrela";

		Pontuacao pontuacaoJoao = new Pontuacao("joao", tipoPonto, 20);
		
		Pontuacao pontuacaoDavid = new Pontuacao("david", tipoPonto , 30);
		
		Pontuacao pontuacaoMaria = new Pontuacao("maria", tipoPonto, 10);
		
		List<Pontuacao> rankingEsperado = Arrays.asList(pontuacaoDavid, pontuacaoJoao, pontuacaoMaria);
		
		assertEquals(rankingEsperado, placar.recuperaRankinkPorTipoPontuacao(tipoPonto));
		
		armazenamento.verificaChamadaRecuperaUsuariosPorTipoPonto();
		armazenamento.verificaChamadaRecuperaPontuacaoDeUsuario();
	}
}
