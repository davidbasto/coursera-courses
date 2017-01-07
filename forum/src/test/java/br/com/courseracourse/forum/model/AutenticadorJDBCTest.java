package br.com.courseracourse.forum.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.courseracourse.forum.exception.UsuarioNaoAutenticadoException;
import br.com.courseracourse.forum.model.Autenticador;
import br.com.courseracourse.forum.model.AutenticadorJDBC;

public class AutenticadorJDBCTest {
	
	private Autenticador autenticador;
	
	@Before
	public void setUpTeste() throws Exception {
		autenticador = new AutenticadorJDBC();
		new DBUnitController("/dados_iniciais_testes.xml");
	}
	
	@Test
	public void autenticaUsuarioComSucesso() throws UsuarioNaoAutenticadoException {
		String nome = autenticador.autenticar("fulanosilva", "123");
		Assert.assertEquals(nome, "Fulano da Silva");
	}
	
	@Test(expected=UsuarioNaoAutenticadoException.class)
	public void autenticaUsuarioNaoEncontrado() throws UsuarioNaoAutenticadoException {
		String nome = autenticador.autenticar("fulanosilva", "567");
		Assert.assertEquals(nome, "Fulano da Silva");
	}
}
