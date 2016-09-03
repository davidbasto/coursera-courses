package conversorunidtemp.business;

public class ConversorFahrenheitParaCelsius implements Conversor {

	@Override
	public Double converte(Double tempInFahrenheit) {
		return ((tempInFahrenheit - 32) * 5) /9;
	}

	@Override
	public String descreveConversao(Double tempInFahrenheit) {
		return tempInFahrenheit + " em Fahrenheit é igual a " + converte(tempInFahrenheit) + " em Celsius";
	}

}
