package br.com.courseracourse.tradutor.teste.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.courseracourse.tradutor.DicionarioTraducoes;
import br.com.courseracourse.tradutor.exception.TraducaoNaoEncontradaException;

public class DicionarioTraducoesTest {

	private DicionarioTraducoes dicionario;
	
	@Before
	public void inicializaDicionario() {
		dicionario = new DicionarioTraducoes();
		dicionario.adicionaVerbete("teste", "test");
		dicionario.adicionaVerbete("Cachorro", "Dog");
		dicionario.adicionaVerbete("Gato", "Cat");
	}
	
	@Test
	public void testaAdicaoVerbete() {
		String palavra = "Macaco";
		String traducao = "Monkey";
		
		assertEquals(new Integer(3), dicionario.quantidadeVerbetes());
		assertFalse(dicionario.possuiTraducao(palavra));
		
		dicionario.adicionaVerbete(palavra, traducao);
		assertEquals(new Integer(4), dicionario.quantidadeVerbetes());
		assertTrue(dicionario.possuiTraducao(palavra));
		assertEquals(traducao, dicionario.recuperaTraducao(palavra));
	}
	
	@Test
	public void testaRecuperaVerbete() {
		String palavra = "Cachorro";
		String traducao = "Dog";
		
		assertTrue(dicionario.possuiTraducao(palavra));
		assertEquals(traducao, dicionario.recuperaTraducao(palavra));
	}
	
	@Test(expected=TraducaoNaoEncontradaException.class)
	public void testaLancaExcecaoVerbeteInesistente() {
		String palavra = "Macaco";
		
		assertFalse(dicionario.possuiTraducao(palavra));
		dicionario.recuperaTraducao(palavra);
	}
	
	@Test
	public void testaCaseInsensitiveness() {
		String palavra = "CACHORRO";
		String traducao = "Dog";
		
		assertTrue(dicionario.possuiTraducao(palavra));
		assertEquals(traducao, dicionario.recuperaTraducao(palavra));
	}

}
