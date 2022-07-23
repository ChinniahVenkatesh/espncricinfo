package Pages;




import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v103.network.model.Response;
import org.testng.annotations.Test;

import junit.framework.Assert;
import objects.browser;
import objects.homepageObject;
import objects.liveScoresObject;
import utilities.CommonMethods;
import utilities.testData;
public class LiveScores_Live extends browser{

	public WebDriver driver;
	public  Logger log = LogManager.getLogger(LiveScores_Live.class);
	CommonMethods c = new CommonMethods();
	ArrayList<String>gameUrl = new ArrayList<String>();
	SimpleDateFormat sd = new SimpleDateFormat("dd MMM yyyy");
	Date d = new Date();
	public ChromeDriver driver1;
	
	
	@Test(priority=0)
	public void alert() throws IOException, InterruptedException
	{
		driver = browserInstallation();
		CommonMethods c = new CommonMethods();
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
			log.error(e);
		}
	}
	
	@Test(priority=2)
	public void pageTitle() throws IOException
	{
		String title = driver.getTitle();
		Assert.assertEquals("Live cricket scores and updates, "+sd.format(d)+" for all matches, teams and tournaments", title);
	}
	
	@Test(priority=1) // https://hotstar.atlassian.net/browse/ER-4794
	public void brokenURl() throws IOException, InterruptedException
	{
		CommonMethods c = new CommonMethods();
		String url = c.PropertiesData("liveScores");
		driver.get(url);
		int status = c.brokenurl(driver,url);
		log.info("Page url is:"+url+"Status of the page url is:"+ status);
		if(status == 404 || status == 504  || status == 500)
		{
		log.info("Page url is:"+url+"Status of the page url is:"+ status);
		Assert.assertFalse(true);
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
		 gameUrl.add(gameurl);
		}
		String fileName = new SimpleDateFormat("ddMMyyyy'.txt'").format(new Date());
		String path = System.getProperty("user.dir")+"//src//test//java//testingSource//LiveScores"+fileName;
		File file = new File(path);
		file.createNewFile();
		file.canWrite();
		FileOutputStream fos = new FileOutputStream(file);
		for(int i =0 ; i < a.size();i++)
		{
		String gameurl = a.get(i);
		byte[] b = gameurl.getBytes();
		fos.write(b);
		CommonMethods c = new CommonMethods();
		String s = new String(b);
		String link = s.toString();
		int status =  c.brokenurl(driver, link);
		if(status==400 || status == 404 || status == 500)
		{
		log.info("Page url is:"+link+"Status of the page url is:"+ status);
		Assert.assertFalse(true);
		}
		fos.write("\n".getBytes());
		fos.flush();
		}
		}	
	
	@Test(priority=6)
	public void stickyTeamFilter()
	{	
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
		System.out.println(ls.Apply().isEnabled());
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
			log.info("Page url is:"+url+"Status of the page url is:"+ status);
			if(status == 404 || status == 504  || status == 500)
			{
			log.info("Page url is:"+url+"Status of the page url is:"+ status);
			Assert.assertFalse(true);
			}
		}
			
	}
	
	@Test(priority = 10)
	public void clearall() throws InterruptedException
	{
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
		Thread.sleep(1000);
		ls.clearAll().click();
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
			log.info("Page url is:"+url+"Status of the page url is:"+ status);
			if(status == 404 || status == 504  || status == 500)
			{
			log.info("Page url is:"+url+"Status of the page url is:"+ status);
			Assert.assertFalse(true);
			}
		}
	}
	
	@Test(priority=12)
	public void LiveBackendTest() throws IOException, InterruptedException
	{
		CommonMethods c = new CommonMethods();
		driver1 = browserchrome();
		String liveScoresurl = c.PropertiesData("liveScores");
		testData t = new testData();
		Response response = t.BackendtestData(driver1,liveScoresurl);
		if(response.getStatus() == 400 || response.getStatus() == 404 || response.getStatus() == 500)
		{
			log.info("Url of the page:"+response.getUrl()+"status of the page:"+response.getStatus());
		}
	}
	}


