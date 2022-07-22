package utilities;



import java.io.IOException;


import java.util.Iterator;
import java.util.Optional;

import java.util.Set;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.Response;

public class testData {
	
	public ChromeDriver driver;
	public Logger log = LogManager.getLogger(testData.class);
	public Response response;

public  Response  BackendtestData(ChromeDriver driver, String url) throws IOException, InterruptedException 
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
	response = res.getResponse();
	
});
	Thread.sleep(10000);
	driver.close();
	driver.switchTo().window(parentWindow);
	return response;
}

}
