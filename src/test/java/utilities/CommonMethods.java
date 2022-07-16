package utilities;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import objects.browser;

public class CommonMethods extends browser {
	
	public ChromeDriver driver;
	
	
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
		HttpsURLConnection connect = (HttpsURLConnection) new URL(pageurl).openConnection();
		connect.setRequestMethod("GET");
		int Status = connect.getResponseCode();
		Thread.sleep(20000);
		driver.close();
		driver.switchTo().window(parentWindow);
		return Status;
	}
}
