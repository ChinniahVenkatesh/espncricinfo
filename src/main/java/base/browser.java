package base;



import java.io.IOException;



import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class browser {
	
	public ChromeDriver driver;
	
	

	public ChromeDriver browserInstallation() throws IOException
	{
		driver = (ChromeDriver) WebDriverManager.chromedriver().create();
		return driver;	
	}
}
