package br.com.courseracourse.forum.model;

import java.util.ArrayList;
import java.util.List;

public class Topico {
	
	private Integer id;
	
	private Usuario usuario;
	
	private String titulo;
	
	private String conteudo;
	
	private List<Comentario> comentarios = new ArrayList<>();

	public Topico(Usuario usuario, int id, String titulo, String conteudo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.titulo = titulo;
		this.conteudo = conteudo;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getConteudo() {
		return conteudo;
	}
	
	public List<Comentario> getComentarios() {
		return new ArrayList<>(this.comentarios);
	}

	public void adicionaComentario(String texto, Usuario usuario) {
		this.comentarios.add(new Comentario(this, usuario, texto));
	}

	public void adicionaComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}
	
	@Override
	public String toString() {
		return "Topico [usuario=" + usuario + ", titulo=" + titulo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
