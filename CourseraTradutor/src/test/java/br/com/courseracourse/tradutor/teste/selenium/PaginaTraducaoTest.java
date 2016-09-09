package br.com.courseracourse.tradutor.teste.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PaginaTraducaoTest {
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
  public void testECachorro() throws Exception {
    driver.get(baseUrl + "/CourseraTradutor/");
    assertEquals("Digite a palavra que deseja realizar a tradução", driver.findElement(By.cssSelector("h1")).getText());
    driver.findElement(By.name("palavra")).clear();
    driver.findElement(By.name("palavra")).sendKeys("Cachorro");
    driver.findElement(By.name("Traduzir")).click();
    assertEquals("A tradução para a palavra Cachorro é Dog", driver.findElement(By.cssSelector("h1")).getText());
  }

  @Test
  public void testEGato() throws Exception {
    driver.get(baseUrl + "/CourseraTradutor/");
    assertEquals("Digite a palavra que deseja realizar a tradução", driver.findElement(By.cssSelector("h1")).getText());
    driver.findElement(By.name("palavra")).clear();
    driver.findElement(By.name("palavra")).sendKeys("Cachorro");
    driver.findElement(By.name("Traduzir")).click();
    assertEquals("A tradução para a palavra Cachorro é Dog", driver.findElement(By.cssSelector("h1")).getText());
  }
  @Test
  public void testPalavraInexistente() throws Exception {
    driver.get(baseUrl + "/CourseraTradutor/");
    assertEquals("Digite a palavra que deseja realizar a tradução", driver.findElement(By.cssSelector("h1")).getText());
    driver.findElement(By.name("palavra")).clear();
    driver.findElement(By.name("palavra")).sendKeys("PalavraInexistente");
    driver.findElement(By.name("Traduzir")).click();
    assertEquals("Não foi possível encontrar uma tradução para a palavra PalavraInexistente", driver.findElement(By.cssSelector("h1")).getText());
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
