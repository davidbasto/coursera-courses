package conversorunidtemp.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import conversorunidtemp.business.Conversor;
import conversorunidtemp.business.ConversorFactory;
import conversorunidtemp.business.TipoConversao;

public class ConversorTemperaturaTester {

	private ConversorFactory conversorFactory;
	
	@Before
	public void inicializaTeste() {
		conversorFactory = new ConversorFactory();
	}
	
	@Test
	public void testConversaoCelsiusParaFahrenheit() {
		Conversor conversor = conversorFactory.getConversor(TipoConversao.CELSIUS_PARA_FAHRENHEIT);
		assertEquals(new Double(212), conversor.converte(100d));
	}

	@Test
	public void testConversaoFahrenheitParaCelsius() {
		Conversor conversor = conversorFactory.getConversor(TipoConversao.FAHRENHEIT_PARA_CELSIUS);
		assertEquals(new Double(100), conversor.converte(212d));
	}

}
