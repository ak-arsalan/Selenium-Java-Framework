package akautomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	ChromeDriver driver;
	//create the htmlReporter object 
	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
	//create ExtentReports and attach reporter(s)
	ExtentReports extent = new ExtentReports();
	//creates a toggle for the given test, add all log events under it
	ExtentTest test1 = extent.createTest("Java Maven TestNG Framework", "Here we go!");

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
		
		extent.attachReporter(htmlReporter);
		
		//Setting up ChromeDriver
		WebDriverManager.chromedriver().setup();
		//Creating object with driver name
	    driver= new ChromeDriver();
	    //maximizing the browse window
		driver.manage().window().maximize();
		//clearing the Cache
		driver.manage().deleteAllCookies();
		
	}
	
	@Test
	public void actualTest() throws InterruptedException {
		
		test1.log(Status.INFO, "Url opened");
		//maximize the window 
		//wait for 3 seconds
		Thread.sleep(3000);
		//visit google
		driver.get("https://google.com");
		test1.log(Status.INFO, "Url opened");
		//rejecting cookies on google consent modal
		if(driver.findElement(By.xpath("//*[text()= 'Alle ablehnen']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[text()= 'Alle ablehnen']")).click();
			test1.log(Status.INFO, "closing google consent");
		}
		
		//searching query 
		driver.findElement(By.name("q")).sendKeys("dummy login portal for testing");
		//enter key after entering the query
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		//clicking on searched link
		driver.findElement(By.xpath("//a[@href = 'https://practicetestautomation.com/practice-test-login/']")).click();
		//getting page title
		test1.log(Status.INFO, "Search site for testing passed");
		String ActualTitle = driver.getTitle();
		//storing expected value in string variable
		String ExpectedTitle = "Test Login | Practice Test Automation";
		//comparing actual vs expected title
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		test1.log(Status.INFO, "Title matching passed");
		
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
	 	test1.pass("closed the browser");
	 	test1.info("test completed");
	 	//write results into the file
	 	extent.flush();
	}
				
}

