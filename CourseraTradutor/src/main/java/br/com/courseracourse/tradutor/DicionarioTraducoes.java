package br.com.courseracourse.tradutor;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class DicionarioTraducoes {

	private Map<String, String> verbetes = new TreeMap<>((s1, s2) -> s1.compareToIgnoreCase(s2));
			
	public void adicionaVerbete(String palavra, String traducao) {
		Objects.requireNonNull(palavra, "Palavra não pode ser nula");
		Objects.requireNonNull(traducao, "Tradução não pode ser nula");
		
		verbetes.put(palavra, traducao);
	}

	public String recuperaTraducao(String palavra)  {
		Objects.requireNonNull(palavra, "Palavra não pode ser nula");
		// Retorna a tradução da palavra armazenada no Map ou retorna a própria palavra, caso não encontre.
		return Optional.ofNullable(verbetes.get(palavra)).orElse(palavra);
	}
	
	public Boolean possuiTraducao(String palavra) {
		return verbetes.containsKey(palavra);
	}
	
	public Integer quantidadeVerbetes() {
		return verbetes.size();
	}
}
