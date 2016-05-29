import java.util.List;

public abstract class AbstractConversorCamelCase implements ConversorCamelCase {

	private static final String REGEX_LETRAS_NUMEROS = "\\A[A-Za-z0-9]*\\Z";
	private static final String REGEX_INICIA_COM_NUMERO = "\\A[0-9].*\\Z";

	@Override
	public List<String> converterCamelCase(String original) {

		verificaStringValida(original);
		
		return aplicaLogicaConversorCamelCase(original);
	}

	private void verificaStringValida(String original) {
		if(!possuiApenasLetrasENumeros(original)) {
			throw new StringInvalidaException("Somente Letras e números são permitidos!");
		}
		
		if(stringIniciaComNumero(original)) {
			throw new StringInvalidaException("Não é permitida uma string iniciada com números.");
		}
	}

	private boolean stringIniciaComNumero(String original) {
		return original.matches(REGEX_INICIA_COM_NUMERO);
	}

	private boolean possuiApenasLetrasENumeros(String original) {
		return original.matches(REGEX_LETRAS_NUMEROS);
	}
	
	public abstract List<String> aplicaLogicaConversorCamelCase(String original);
}
