package akautomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest2 {
	
ChromeDriver driver1;
	
	@BeforeTest
	public void Setup1() {
		
		//Setting up ChromeDriver
		WebDriverManager.chromedriver().setup();
		//Creating object with driver name
        driver1= new ChromeDriver();
        //maximizing the browse window
		driver1.manage().window().maximize();
		//clearing the Cache
		driver1.manage().deleteAllCookies();
		//visiting my Portfolio
		
	}
	
	@Test
	public void actualTest1() throws InterruptedException {
		//Visit my portfolio
		driver1.get("https://rcsmetx.wixsite.com/arsalanportfolio");
		//wait for 3 seconds
		Thread.sleep(3000);
	}
	
	@AfterTest
	public void endTest1() {
		//quit browser
	 	driver1.quit();
	}
				
}

