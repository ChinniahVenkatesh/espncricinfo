package Pages;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;

import java.time.Duration;
import java.util.ArrayList;

import java.util.List;


import org.openqa.selenium.devtools.v103.network.model.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

import objects.browser;
import objects.homepageObject;
import utilities.CommonMethods;
import utilities.testData;



public class homepage extends browser {
	
	public WebDriver driver;
	public  Logger log = LogManager.getLogger(homepage.class);
	public CommonMethods c = new CommonMethods();
	public ChromeDriver driver1;
	public ArrayList<String> gamepageUrl = new ArrayList<String>();
	
	@Test(priority=0)
	public void Homepagestate() throws IOException, InterruptedException
	{
		driver = browserInstallation();
		driver.get("https://pp.espncricinfo.com/?hsci=true");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		homepageObject ho = new homepageObject(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60000));
		try
		{
		ho.alert().click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		log.info(driver.getTitle());
		driver.navigate().refresh();
	}
	
	
	@Test(priority=1)
	public void hsb() throws MalformedURLException, IOException, InterruptedException
	{
		 homepageObject ho = new homepageObject(driver);
		List<WebElement> hsbOptions = ho.hsb();
		ArrayList<String> url = new ArrayList<String>();
		for(WebElement hsbOption: hsbOptions)
		{
			hsbOption.click();
			List<WebElement> hsbScorecells = ho.hsbScorecells();
			for(WebElement hsbScorecell: hsbScorecells)
			{
				url.add(hsbScorecell.getAttribute("href"));
				String gameurl = hsbScorecell.getAttribute("href");
				gamepageUrl.add(gameurl);
				int Status = c.brokenurl(driver,gameurl);
				System.out.println("Page url is:"+gameurl+"Status of the page url is:"+ Status);
				if(Status == 404 || Status == 504 || Status ==  400 || Status == 200)
				{
				log.info("Page url is:"+gameurl+"Status of the page url is:"+ Status);
				//Assert.assertFalse(true);
				}
			}
		}
	
		
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\testingSource\\GamePage";
		File file = new File(path);
		file.canWrite();
		FileOutputStream fos = new FileOutputStream(path);
		for(int i =0 ; i < url.size();i++)
		{
		String gameUrl = url.get(i);
		byte[] b = gameUrl.getBytes();
		fos.write(b);
		fos.write("\n".getBytes());
		fos.flush();
		}
		
		
		
	}
	
	@Test(priority=3)
	public void gamePagebackendTest() throws IOException, InterruptedException
	{
		CommonMethods c = new CommonMethods();
		driver1= browserchrome();
		driver1.get(c.PropertiesData("domainurl"));
		driver1.manage().window().maximize();
		for(int i =0; i< gamepageUrl.size();i++)
		{
		String pageLink = gamepageUrl.get(i);
		testData t = new testData();
		Response response = t.BackendtestData(driver1, pageLink);
		if(response.getStatus() == 400 || response.getStatus() == 404 || response.getStatus() == 500)
		{
			log.info("Url of the page:"+response.getUrl()+"status of the page:"+response.getStatus());
		}
		}
	}
	
	@Test(priority=4)
	public void homepageBackendTest() throws IOException, InterruptedException
	{
		CommonMethods c = new CommonMethods();
		String homeUrl = c.PropertiesData("domainurl");
		testData t = new testData();
		Response response = t.BackendtestData(driver1, homeUrl);
		
		if(response.getStatus() == 400 || response.getStatus() == 404 || response.getStatus() == 500)
		{
			log.info("Url of the page:"+response.getUrl()+"status of the page:"+response.getStatus());
		}
		
	}
	
	
}
