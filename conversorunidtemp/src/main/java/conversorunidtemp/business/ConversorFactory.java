package conversorunidtemp.business;

public class ConversorFactory {

	public Conversor getConversor(TipoConversao tipoConversao) {
		switch (tipoConversao) {
		case CELSIUS_PARA_FAHRENHEIT:
			return new ConversorCelsiusParaFahrenheit();

		case FAHRENHEIT_PARA_CELSIUS:
			return new ConversorFahrenheitParaCelsius();
			
		default:
			return null;
		}
	}

}
