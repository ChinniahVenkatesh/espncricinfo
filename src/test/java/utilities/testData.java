package utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.Response;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import objects.browser;



public class testData extends browser{
	
	public ChromeDriver driver;
	public Logger log = LogManager.getLogger(testData.class);

public  testData(ChromeDriver driver, String url) throws IOException, InterruptedException 
{
	JavascriptExecutor je = (JavascriptExecutor) driver;
	je.executeScript("window.open()");
	Set<String> windows = driver.getWindowHandles();
	Iterator<String> i = windows.iterator();
	String  parentWindow = i.next();
	while(i.hasNext())
	{
		driver.switchTo().window(i.next());
	}
	driver.get(url);
	DevTools devtools = driver.getDevTools();
	devtools.createSession();
	devtools.send(Network.enable(Optional.<Integer> empty(), Optional.<Integer> empty(), Optional.<Integer> empty()));
	devtools.addListener(Network.responseReceived(), res ->
	{
	Response response = res.getResponse();
	if(response.getStatus() == 404 || response.getStatus() == 504 || response.getStatus() == 500)
	{
	Assert.assertFalse(false, "URL of the API:"+response.getUrl()+"Status is:"+response.getStatus());
	log.error("URL of the API:"+response.getUrl()+"Status is:"+response.getStatus());
	System.out.println("URL of the API:"+response.getUrl()+"Status is:"+response.getStatus());
	}
});
	Thread.sleep(60000);
	driver.switchTo().window(parentWindow);
}

}
