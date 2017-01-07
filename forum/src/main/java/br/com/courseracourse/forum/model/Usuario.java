package br.com.courseracourse.forum.model;

public class Usuario {
	
	private String login;
	private String email;
	private String nome;
	private String senha;
	private Integer pontos;
	
	public Usuario(String login, String email, String nome, String senha, Integer pontos) {
		super();
		this.login = login;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.pontos = pontos;
	}

	public Usuario(String login, String email, String nome, String senha) {
		this(login, email, nome, senha, 0);
	}

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public Integer getPontos() {
		return pontos;
	}


	public void adicionaPontos(Integer incrementoPontos) {
		this.pontos += incrementoPontos;
	}
	
	@Override
	public String toString() {
		return "Usuario [login=" + login + ", pontos=" + pontos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}	
	
}
