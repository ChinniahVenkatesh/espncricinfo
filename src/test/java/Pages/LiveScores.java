package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import base.browser;

import junit.framework.Assert;
import objects.homepageObject;
import objects.liveScoresObject;
import utilities.CommonMethods;
import utilities.testData;

public class LiveScores extends browser{
	
	public ChromeDriver driver;
	public  Logger log = LogManager.getLogger(LiveScores.class);
	public CommonMethods c = new CommonMethods();
	
	
	public String PropertiesData(String name) throws IOException
	{
		Properties prop = new Properties();
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\Pages\\testData\\browserOption.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		return prop.getProperty(name);
	}
	
	@Test(priority=0)
	public void pageTitle() throws IOException, InterruptedException
	{
		driver = browserInstallation();
		driver.get(PropertiesData("domainurl"));
		driver.manage().window().maximize();
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		System.out.println(formatter.toString());
		Thread.sleep(10000);
		log.info(driver.getTitle());
		log.info(driver.manage().timeouts().getPageLoadTimeout());
		homepageObject ho = new homepageObject(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50000));
		try
		{
		ho.alert().click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		driver.navigate().to(PropertiesData("liveScores"));
	}
	
	@Test(priority=1)
	public void BackendTest() throws IOException, InterruptedException
	{
		String url = driver.getCurrentUrl();
		testData t = new testData(driver, url);
	}

	
	@Test(priority=2)
	public void quickFilter()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		System.out.println(ls.reset().isEnabled());
		List<WebElement> quickFilters = ls.quickFilter();
		for(WebElement filter: quickFilters)
		{
			if(filter.isEnabled() == true)
			filter.click();
		}
	}
	
	@Test(priority=3)
	public void Reset()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		if(ls.reset().isEnabled() == true)
		ls.reset().click();
	}
	
	@Test(priority=4)
	public void TopEvents() throws IOException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> topEvents = ls.topEvents();
		ArrayList<String> url = new ArrayList<String>();
		for(WebElement topEvent: topEvents)
		{
			url.add(topEvent.getAttribute("href"));
			String gameurl = topEvent.getAttribute("href");
			int Status = c.brokenurl(gameurl);
			if(Status == 404 || Status == 504)
			{
			log.info("Page url is:"+gameurl+"Status of the page url is:"+ Status);
			Assert.assertFalse(true);
			}
		}
	}
	
}
