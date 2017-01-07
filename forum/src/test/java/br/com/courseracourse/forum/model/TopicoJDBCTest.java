package br.com.courseracourse.forum.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.courseracourse.forum.factory.ConnectionFactory;

public class TopicoJDBCTest {

	private TopicoDAO topicoDAO;

	@Before
	public void setUpTest() throws Exception {
		topicoDAO = new TopicoDAOJDBC(new ConnectionFactory());

		new DBUnitController("/dados_iniciais_testes.xml");
	}
	
	@Test
	public void recuperaTopicosPorUsuario() throws Exception {
		Usuario usuario = recuperaUsuarioPadrao();
		
		List<Topico> topicosUsuario = topicoDAO.recuperaTopicosPorUsuario(usuario);
		
		List<Topico> listaTopicosCompara = new ArrayList<Topico>(); 
		listaTopicosCompara.add(new Topico(usuario, 0, "TOPICO 1", ""));
		listaTopicosCompara.add(new Topico(usuario, 0, "TOPICO 2", ""));
		
		Assert.assertEquals(topicosUsuario, listaTopicosCompara);
	}
	
	@Test
	public void recuperaTodosTopicos() throws Exception {
		
		List<Topico> topicosUsuario = topicoDAO.recuperaTodosTopicos();

		List<Topico> listaTopicosCompara = criaListaTopicosIniciaisXML();
		
		Assert.assertEquals(topicosUsuario, listaTopicosCompara);
	}
	
	@Test
	public void recuperaTopicoPorIdComComentarios() throws Exception {
		UsuarioDAO usuarioDAO = criaInstanciaUsuarioDAO();
		Usuario usuarioAutor = recuperaUsuarioPadrao();
		Usuario usuarioComentario1 = usuarioDAO.recuperaUsuarioPorLogin("joaojose");
		Usuario usuarioComentario2 = usuarioDAO.recuperaUsuarioPorLogin("maria");
		
		Integer idTopico = 1;
		
		Topico topico = topicoDAO.recuperaTopicoCompletoPorId(idTopico);
		
		Topico topicoCompara = new Topico(usuarioAutor, 0, "TOPICO 1", ""); 
		topicoCompara.adicionaComentario("Comentário no TOPICO 1 do usuário joaojose", usuarioComentario1);
		topicoCompara.adicionaComentario("Outro Comentário no TOPICO 1 do usuário joaojose", usuarioComentario1);
		topicoCompara.adicionaComentario("Comentário no TOPICO 1 do usuário maria", usuarioComentario2);
		
		Assert.assertEquals(topico, topicoCompara);
		Assert.assertEquals(topico.getComentarios(), topicoCompara.getComentarios());
	}
	
	@Test
	public void insereNovoTopico() {
		Usuario usuarioAutor = recuperaUsuarioPadrao();
		
		List<Topico> listaTopicosCompara = criaListaTopicosIniciaisXML();
		
		String tituloNovoTopico = "Título novo tópico";
		String conteudoTopico = "Conteúdo Novo Tópico";
		
		listaTopicosCompara.add(new Topico(usuarioAutor, 0, tituloNovoTopico, conteudoTopico));
		
		topicoDAO.insereNovoTopico(usuarioAutor, tituloNovoTopico, conteudoTopico);
		List<Topico> todosTopicosBD = topicoDAO.recuperaTodosTopicos();
		
		Assert.assertEquals(todosTopicosBD, listaTopicosCompara);
	}

	@Test
	public void incrementaPontuacaoAposInsercaoTopico() {
		Usuario usuarioAutor = recuperaUsuarioPadrao();
		
		Integer pontosAntes = usuarioAutor.getPontos();
		
		new GerenciadorTopico().insereTopico(usuarioAutor.getLogin(), "Tópico Novo", "Conteúdo Novo");
		
		Integer pontosDepoisNovoTopico = recuperaUsuarioPadrao().getPontos();
		
		Assert.assertEquals(Integer.valueOf(pontosDepoisNovoTopico), Integer.valueOf(pontosAntes + 10));
		
	}

	@Test
	public void incrementaPontuacaoAposInsercaoComentario() {
		Usuario usuarioAutor = recuperaUsuarioPadrao();
		
		Integer pontosAntes = usuarioAutor.getPontos();
		
		new GerenciadorTopico().insereNovoComentario(1, usuarioAutor.getLogin(), "Comentário do Teste");
		
		Integer pontosDepoisNovoTopico = recuperaUsuarioPadrao().getPontos();
		
		Assert.assertEquals(Integer.valueOf(pontosDepoisNovoTopico), Integer.valueOf(pontosAntes + 3));
		
	}
	
	private Usuario recuperaUsuarioPadrao() {
		UsuarioDAO usuarioDAO = criaInstanciaUsuarioDAO();
		Usuario usuarioAutor = usuarioDAO.recuperaUsuarioPorLogin("fulanosilva");
		return usuarioAutor;
	}

	private UsuarioDAO criaInstanciaUsuarioDAO() {
		UsuarioDAO usuarioDAO = new UsuarioDAOJDBC(new ConnectionFactory());
		return usuarioDAO;
	}
	

	private List<Topico> criaListaTopicosIniciaisXML() {
		UsuarioDAO usuarioDAO = criaInstanciaUsuarioDAO();
		Usuario usuario1 = recuperaUsuarioPadrao();
		Usuario usuario2 = usuarioDAO.recuperaUsuarioPorLogin("joaojose");
		Usuario usuario3 = usuarioDAO.recuperaUsuarioPorLogin("pedropaulo");

		List<Topico> listaTopicos = new ArrayList<Topico>(); 
		
		listaTopicos.add(new Topico(usuario1, 0, "TOPICO 1", ""));
		listaTopicos.add(new Topico(usuario1, 0, "TOPICO 2", ""));
		listaTopicos.add(new Topico(usuario2, 0, "TOPICO 3 DO JOAO JOSE", ""));
		listaTopicos.add(new Topico(usuario3, 0, "TOPICO 4  DO PEDRO PAULO", ""));
		
		return listaTopicos;
	}
}
