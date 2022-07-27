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
	
	public ChromeDriver driver1;
	public Logger log = LogManager.getLogger(testData.class);
	public Response response;

public  void BackendtestData(ChromeDriver driver1, String url) throws IOException, InterruptedException 
{
	JavascriptExecutor je = (JavascriptExecutor) driver1;
	je.executeScript("window.open()");
	Set<String> windows = driver1.getWindowHandles();
	Iterator<String> i = windows.iterator();
	String  parentWindow = i.next();
	while(i.hasNext())
	{
		driver1.switchTo().window(i.next());
	}
	driver1.get(url);
	DevTools devtools = driver1.getDevTools();
	devtools.createSession();
	devtools.send(Network.enable(Optional.empty(), Optional.empty(),Optional.empty()));
	devtools.addListener(Network.responseReceived(), res -> 
	{
	 response = res.getResponse();
	int Status =  response.getStatus();
	 if(Status== 400 || Status == 404 || Status == 500)
	 {
	System.out.println("URL is:"+response.getUrl()+"and status is"+response.getStatus());
	 }
	});
	driver1.navigate().refresh();
	Thread.sleep(10000);
	driver1.close();
	driver1.switchTo().window(parentWindow);
}
}
