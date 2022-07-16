package objects;



import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




public class browser {
	

	public ChromeDriver driver;
	
	

	public ChromeDriver browserInstallation() throws IOException
	{
		driver = (ChromeDriver) WebDriverManager.chromedriver().create();
		return driver;
		
	}
	
	/*public ChromeDriver browserInstallation()
	{
		
	}*/
	
}
