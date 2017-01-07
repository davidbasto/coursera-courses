package br.com.courseracourse.forum.model;

import java.util.List;

import br.com.courseracourse.forum.factory.ConnectionFactory;

public class GerenciadorUsuario {

	private UsuarioDAO usuarioDAO;
	private TopicoDAO topicoDAO;
			
	public GerenciadorUsuario() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		usuarioDAO = new UsuarioDAOJDBC(connectionFactory);
		topicoDAO = new TopicoDAOJDBC(connectionFactory);
	}
	
	public void cadastraUsuario(String login, String nome, String email, String senha) {
		usuarioDAO.inserirUsuario(new Usuario(login, email, nome, senha));
	}
	
	public List<Topico> recuperaTopicosPorLoginUsuario(String login) {
		Usuario usuario = usuarioDAO.recuperaUsuarioPorLogin(login);
		return topicoDAO.recuperaTopicosPorUsuario(usuario);
	}
	
	public List<Usuario> recuperaRankingUsuarios() {
		return usuarioDAO.recuperaRankingUsuarios();
	}
}
