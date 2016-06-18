package com.coursera.cursooo.componentepontuacao;

public class RegistroPontuacaoUsuario {

	private String nomeUsuario;
	
	private String tipoPontuacao;
	
	private Integer pontuacao;

	public RegistroPontuacaoUsuario(String nomeUsuario, String tipoPontuacao, Integer pontuacao) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.tipoPontuacao = tipoPontuacao;
		this.pontuacao = pontuacao;
	}
	
	public RegistroPontuacaoUsuario(String registroFormatado) {
		if(!RegistroPontuacaoUsuario.ehFormatoRegistroValido(registroFormatado)) {
			throw new IllegalArgumentException("Formato do Registro inválido.");
		}
		
		String[] dados = registroFormatado.split("\\|");
		
		this.nomeUsuario = dados[0];
		this.tipoPontuacao = dados[1];
		this.pontuacao = new Integer(dados[2]);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getTipoPontuacao() {
		return tipoPontuacao;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public String getRegistroFormatado() {
		return String.join("|", nomeUsuario, tipoPontuacao, pontuacao.toString());
	}
	
	public static boolean ehFormatoRegistroValido(String registroFormatado) {
		return registroFormatado.matches("\\w+\\|\\w+\\|[0-9]+");
	}
}
