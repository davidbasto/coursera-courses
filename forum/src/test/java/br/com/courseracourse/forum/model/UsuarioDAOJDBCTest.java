package br.com.courseracourse.forum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.courseracourse.forum.factory.ConnectionFactory;
import br.com.courseracourse.forum.model.Usuario;
import br.com.courseracourse.forum.model.UsuarioDAO;
import br.com.courseracourse.forum.model.UsuarioDAOJDBC;

public class UsuarioDAOJDBCTest {

	private UsuarioDAO usuarioDAO;

	@Before
	public void setUpTest() throws Exception {
		new DBUnitController("/dados_iniciais_testes.xml");
		
		usuarioDAO = new UsuarioDAOJDBC(new ConnectionFactory());
	}
	
	@Test
	public void insereUsuarioTest() throws Exception {

		try{
			usuarioDAO.recuperaUsuarioPorLogin("ciclanosouza");
			Assert.fail("O usuário não deveria ter sido encontrado");
		}
		catch(RuntimeException e) {
			
		}
		
		Usuario usuario = new Usuario("ciclanosouza", "ciclanosouza@gmail.com", "Ciclano Souza", "456", 123);
		usuarioDAO.inserirUsuario(usuario);

		Usuario usuarioDepoisInsercao = usuarioDAO.recuperaUsuarioPorLogin("ciclanosouza");
		Assert.assertEquals(usuario, usuarioDepoisInsercao);
	}
	
	@Test
	public void verificaRanking() {

		List<Usuario> expectedRanking = new ArrayList<>();
		Collections.addAll(expectedRanking, 
				new Usuario("fulanosilva", "fulanosilva@gmail.com", "Fulano da Silva", "123", 100),
				new Usuario("maria", "mt@gmail.com", "Maria Tereza", "567", 200),
				new Usuario("pedropaulo", "pedropaulo@gmail.com", "Pedro Paulo", "67890", 140),
				new Usuario("joaojose", "joaojose@gmail.com", "Joao Jose", "123456", 150)
			);
		
		//Ordenando a lista de resultado esperado de acordo com o critério de pontos.
		expectedRanking.sort((o1, o2) -> o1.getPontos().compareTo(o2.getPontos()) * -1);
		
		List<Usuario> rankingUsuarios = usuarioDAO.recuperaRankingUsuarios();
		
		Assert.assertEquals(expectedRanking, rankingUsuarios);
	}

}
