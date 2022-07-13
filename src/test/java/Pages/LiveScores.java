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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

import base.browser;
import junit.framework.Assert;
import objects.homepageObject;
import objects.liveScoresObject;

import utilities.Commonmethods;
import utilities.testData;

public class LiveScores extends browser{
	
	public WebDriver driver;
	public  Logger log = LogManager.getLogger(LiveScores.class);
	Commonmethods c = new Commonmethods();
	String gameurl;
	SimpleDateFormat sd = new SimpleDateFormat("dd MMM yyyy");
	Date d = new Date();
	liveScoresObject ls = new liveScoresObject(driver);
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
	
	@Test(priority=5,enabled= false)
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
		Commonmethods c = new Commonmethods();
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
	public void Apply()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		ls.Apply();
	}
	
	@Test(priority=9)
	public void ClickTeamSelectedEvent()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> events = ls.Events();
		String parentWindow = " ";
		for(int i = 0;  i<= events.size();i++)
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("window.open()");
			Set<String> a = driver.getWindowHandles();
			Iterator<String> ij = a.iterator();
			parentWindow = driver.getWindowHandle();
			while(ij.hasNext())
			{
				driver.switchTo().window(ij.next());
			}
			String url = events.get(i).getAttribute("href");
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(i));
		    driver.get(url);
		}
		driver.switchTo().window(parentWindow);
	}
	
	@Test(priority=10)
	
	public void allmatches()
	{
		liveScoresObject ls = new liveScoresObject(driver);
		List<WebElement> links = ls.viewallMatches();
		String parentWindow = " ";
		for(WebElement link: links)
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("window.open()");
			Set<String> a = driver.getWindowHandles();
			Iterator<String> i = a.iterator();
			 parentWindow = driver.getWindowHandle();
			while(i.hasNext())
			{
				driver.switchTo().window(i.next());
			}
			String url = link.getAttribute("href");
			driver.get(url);	
		}
		driver.switchTo().window(parentWindow);
	}
	
	}


