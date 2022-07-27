package Pages;


import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import objects.LiveScoresResultObject;
import objects.LiveScoresUpcoming;
import objects.browser;
import objects.homepageObject;
import objects.liveScoresObject;
import utilities.CommonMethods;
import utilities.testData;

public class LiveScoresResult extends browser{
	
	public WebDriver driver;
	public Logger log = LogManager.getLogger(LiveScoresResult.class);
	
	@Test(priority=0)
	public void pageTitle() throws IOException, InterruptedException
	{
		CommonMethods c = new CommonMethods();
		driver = browserInstallation();
		driver.get(c.PropertiesData("domainurl"));
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
		driver.get(c.PropertiesData("liveScoresResult"));
		String title = driver.getTitle();
		Assert.assertEquals("Live Cricket Results - Find Latest cricket match results of all Matches Online | ESPNcricinfo.com", title);
	}
	
	@Test(priority=3,enabled=true)
	public void dates() throws IOException, InterruptedException
	{
		driver1= browserchrome();
		CommonMethods c = new CommonMethods();
		driver1.get(c.PropertiesData("domainurl"));
		LiveScoresUpcoming les = new LiveScoresUpcoming(driver);
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> dates = les.date();
		List<WebElement> dateEvents = ls.Events();
		testData t = new testData();
		for(WebElement date : dates)
		{
			date.click();
			for(WebElement dateEvent: dateEvents)
			{
				String gameUrl = dateEvent.getAttribute("href");
				c.brokenurl(driver, gameUrl);
				int status = c.brokenurl(driver, gameUrl);
				if(status == 404 || status == 400 || status == 500 || status == 200)
				{
				log.info("Page url is:"+gameUrl+"Status of the page url is:"+ status);
				}
				t.BackendtestData(driver1, gameUrl);
			}
			
		}
	}
	
	@Test(priority = 1)
	public void seriesFilter()
	{
		LiveScoresResultObject lesr = new LiveScoresResultObject(driver);
		liveScoresObject ls = new liveScoresObject(driver);
		lesr.seriesFilter().click();
		ls.teamOption().click();
		ls.Apply().click();
	}
	
	@Test(priority=2)
	public void clearall() throws InterruptedException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
		Thread.sleep(1000);
		ls.clearAll().click();
	}
	
	@Test(priority=4)
	public void previous30days() throws IOException, InterruptedException
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		liveScoresObject ls = new liveScoresObject(driver);
		Actions a = new Actions(driver);
		a.moveToElement(ls.viewnext30days()).click();
		String url = driver.getCurrentUrl();
		CommonMethods c = new CommonMethods();
		int status = c.brokenurl(driver, url);
		
		if(status == 404 || status == 400 || status == 500 || status == 200)
		{
		log.info("Page url is:"+url+"Status of the page url is:"+ status);
		}
	}
}
