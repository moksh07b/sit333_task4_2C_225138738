package sit707_week4;

import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginFormTest 
{

	WebDriver driver;
	WebDriverWait wait;
	 @Before
	 public void setUp() {
		 driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 }
	 
	 @After
	 public void tearDown() {
		 if (driver != null) {
			 driver.quit();
	     }
	 }
	 
	 private void openLoginPage() {
		 driver.get("https://www.bunnings.com.au/login");
	 }
	 
	 private void sleep(int sec) { 
		 try {
			 Thread.sleep(sec*1000); 
			 } 
		 catch (InterruptedException e) { 
			 e.printStackTrace(); 
			 } 
		 }
	 
	@Test
	public void testStudentIdentity() {
		String studentId = "225138738";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Moksh Bansal";
		Assert.assertNotNull("Student name is null", studentName);
	}
	
	private void performLogin(String email, String password ) {
		
		openLoginPage();
		
		System.out.println(driver.getCurrentUrl());
		
		WebElement emailField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement signInButton = driver.findElement(By.id("login-submit"));
		
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		
		signInButton.click();
		System.out.println(driver.getCurrentUrl());
	}
	
	@Test
	public void testFailNoEmailNoPass() {
		performLogin("", "");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
	}
	
	@Test
	public void testFailNoEmailWrongPass() {
		performLogin("", "abc!@#215125A");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));		
	}
	
	@Test
	public void testFailWrongEmailNoPass() {
		performLogin("abc@gmail.com", "");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));		
	}
	
	@Test
	public void testFailWrongEmailWrongPass() {
		performLogin("abc@gmail.com", "abc#$%^124A");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
	}
	
	@Test
	public void testFailCorrectEmailNoPass() {
		performLogin("mokshbansal07@gmail.com", "");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
	}
	
	@Test
	public void testFailCorrectEmailWrongPass() {
		performLogin("mokshbansal07@gmail.com", "abc#$%^124A");
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
	}
	
	@Test
	public void testFailCorrectEmailCorrectPass() {
		performLogin("mokshbansal07@gmail.com", "Moksh070!");
		sleep(4);
		Assert.assertFalse(driver.getCurrentUrl().contains("login"));
	}
	
}
