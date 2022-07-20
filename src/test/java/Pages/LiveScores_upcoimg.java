package Pages;






import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import objects.LiveScoresUpcoming;
import objects.browser;
import objects.homepageObject;
import objects.liveScoresObject;
import utilities.CommonMethods;

public class LiveScores_upcoimg extends browser{
	
	public ChromeDriver driver;
	public  Logger log = LogManager.getLogger(homepage.class);
	
	
	@Test(priority=0)
	public void pageTitle() throws IOException, InterruptedException
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
			log.info(e);
		}
		
		driver.get(c.PropertiesData("liveScoresupcoming"));
		String title = driver.getTitle();
		Thread.sleep(10000);
		Assert.assertEquals("Upcoming cricket match schedules, fixtures and timetables | ESPNcricinfo.com", title);
	}
	
	@Test(priority=1)
	public void clickviewByDay() throws InterruptedException {
		LiveScoresUpcoming ls = new LiveScoresUpcoming(driver);
		ls.findByoption().click();
		ls.clickonDay().click();
		Thread.sleep(10000);
	}
	
	@Test(priority=2)
	public void backToCurrent()
	{
		LiveScoresUpcoming ls = new LiveScoresUpcoming(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,15000)");
		ls.backToCurrent().click();

	}
	
	@Test(priority=3)
	public void clickdate() throws IOException, InterruptedException
	{
		LiveScoresUpcoming ls = new LiveScoresUpcoming(driver);
		List<WebElement> dates = ls.date();
		List<WebElement> dateEvents = ls.dateEvents();
		for(WebElement date: dates)
		{
			date.click();
			for(WebElement dateEvent : dateEvents)
			{
				String url = dateEvent.getAttribute("href");
				CommonMethods m = new CommonMethods();
				int status = m.brokenurl(driver, url);
				log.info("Page url is:"+url+"Status of the page url is:"+ status);
			}
		}
	}
	
	@Test(priority=4)
	public void TeamFilter() {
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
		ls.teamOption().click();
		ls.Apply().click();
	}
}
