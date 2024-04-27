package akautomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest2 {
	
ChromeDriver driver;
	
	@BeforeTest
	public void Setup() {
		
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
	public void actualTest1() throws InterruptedException {
		//Visit my portfolio
		driver.get("https://rcsmetx.wixsite.com/arsalanportfolio");
		//wait for 3 seconds
		Thread.sleep(3000);
	}
	
	@AfterTest
	public void endTest1() {
		//quit browser
	 	driver.quit();
	}
				
}

