package br.com.courseracourse.forum.model;

import br.com.courseracourse.forum.exception.UsuarioNaoAutenticadoException;

public interface Autenticador {
	public String autenticar(String login, String senha) throws UsuarioNaoAutenticadoException;
}
