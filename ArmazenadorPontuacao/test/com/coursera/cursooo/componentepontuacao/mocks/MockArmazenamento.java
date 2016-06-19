package com.coursera.cursooo.componentepontuacao.mocks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.coursera.cursooo.componentepontuacao.Armazenamento;

import static org.junit.Assert.*;

public class MockArmazenamento implements Armazenamento {

	private String usuarioRegistrado = null;
	
	private String tipoPontuacaoRegistrado = null;
	
	private Integer qtdePontosRegistrada = null;

	private boolean pontosRegistrados = false;

	private boolean tiposPontosUsuarioRetornados = false;

	private boolean pontuacaoUsuarioRetornados = false;

	private boolean usuariosPorTipoPontoRecuperados = false;
	
	@Override
	public void registraPontuacaoDeUsuario(String usuario, String tipoPontuacao, Integer quantidade) {
		pontosRegistrados = true;
		usuarioRegistrado = usuario;
		tipoPontuacaoRegistrado = tipoPontuacao;
		qtdePontosRegistrada = quantidade;
	}

	@Override
	public Integer recuperaPontuacaoDeUsuario(String usuario, String tipoPontuacao) {
		
		pontuacaoUsuarioRetornados = true;
		
		if(usuario.equals("david")) {
			if(tipoPontuacao.equals("estrela")) {
				return 30;
			}
			else if(tipoPontuacao.equals("moeda")) {
				return 20;
			}
		}

		if(usuario.equals("joao")) {
			if(tipoPontuacao.equals("estrela")) {
				return 20;
			}
		}

		if(usuario.equals("maria")) {
			if(tipoPontuacao.equals("estrela")) {
				return 10;
			}
		}
		
		return 0;
	}

	@Override
	public Set<String> recuperaUsuariosPorTipoPonto(String tipoPonto) {
		this.usuariosPorTipoPontoRecuperados = true;
		if(tipoPonto.equals("estrela")) {
			return new HashSet<>(Arrays.asList("david", "joao", "maria"));
		}
		
		return null;
	}

	@Override
	public Set<String> recuperaTiposPontoPorUsuario(String usuario) {
		
		this.tiposPontosUsuarioRetornados = true;
		
		Set<String> retorno = new HashSet<>();
		
		if(usuario.equals("david")) {
			retorno.add("estrela");
			retorno.add("moeda");
		}
		
		return retorno;
	}

	public void verificaExecucaoRegistroDePontuacao() {
		assertTrue(this.pontosRegistrados );
	}

	public void verificaParametrosRegistro(String usuario, String tipoPontuacao, Integer qtdePontos) {
		assertEquals(usuario, usuarioRegistrado);
		assertEquals(tipoPontuacao, tipoPontuacaoRegistrado);
		assertEquals(qtdePontos, qtdePontosRegistrada);
	}
	
	public void verificaChamadaRecuperaTiposPontoPorUsuario() {
		assertTrue(this.tiposPontosUsuarioRetornados);
	}
	
	public void verificaChamadaRecuperaPontuacaoDeUsuario() {
		assertTrue(this.pontuacaoUsuarioRetornados);
	}
	
	public void verificaChamadaRecuperaUsuariosPorTipoPonto() {
		assertTrue(this.usuariosPorTipoPontoRecuperados);
	}

}
