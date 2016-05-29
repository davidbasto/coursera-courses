import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConversorCamelCaseTeste {

	private ConversorCamelCase conversor;
	
	@Before
	public void inicializaConversor() {
		conversor = new ConversorCamelCaseIteraCaracteres();
	}

	@Test
	public void aceitaStringLetrasENumerosTest() {
		String comCaracterEspecial = "nomeComposto";
		
		conversor.converterCamelCase(comCaracterEspecial);
	}
	
	@Test
	public void caracteresInvalidosNaoPermitidosTest() {
		
		List<String> stringCaracterEspecialList = new ArrayList<String>();
		stringCaracterEspecialList.add("nome@Composto");
		stringCaracterEspecialList.add("nome#Composto");
		stringCaracterEspecialList.add("%nomeComposto");
		stringCaracterEspecialList.add("&nomeComposto");
		stringCaracterEspecialList.add("nomeComposto*");
		stringCaracterEspecialList.add("&nomeComposto(");
		
		for (String string : stringCaracterEspecialList) {
			/*
			 * Validando que para todas as strings inválidas da lista, a exceção será lançada.
			 */
			try {
				conversor.converterCamelCase(string);
				fail("Não lançou exceção esperada.");
			}
			catch(StringInvalidaException e) {
				
			}
		}
	}
	
	@Test
	public void somenteUmaPalavraNaStringTest() {
		String stringOriginal = "nome";
		List<String> retorno = conversor.converterCamelCase(stringOriginal);
		assertEquals(Arrays.asList(stringOriginal), retorno);
	}

	@Test(expected=StringInvalidaException.class)
	public void stringIniciadaComNumerosInvalidaTest() {
		String stringIniciadaComNumeros = "10Primeiros";
		
		conversor.converterCamelCase(stringIniciadaComNumeros);
	}

	@Test
	public void stringComDuasPalavrasTest() {
		String stringComDuasPalavras = "nomeComposto";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("nome", "composto"), retorno);
	}

	@Test
	public void stringComTresPalavrasTest() {
		String stringComDuasPalavras = "nomeCompostoTriplo";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("nome", "composto", "triplo"), retorno);
	}

	@Test
	public void primeiraPalavraIniciaComMaiusculaTest() {
		String stringComDuasPalavras = "NomeComposto";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("nome", "composto"), retorno);
	}

	@Test
	public void ultimaPalavraComUmaLetraTest() {
		String stringComDuasPalavras = "nomeCompostoT";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("nome", "composto", "T"), retorno);
	}

	@Test
	public void palavraEmCaixaAltaTest() {
		String stringComDuasPalavras = "numeroCPFContribuinte";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("numero", "CPF", "contribuinte"), retorno);
	}

	@Test
	public void palavraSomenteDigitosTest() {
		String stringComDuasPalavras = "recupera10Primeiros";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("recupera", "10", "primeiros"), retorno);
	}
	
	@Test
	public void palavrasSomenteDigitosEMaiusculaTest() {
		String stringComDuasPalavras = "recupera10CPFPrimeiros";
		
		List<String> retorno = conversor.converterCamelCase(stringComDuasPalavras);
		
		assertEquals(Arrays.asList("recupera", "10","CPF", "primeiros"), retorno);
	}
}
