package br.com.courseracourse.forum.model;

import java.util.List;

import br.com.courseracourse.forum.factory.ConnectionFactory;

public class GerenciadorTopico {

	private static final Integer PONTOS_POR_NOVO_COMENTARIO = 3;
	private static final Integer PONTOS_POR_NOVO_TOPICO = 10;
	private TopicoDAO topicoDAO;
	private UsuarioDAO usuarioDAO;
			
	public GerenciadorTopico() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		topicoDAO = new TopicoDAOJDBC(connectionFactory);
		usuarioDAO = new UsuarioDAOJDBC(connectionFactory);
	}
	
	public Topico recuperaTopicoPorId(Integer idTopico) {
		Topico topico = topicoDAO.recuperaTopicoCompletoPorId(idTopico);
		return topico;
	}

	public List<Topico> recuperaTodosTopicos() {
		return topicoDAO.recuperaTodosTopicos();
	}

	public void insereNovoComentario(Integer idTopico, String loginUsuario, String conteudoComentario) {

		Topico topico = this.recuperaTopicoPorId(idTopico);
		Usuario usuario = usuarioDAO.recuperaUsuarioPorLogin(loginUsuario);
		
		usuario.adicionaPontos(PONTOS_POR_NOVO_COMENTARIO);
		
		topico.adicionaComentario(conteudoComentario, usuario);
		topicoDAO.persisteComentariosTopico(topico);
		usuarioDAO.atualizaPontuacaoUsuario(usuario);
	}

	public void insereTopico(String loginUsuario, String tituloTopico, String conteudoTopico) {

		Usuario usuario = usuarioDAO.recuperaUsuarioPorLogin(loginUsuario);
		
		usuario.adicionaPontos(PONTOS_POR_NOVO_TOPICO);
		
		topicoDAO.insereNovoTopico(usuario, tituloTopico, conteudoTopico);
		usuarioDAO.atualizaPontuacaoUsuario(usuario);
	}
}
