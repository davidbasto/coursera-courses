package br.com.courseracourse.forum.model;

import java.util.List;

public interface TopicoDAO {

	public List<Topico> recuperaTopicosPorUsuario(Usuario usuario) ;

	public Topico recuperaTopicoCompletoPorId(Integer idTopico);

	public void persisteComentariosTopico(Topico topico);

	public List<Topico> recuperaTodosTopicos();

	public void insereNovoTopico(Usuario usuario, String tituloTopico, String conteudoTopico);

}
