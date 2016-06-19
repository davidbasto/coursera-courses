package com.coursera.cursooo.componentepontuacao;

public class Pontuacao {
	
	private String usuario;
	
	private String tipoPontuacao;

	private Integer qtdePontos;

	public Pontuacao(String usuario, String tipoPontuacao, Integer qtdePontos) {
		this.usuario = usuario;
		this.tipoPontuacao = tipoPontuacao;
		this.qtdePontos = qtdePontos;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getTipoPontuacao() {
		return this.tipoPontuacao;
	}
	
	public Integer getQuantidadePontos() {
		return this.qtdePontos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qtdePontos == null) ? 0 : qtdePontos.hashCode());
		result = prime * result + ((tipoPontuacao == null) ? 0 : tipoPontuacao.hashCode());
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
		Pontuacao other = (Pontuacao) obj;
		if (qtdePontos == null) {
			if (other.qtdePontos != null)
				return false;
		} else if (!qtdePontos.equals(other.qtdePontos))
			return false;
		if (tipoPontuacao == null) {
			if (other.tipoPontuacao != null)
				return false;
		} else if (!tipoPontuacao.equals(other.tipoPontuacao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Usuário " + usuario + " possui " + qtdePontos + " pontos do tipo " + tipoPontuacao;
	}
	
}
