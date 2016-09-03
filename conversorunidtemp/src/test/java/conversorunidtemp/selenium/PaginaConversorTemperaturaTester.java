package conversorunidtemp.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PaginaConversorTemperaturaTester {

	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testConversaoCelsiusFarenheit() throws Exception {
	    driver.get(baseUrl + "/conversorunidtemp/");
	    new Select(driver.findElement(By.id("tipoConversao"))).selectByVisibleText("Celsius Para Fahrenheit");
	    driver.findElement(By.name("parametro")).clear();
	    driver.findElement(By.name("parametro")).sendKeys("100");
	    driver.findElement(By.name("Converter")).click();
	    assertEquals("100.0 em Celsius é igual a 212.0 em Fahrenheit", driver.findElement(By.cssSelector("h1")).getText());
	  }


	  @Test
	  public void testConversaoFarenheitCelsius() throws Exception {
	    driver.get(baseUrl + "/conversorunidtemp/");
	    new Select(driver.findElement(By.id("tipoConversao"))).selectByVisibleText("Fahrenheit Para Celsius");
	    driver.findElement(By.name("parametro")).clear();
	    driver.findElement(By.name("parametro")).sendKeys("212");
	    driver.findElement(By.name("Converter")).click();
	    assertEquals("212.0 em Fahrenheit é igual a 100.0 em Celsius", driver.findElement(By.cssSelector("h1")).getText());
	  }

	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}
