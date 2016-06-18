package com.coursera.cursooo.componentepontuacao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ArmazenadorEmArquivoTest {

	private static final String NOME_ARQUIVO_PONTUACAO = "arquivo_pontuacao.txt";
	private Armazenamento armazenamento;
	
	@Before
	public void excluiArquivoPontuacao() {
		try {
			Files.delete(Paths.get(NOME_ARQUIVO_PONTUACAO));
		}
		catch (IOException e) {
			
		}
	}
	
	@Before
	public void criaArmazenamento() {
		armazenamento = new ArmazenamentoEmArquivo(NOME_ARQUIVO_PONTUACAO);
	}
	
	@Test
	public void armazenaPontosDeUmUsuario() {
		criaArmazenamento();
		
		String usuario = "david";
		String tipoPontuacao = "estrela";
		Integer quantidade = 10;
		
		armazenamento.registraPontuacaoDeUsuario(usuario, tipoPontuacao, quantidade);
		
		assertEquals(quantidade, armazenamento.recuperaPontuacaoDeUsuario(usuario, tipoPontuacao));
	}
	
	@Test
	public void armazenaPontosDeMaisDeUmUsuario() {
		criaArmazenamento();
		
		String usuario1 = "david";
		String tipoPontuacao1 = "estrela";
		Integer quantidade1 = 10;

		String usuario2 = "joao";
		String tipoPontuacao2 = "estrela";
		Integer quantidade2 = 20;
		
		armazenamento.registraPontuacaoDeUsuario(usuario1, tipoPontuacao1, quantidade1);
		
		assertEquals(quantidade1, armazenamento.recuperaPontuacaoDeUsuario(usuario1, tipoPontuacao1));
		
		armazenamento.registraPontuacaoDeUsuario(usuario2, tipoPontuacao2, quantidade2);
		
		assertEquals(quantidade2, armazenamento.recuperaPontuacaoDeUsuario(usuario2, tipoPontuacao2));
	}
	
	@Test
	public void recuperaTiposDiferentesPontosMesmoUsuario() {
		criaEPopulaArmazenamento();
		
		assertEquals(30, armazenamento.recuperaPontuacaoDeUsuario("david", "estrela").intValue());
		
		assertEquals(10, armazenamento.recuperaPontuacaoDeUsuario("david", "moeda").intValue());
	}

	@Test
	public void recuperaTiposDiferentesPontosUsuariosDiferentes() {
		criaEPopulaArmazenamento();

		assertEquals(30, armazenamento.recuperaPontuacaoDeUsuario("david", "estrela").intValue());
		
		assertEquals(20, armazenamento.recuperaPontuacaoDeUsuario("joao", "topico").intValue());
	}
	
	@Test
	public void naoOcorreErroArquivoFormatoIncorreto() throws IOException {
		criaArmazenamento();
		
		Files.write(Paths.get(NOME_ARQUIVO_PONTUACAO), ("\n" + "david|estrela|STRING_NAO_EH_PONTO_CAUSA_ERRO").getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		
		armazenamento.recuperaPontuacaoDeUsuario("david", "estrela");
	}
	
	@Test
	public void recuperaQuantidadeUsuariosDiferentesPorPonto() {
		criaEPopulaArmazenamento();
		
		Set<String> usuariosEstrela = new HashSet<>();
		usuariosEstrela.addAll(Arrays.asList("david", "joao", "maria"));
		
		Set<String> usuariosTopico= new HashSet<>();
		usuariosTopico.addAll(Arrays.asList("joao"));

		Set<String> usuariosMoeda = new HashSet<>();
		usuariosMoeda.addAll(Arrays.asList("david", "joao", "maria"));
		
		assertEquals(usuariosEstrela, armazenamento.recuperaUsuariosPorTipoPonto("estrela"));

		assertEquals(usuariosTopico, armazenamento.recuperaUsuariosPorTipoPonto("topico"));

		assertEquals(usuariosMoeda, armazenamento.recuperaUsuariosPorTipoPonto("moeda"));
	}
	
	@Test
	public void recuperaTiposPontosDiferentesParaUsuario() {
		criaEPopulaArmazenamento();
		
		Set<String> tiposPontosDavid = new HashSet<>(Arrays.asList("estrela", "moeda"));

		Set<String> tiposPontosJoao = new HashSet<>(Arrays.asList("estrela", "moeda", "topico"));
		
		Set<String> tiposPontosMaria = new HashSet<>(Arrays.asList("estrela", "moeda"));
		
		assertEquals(tiposPontosDavid, armazenamento.recuperaTiposPontoPorUsuario("david"));

		assertEquals(tiposPontosJoao, armazenamento.recuperaTiposPontoPorUsuario("joao"));

		assertEquals(tiposPontosMaria, armazenamento.recuperaTiposPontoPorUsuario("maria"));
	}

	private void criaEPopulaArmazenamento() {
		criaArmazenamento();
		
		populaArquivoArmazenamento();
	}
	
	private void populaArquivoArmazenamento() {
		armazenamento.registraPontuacaoDeUsuario("david", "estrela", 10);
		armazenamento.registraPontuacaoDeUsuario("joao", "estrela", 10);
		armazenamento.registraPontuacaoDeUsuario("maria", "estrela", 10);
		armazenamento.registraPontuacaoDeUsuario("david", "moeda", 10);
		armazenamento.registraPontuacaoDeUsuario("maria", "moeda", 10);
		armazenamento.registraPontuacaoDeUsuario("joao", "topico", 10);
		armazenamento.registraPontuacaoDeUsuario("david", "estrela", 10);
		armazenamento.registraPontuacaoDeUsuario("david", "estrela", 10);
		armazenamento.registraPontuacaoDeUsuario("joao", "moeda", 10);
		armazenamento.registraPontuacaoDeUsuario("joao", "topico", 10);
	}
}
