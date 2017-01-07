package br.com.courseracourse.forum.model;

import java.util.List;

public interface UsuarioDAO {

	public void inserirUsuario(Usuario usuario);

	public Usuario recuperaUsuarioPorLogin(String string);

	public void atualizaPontuacaoUsuario(Usuario usuario);

	public List<Usuario> recuperaRankingUsuarios();
}
