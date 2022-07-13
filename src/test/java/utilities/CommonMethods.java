package utilities;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.browser;

public class Commonmethods extends browser {
	
	public WebDriver driver;
	
	
	public int brokenurl(WebDriver driver , String pageurl) throws IOException, InterruptedException
	{ 
		
		driver.navigate().to(pageurl);
		Thread.sleep(60000);
		HttpsURLConnection connect = (HttpsURLConnection) new URL(pageurl).openConnection();
		connect.setRequestMethod("GET");
		int Status = connect.getResponseCode();
		return Status;
	}
}
