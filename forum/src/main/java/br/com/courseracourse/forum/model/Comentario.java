package br.com.courseracourse.forum.model;

public class Comentario {
	
	private Integer id;
	
	private Topico topico;
	
	private Usuario usuario;
	
	private String textoComentario;
	
	public Comentario(Topico topico, Usuario usuario, String textoComentario) {
		this(0, topico, usuario, textoComentario);
	}

	public Comentario(Integer id, Topico topico, Usuario usuario, String textoComentario) {
		super();
		this.id = id;
		this.topico = topico;
		this.usuario = usuario;
		this.textoComentario = textoComentario;
	}

	public Integer getId() {
		return id;
	}

	public Topico getTopico() {
		return topico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((textoComentario == null) ? 0 : textoComentario.hashCode());
		result = prime * result + ((topico == null) ? 0 : topico.hashCode());
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
		Comentario other = (Comentario) obj;
		if (textoComentario == null) {
			if (other.textoComentario != null)
				return false;
		} else if (!textoComentario.equals(other.textoComentario))
			return false;
		if (topico == null) {
			if (other.topico != null)
				return false;
		} else if (!topico.equals(other.topico))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
