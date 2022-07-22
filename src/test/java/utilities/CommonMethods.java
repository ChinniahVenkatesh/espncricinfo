package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;


public class CommonMethods {
	
	public WebDriver driver;
	
	
	
	public int brokenurl(WebDriver driver , String pageurl) throws IOException, InterruptedException
	{ 
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.open()");
		Set<String> a = driver.getWindowHandles();
		Iterator<String> i = a.iterator();
		String  parentWindow = i.next();
		while(i.hasNext())
		{
			driver.switchTo().window(i.next());
		}
		driver.navigate().to(pageurl);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10000));
		HttpsURLConnection connect = (HttpsURLConnection) new URL(pageurl).openConnection();
		connect.setRequestMethod("GET");
		int Status = connect.getResponseCode();
		driver.close();
		driver.switchTo().window(parentWindow);
		return Status;
	}
	
	public String PropertiesData(String name) throws IOException
	{
		Properties prop = new Properties();
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\Pages\\testData\\browserOption.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		return prop.getProperty(name);
	}
}
