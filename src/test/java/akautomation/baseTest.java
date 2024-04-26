package akautomation;

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
		//quit browser
	 	driver.quit();
				
}
}
