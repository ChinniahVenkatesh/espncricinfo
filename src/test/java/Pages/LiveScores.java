package Pages;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

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
	CommonMethods c = new CommonMethods();
	String gameurl;
	SimpleDateFormat sd = new SimpleDateFormat("dd MMM yyyy");
	Date d = new Date();
	
	public String PropertiesData(String name) throws IOException
	{
		Properties prop = new Properties();
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\Pages\\testData\\browserOption.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		return prop.getProperty(name);
	}
	
	@Test(priority=0)
	public void alert() throws IOException, InterruptedException
	{
		driver = browserInstallation();
		driver.get(PropertiesData("domainurl"));
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
	}
	
	@Test(priority=2)
	public void pageTitle() throws IOException
	{
		String title = driver.getTitle();
		System.out.println("Live cricket scores and updates,"+sd.format(d)+" for all matches, teams and tournaments");
		Assert.assertEquals("Live cricket scores and updates, "+sd.format(d)+" for all matches, teams and tournaments", title);
	}
	
	@Test(priority=1) // https://hotstar.atlassian.net/browse/ER-4794
	public void brokenURl() throws IOException, InterruptedException
	{
		
		String url = PropertiesData("liveScores");
		driver.get(url);
		int status = c.brokenurl(driver,url);
		System.out.println("Page url is:"+url+"Status of the page url is:"+ status);
		if(status == 404 || status == 504  || status == 500)
		{
		log.info("Page url is:"+url+"Status of the page url is:"+ status);
		Assert.assertFalse(true);
		System.out.println("Page url is:"+url+"Status of the page url is:"+ status);
		}
	}
	

	
	@Test(priority=3)
	public void quickFilter() throws InterruptedException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		System.out.println(ls.reset().isEnabled());
		List<WebElement> quickFilters = ls.quickFilter();
		for(WebElement filter: quickFilters)
		{
			filter.click();
		}
	}
	
	@Test(priority=4)
	public void Reset()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		if(ls.reset().isEnabled() == true)
		ls.reset().click();
	}
	
	@Test(priority=5,enabled= true)
	public void TopEvents() throws IOException, InterruptedException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> topEvents = ls.topEvents();
		ArrayList<String> a = new ArrayList<String>();
		for(WebElement topEvent: topEvents)
		{
		 a.add(topEvent.getAttribute("href"));
		 String gameurl = topEvent.getAttribute("href");
		}
		String fileName = new SimpleDateFormat("ddMMyyyy'.txt'").format(new Date());
		String path = System.getProperty("user.dir")+"//src//test//java//testingSource//LiveScores"+fileName;
		File file = new File(path);
		file.createNewFile();
		file.canWrite();
		FileOutputStream fos = new FileOutputStream(file);
		for(int i =0 ; i < a.size();i++)
		{
		String gameUrl = a.get(i);
		byte[] b = gameUrl.getBytes();
		fos.write(b);
		CommonMethods c = new CommonMethods();
		String s = new String(b);
		String link = s.toString();
		c.brokenurl(driver, link);
		fos.write("\n".getBytes());
		fos.flush();
		}
		}	
	
	@Test(priority=6)
	public void stickyTeamFilter()
	{	
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
	}
	
	@Test(priority=7)
	
	public void selectTeamOption()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamOption().click();
	}
	
	@Test(priority=8)
	public void Apply() throws InterruptedException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		ls.Apply().click();
		Thread.sleep(2000);
	}
	
	@Test(priority=9)
	public void ClickTeamSelectedEvent() throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> events = ls.Events();
		
		for(WebElement event:events)
		{
			String url = event.getAttribute("href");
			CommonMethods c = new CommonMethods();
			int status = c.brokenurl(driver, url);
			System.out.println("Page url is:"+url+"Status of the page url is:"+ status);
			if(status == 404 || status == 504  || status == 500)
			{
			log.info("Page url is:"+url+"Status of the page url is:"+ status);
			Assert.assertFalse(true);
			System.out.println("Page url is:"+url+"Status of the page url is:"+ status);
			}
			
		}
			
	}
	
	@Test(priority = 10)
	public void clearall()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
		ls.clearAll().click();
		ls.Apply().click();
		ls.reset().click();
	}
	
	@Test(priority=11)
	
	public void allmatches() throws IOException, InterruptedException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> links = ls.viewallMatches();
		for(WebElement link: links)
		{
			String url = link.getAttribute("href");
			CommonMethods c = new CommonMethods();
			int status = c.brokenurl(driver, url);
			System.out.println("Page url is:"+url+"Status of the page url is:"+ status);
			if(status == 404 || status == 504  || status == 500)
			{
			log.info("Page url is:"+url+"Status of the page url is:"+ status);
			Assert.assertFalse(true);
			System.out.println("Page url is:"+url+"Status of the page url is:"+ status);
			}
			
		}
		
	}
	
	}


