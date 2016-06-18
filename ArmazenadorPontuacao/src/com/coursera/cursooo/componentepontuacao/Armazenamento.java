package com.coursera.cursooo.componentepontuacao;

import java.util.Set;

public interface Armazenamento {

	void registraPontuacaoDeUsuario(String usuario, String tipoPontuacao, Integer quantidade);

	Integer recuperaPontuacaoDeUsuario(String usuario, String tipoPontuacao);

	Set<String> recuperaUsuariosPorTipoPonto(String tipoPonto);

	Set<String> recuperaTiposPontoPorUsuario(String usuario);

}
