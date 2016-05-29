import java.util.ArrayList;
import java.util.List;

public class ConversorCamelCaseIteraCaracteres extends AbstractConversorCamelCase {
	
	@Override
	public List<String> aplicaLogicaConversorCamelCase(String original) {
		
		List<String> retorno = new ArrayList<String>();

		int indiceCorrente = 0;

		while(indiceCorrente + 1 <= original.length()) {
			Integer indiceFinalPalavra = ConversorCamelCaseIteraCaracteres.retornaIndiceFinalPalavra(original, indiceCorrente);
			String palavra = (indiceFinalPalavra <= original.length() ? 
					original.substring(indiceCorrente, indiceFinalPalavra) :
					original.substring(indiceCorrente));
			indiceCorrente = indiceFinalPalavra;
			retorno.add(palavra.toUpperCase().equals(palavra) ? palavra : palavra.toLowerCase());
		}
		
		return retorno;
	}

	private static Integer retornaIndiceFinalPalavra(String string, int indiceInicio) {
		boolean isUpperCaseWord = indiceInicio + 1 < string.length() && Character.isUpperCase(string.charAt(indiceInicio + 1));
		boolean isDigitoWord = Character.isDigit(string.charAt(indiceInicio));
		
		return (isDigitoWord ? retornaIndiceFinalPalavraSomenteNumeros(string, indiceInicio) :
					(isUpperCaseWord ? retornaIndiceFinalPalavraEmMaiuscula(string, indiceInicio) :
								  	   retornaIndicaFinalPalavraEmMinuscula(string, indiceInicio)));
	}

	private static Integer retornaIndiceFinalPalavraSomenteNumeros(String string, int indiceInicio) {
		int indiceCorrente = indiceInicio + 1;

		while(indiceCorrente + 1 < string.length()  && 
				 Character.isDigit(string.charAt(indiceCorrente + 1))) {
			indiceCorrente ++ ;
		}
		
		return indiceCorrente + 1;
	}

	private static Integer retornaIndicaFinalPalavraEmMinuscula(String string, int indiceInicio) {
		int indiceCorrente = indiceInicio + 1;

		while(indiceCorrente + 1 < string.length()  && 
				 Character.isLowerCase(string.charAt(indiceCorrente + 1))) {
			indiceCorrente ++ ;
		}
		
		return indiceCorrente + 1;
	}

	private static Integer retornaIndiceFinalPalavraEmMaiuscula(String string, int indiceInicio) {
		int indiceCorrente = indiceInicio + 1;

		while(indiceCorrente + 1 < string.length()  && 
				 Character.isUpperCase(string.charAt(indiceCorrente + 1))) {
			indiceCorrente ++ ;
		}
		
		return indiceCorrente;
	}
}
