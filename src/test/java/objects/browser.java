package objects;



import java.io.IOException;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




import io.github.bonigarcia.wdm.WebDriverManager;




public class browser {
	

	public WebDriver driver;
	public ChromeDriver driver1;
	
	

	public WebDriver browserInstallation() throws IOException
	{
		driver =  WebDriverManager.edgedriver().create();
		return driver;
	}
	
	public ChromeDriver browserchrome()
	{
		
		driver1 = (ChromeDriver) WebDriverManager.chromedriver().create();
		return driver1;
	}
	
}
