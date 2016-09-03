package conversorunidtemp.business;

public class ConversorCelsiusParaFahrenheit implements Conversor {

	@Override
	public Double converte(Double tempInCelsiusDegrees) {
		return ((tempInCelsiusDegrees * 9) / 5) + 32;
	}

	@Override
	public String descreveConversao(Double tempInCelsiusDegrees) {
		return tempInCelsiusDegrees + " em Celsius é igual a " + converte(tempInCelsiusDegrees) + " em Fahrenheit";
	}

}
