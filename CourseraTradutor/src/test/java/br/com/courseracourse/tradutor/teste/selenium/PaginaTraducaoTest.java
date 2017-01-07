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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaTraducaoTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		
		String currentDir = System.getProperty("user.dir");
		String marionetteDriverLocation = currentDir + "/src/main/resources/marionette/wires";
		System.out.println(marionetteDriverLocation);
		System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
 
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testECachorro() throws Exception {
		driver.get(baseUrl + "/CourseraTradutor/");
		assertEquals("Digite a palavra que deseja realizar a tradução",
				driver.findElement(By.cssSelector("h1")).getText());
		driver.findElement(By.name("palavra")).clear();
		driver.findElement(By.name("palavra")).sendKeys("Cachorro");
		driver.findElement(By.name("Traduzir")).click();
		
		waitUntilTradutorPageLoaded();
		
		assertEquals("A tradução para a palavra Cachorro é Dog", driver.findElement(By.cssSelector("h1")).getText());
	}

	@Test
	public void testEGato() throws Exception {
		driver.get(baseUrl + "/CourseraTradutor/");
		assertEquals("Digite a palavra que deseja realizar a tradução",
				driver.findElement(By.cssSelector("h1")).getText());
		driver.findElement(By.name("palavra")).clear();
		driver.findElement(By.name("palavra")).sendKeys("Gato");
		driver.findElement(By.name("Traduzir")).click();
		
		waitUntilTradutorPageLoaded();
		
		assertEquals("A tradução para a palavra Gato é Cat", driver.findElement(By.cssSelector("h1")).getText());
	}

	@Test	
	public void testPalavraInexistente() throws Exception {
		driver.get(baseUrl + "/CourseraTradutor/");
		assertEquals("Digite a palavra que deseja realizar a tradução",
				driver.findElement(By.cssSelector("h1")).getText());
		driver.findElement(By.name("palavra")).clear();
		driver.findElement(By.name("palavra")).sendKeys("PalavraInexistente");
		driver.findElement(By.name("Traduzir")).click();
		
		waitUntilTradutorPageLoaded();
		
		assertEquals("A tradução para a palavra PalavraInexistente é PalavraInexistente", driver.findElement(By.cssSelector("h1")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void waitUntilTradutorPageLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("traducao")));
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
	
	/*
	 * private WebDriver buildWebDriver(String pathToDriver) {
	 * 
	 * DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	 * capabilities.setCapability("marionette", true); // determine free ports
	 * for Marionette and WebDriver final int marionettePort =
	 * PortProber.findFreePort(); final int webDriverPort =
	 * PortProber.findFreePort(); // override, as GeckoDriverService provides no
	 * direct way to set the Marionette port GeckoDriverService.Builder builder
	 * = new GeckoDriverService.Builder() {
	 * 
	 * @Override protected ImmutableList<String> createArgs() { Builder<String>
	 * argsBuilder = ImmutableList.builder();
	 * argsBuilder.addAll(super.createArgs());
	 * argsBuilder.add(String.format("--marionette-port=%d", marionettePort));
	 * return argsBuilder.build(); } }; builder.usingPort(webDriverPort);
	 * builder.usingDriverExecutable(new File(pathToDriver)); GeckoDriverService
	 * driverService = builder.build(); try { driverService.start(); } catch
	 * (IOException e) { throw new
	 * IllegalStateException("Could not start the GeckoDriverService", e); } try
	 * { // keep checking the WebDriver port via Socket until it's available; //
	 * as far as I could tell, there is nothing more "high level", e.g. REST API
	 * waitUntilReady(webDriverPort, TimeUnit.SECONDS.toMillis(5));
	 * waitUntilReady(marionettePort, TimeUnit.SECONDS.toMillis(5)); } catch
	 * (Exception e) { // ignore } return new MarionetteDriver(driverService,
	 * capabilities); }
	 * 
	 * public static boolean available(int port) {
	 * 
	 * ServerSocket ss = null; DatagramSocket ds = null; try { ss = new
	 * ServerSocket(port); ss.setReuseAddress(true); ds = new
	 * DatagramSocket(port); ds.setReuseAddress(true); return true; } catch
	 * (IOException e) { } finally { if (ds != null) { ds.close(); }
	 * 
	 * if (ss != null) { try { ss.close(); } catch (IOException e) { should not
	 * be thrown } } }
	 * 
	 * return false; }
	 * 
	 * private void waitUntilReady(int webDriverPort, long millis) { boolean
	 * scanning=true; while(scanning) { if(!available(webDriverPort)) { try {
	 * System.out.println("Sleeping"); Thread.sleep(millis); }
	 * catch(InterruptedException ie){ ie.printStackTrace(); } } else {
	 * System.out.println("Port Avaliable " + webDriverPort); scanning=false; }
	 * } }
	 */
}
