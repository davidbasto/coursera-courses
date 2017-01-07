package br.com.courseracourse.forum.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTestUtils {

	private WebDriver driver;
	private String baseUrl;

	public SeleniumTestUtils() {
		String currentDir = System.getProperty("user.dir");
		String marionetteDriverLocation = currentDir + "/src/main/resources/marionette/wires";
		System.out.println(marionetteDriverLocation);
		System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
 
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		baseUrl = "http://localhost:8080/";
	}

	public void login(String login, String password) {
		driver.get(baseUrl + "/forum/");
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(login);
		driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();		
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
