package com.coursera.cursooo.componentepontuacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArmazenamentoEmArquivo implements Armazenamento {

	private final String nomeArquivoPontuacao;

	public ArmazenamentoEmArquivo(String nomeArquivoPontuacao) {
		this.nomeArquivoPontuacao = nomeArquivoPontuacao;
	}
	
	@Override
	public void registraPontuacaoDeUsuario(String usuario, String tipoPontuacao, Integer quantidade) {

		RegistroPontuacaoUsuario registro = new RegistroPontuacaoUsuario(usuario, tipoPontuacao, quantidade);
		
		try {
			Files.write(getPathPeloNomeArquivo(), ("\n" + registro.getRegistroFormatado()).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Integer recuperaPontuacaoDeUsuario(String usuario, String tipoPontuacao) {
		
		int somatorioPontos = 0;
		
		try {
			somatorioPontos = getStreamRegistrosDeArquivo()
					.filter(rec -> rec.getNomeUsuario().equals(usuario) && rec.getTipoPontuacao().equals(tipoPontuacao))
					.collect(Collectors.summingInt(RegistroPontuacaoUsuario::getPontuacao));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return somatorioPontos;
	}

	@Override
	public Set<String> recuperaUsuariosPorTipoPonto(String tipoPonto) {
		Set<String> usuariosTipoPontoRetorno = new HashSet<>();
		
		try {
			getStreamRegistrosDeArquivo()
					.filter(rec -> rec.getTipoPontuacao().equals(tipoPonto))
					.forEach(rec -> usuariosTipoPontoRetorno.add(rec.getNomeUsuario()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuariosTipoPontoRetorno;
	}

	@Override
	public Set<String> recuperaTiposPontoPorUsuario(String usuario) {
		
		Set<String> tiposPontoUsuarioRetorno = new HashSet<>();
		
		try {
			getStreamRegistrosDeArquivo()
					.filter(rec -> rec.getNomeUsuario().equals(usuario))
					.forEach(rec -> tiposPontoUsuarioRetorno.add(rec.getTipoPontuacao()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tiposPontoUsuarioRetorno;
	}

	private Path getPathPeloNomeArquivo() {
		return Paths.get(nomeArquivoPontuacao);
	}

	private Stream<RegistroPontuacaoUsuario> getStreamRegistrosDeArquivo() throws IOException {
		return Files.lines(getPathPeloNomeArquivo())
				.filter(p -> RegistroPontuacaoUsuario.ehFormatoRegistroValido(p))
				.map(p -> new RegistroPontuacaoUsuario(p));
	}

}
