package akautomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	ChromeDriver driver;
	
	@BeforeTest
	public void Setup() {
		
		//All supported Browser Drivers
		
		/*WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
		WebDriverManager.operadriver().setup();
		WebDriverManager.chromiumdriver().setup()
		WebDriverManager.iedriver().setup(); 
		*/
		
		//Command for specific version of browser
        //WebDriverManager.chromedriver().browserVersion("2.36").setup();
		
		//Setting up ChromeDriver
		WebDriverManager.chromedriver().setup();
		//Creating object with driver name
        driver= new ChromeDriver();
        //maximizing the browse window
		driver.manage().window().maximize();
		//clearing the Cache
		driver.manage().deleteAllCookies();
		//visiting my Portfolio
		
	}
	
	@Test
	public void actualTest() throws InterruptedException {
		driver.get("https://rcsmetx.wixsite.com/arsalanportfolio");
		//wait for 3 seconds
		Thread.sleep(3000);
		//visit google
		driver.get("https://google.com");
		//rejecting cookies on google consent modal
		if(driver.findElement(By.xpath("//*[text()= 'Alle ablehnen']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[text()= 'Alle ablehnen']")).click();
		}
		//searching query 
		driver.findElement(By.name("q")).sendKeys("dummy login portal for testing");
		//enter key after entering the query
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		//clicking on searched link
		driver.findElement(By.xpath("//a[@href = 'https://practicetestautomation.com/practice-test-login/']")).click();
		//getting page title
		String ActualTitle = driver.getTitle();
		//storing expected value in string variable
		String ExpectedTitle = "Test Login | Practice Test Automation";
		//comparing actual vs expected title
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		
		//usecase 1: submit the form without filling any field
		driver.findElement(By.id("submit")).click();
		//verifying that error message is coming 
		driver.findElement(By.xpath("//*[text()='Your username is invalid!']")).isDisplayed();
		
		//Refresh Page
		driver.navigate().refresh();
		//usecase 2: Invalid username, valid password
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("Password123");
		driver.findElement(By.id("submit")).click();
		//verifying that error message is coming 
		driver.findElement(By.xpath("//*[text()='Your username is invalid!']")).isDisplayed();
		
		//Refresh Page
		driver.navigate().refresh();
		//usecase 3: Valid username, invalid password
		driver.findElement(By.id("username")).sendKeys("student");
		driver.findElement(By.id("password")).sendKeys("Password123454");
		driver.findElement(By.id("submit")).click();
		//verifying that error message is coming 
		driver.findElement(By.xpath("(//*[text() = 'Your password is invalid!'])[1]")).isDisplayed();
		
		//Refresh Page
		driver.navigate().refresh();
		//usecase 4: Valid username, valid password
		driver.findElement(By.id("username")).sendKeys("student");
		driver.findElement(By.id("password")).sendKeys("Password123");
		driver.findElement(By.id("submit")).click();
		//verifying that no error message is coming and user is successfully logged in
		driver.findElement(By.xpath("//*[text() = 'Logged In Successfully']")).isDisplayed();
		ActualTitle = driver.getTitle();
		Assert.assertNotEquals(ExpectedTitle, ActualTitle);
		
		//logout 
		driver.findElement(By.xpath("//a[text() = 'Log out']")).click();
		//verification
		ActualTitle = driver.getTitle();
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void endTest() {
		//quit browser
	 	driver.quit();
	}
				
}

