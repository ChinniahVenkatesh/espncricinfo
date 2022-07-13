package base;



import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




public class browser {
	
	public WebDriver driver;
	//public ChromeDriver driver;
	
	

	public WebDriver browserInstallation() throws IOException
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("chrome");
		driver = new RemoteWebDriver(new URL("http://192.168.1.101:4444"),caps);
		return driver;
	}
	
	/*public ChromeDriver browserInstallation()
	{
		driver = (ChromeDriver) WebDriverManager.chromedriver().create();
		return driver;
	}*/
	
}
