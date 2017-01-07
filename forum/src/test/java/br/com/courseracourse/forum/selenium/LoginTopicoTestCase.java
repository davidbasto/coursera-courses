package br.com.courseracourse.forum.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.courseracourse.forum.model.DBUnitController;

public class LoginTopicoTestCase {
	private StringBuffer verificationErrors = new StringBuffer();
	private SeleniumTestUtils seleniumUtils;

	@Before
	public void setUp() throws Exception {
		this.seleniumUtils = new SeleniumTestUtils();

		new DBUnitController("/dados_iniciais_testes.xml");
	}

	@Test
	public void testLoginTopicoTestCase() throws Exception {
		this.seleniumUtils.login("fulanosilva", "123");
		assertEquals("TOPICO 1", this.seleniumUtils.getDriver().findElement(By.linkText("TOPICO 1")).getText());
		assertEquals("Fulano da Silva", this.seleniumUtils.getDriver().findElement(By.xpath("//td[2]")).getText());
	}
	
	@Test
	public void testTopicoComentarios() throws Exception {
		this.seleniumUtils.login("fulanosilva", "123");
		this.seleniumUtils.getDriver().findElement(By.linkText("TOPICO 1")).click();

	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Tópico".equals(this.seleniumUtils.getDriver().getTitle())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
		
		assertEquals("Fulano da Silva", this.seleniumUtils.getDriver().findElement(By.cssSelector("td")).getText());
		assertEquals(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque bibendum pretium odio posuere.",
				this.seleniumUtils.getDriver().findElement(By.xpath("//td[2]")).getText());
		assertEquals("Joao Jose", this.seleniumUtils.getDriver().findElement(By.xpath("//div[5]/table/tbody/tr/td")).getText());
		assertEquals("Comentário no TOPICO 1 do usuário joaojose",
				this.seleniumUtils.getDriver().findElement(By.xpath("//div[5]/table/tbody/tr/td[2]")).getText());
		assertEquals("Joao Jose", this.seleniumUtils.getDriver().findElement(By.xpath("//tr[2]/td")).getText());
		assertEquals("Outro Comentário no TOPICO 1 do usuário joaojose",
				this.seleniumUtils.getDriver().findElement(By.xpath("//tr[2]/td[2]")).getText());
		assertEquals("Maria Tereza", this.seleniumUtils.getDriver().findElement(By.xpath("//tr[3]/td")).getText());
		assertEquals("Comentário no TOPICO 1 do usuário maria",
				this.seleniumUtils.getDriver().findElement(By.xpath("//tr[3]/td[2]")).getText());
	}

	@Test
	public void testRanking() throws Exception {
		this.seleniumUtils.login("fulanosilva", "123");
		this.seleniumUtils.getDriver().findElement(By.linkText("Ranking")).click(); 
		
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("Ranking de Usuários".equals(this.seleniumUtils.getDriver().findElement(By.cssSelector("h2")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
		
		validaLinhaTabelaRanking(1, "Maria Tereza", "maria", 200);
		validaLinhaTabelaRanking(2, "Joao Jose", "joaojose", 150);
		validaLinhaTabelaRanking(3, "Pedro Paulo", "pedropaulo", 140);
		validaLinhaTabelaRanking(4, "Fulano da Silva", "fulanosilva", 100);

	}
	@After
	public void tearDown() throws Exception {
		this.seleniumUtils.getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


	private void validaLinhaTabelaRanking(int linha, String nome, String login, int pontos) {
		String xPathFormat = "//tr[%d]/td[%d]";
		assertEquals(Integer.valueOf(linha), Integer.valueOf(this.seleniumUtils.getDriver().findElement(By.xpath(String.format(xPathFormat, linha, 1))).getText()));
		assertEquals(nome, this.seleniumUtils.getDriver().findElement(By.xpath(String.format(xPathFormat, linha, 2))).getText());
		assertEquals(login, this.seleniumUtils.getDriver().findElement(By.xpath(String.format(xPathFormat, linha, 3))).getText());
		assertEquals(Integer.valueOf(pontos), Integer.valueOf(this.seleniumUtils.getDriver().findElement(By.xpath(String.format(xPathFormat, linha, 4))).getText()));
	}
	
}
