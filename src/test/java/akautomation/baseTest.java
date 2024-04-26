package akautomation;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	public static void main(String[] args) throws InterruptedException {
		
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
        ChromeDriver driver= new ChromeDriver();
        //maximizing the browse window
		driver.manage().window().maximize();
		//clearing the Cache
		driver.manage().deleteAllCookies();
		//visiting my Portfolio
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
		Thread.sleep(5000);
		//quit browser
	 	driver.quit();
				
}
}
