package Pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import objects.LiveScoresUpcoming;
import objects.browser;
import objects.homepageObject;
import objects.liveScoresObject;
import utilities.CommonMethods;

public class LiveScores_upcoming extends browser{
	
	public WebDriver driver;
	public  Logger log = LogManager.getLogger(LiveScores_upcoming.class);
	String dateurl;
	
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
		WebElement options = ls.clickonoption();
		options.click();	
		Thread.sleep(1000);
	}
	
	@Test(priority=2)
	public void backToCurrent() throws InterruptedException
	{
		LiveScoresUpcoming ls = new LiveScoresUpcoming(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,15000)");
		ls.backToCurrent().click();
		Thread.sleep(10000);
	}
	
	@Test(priority=3)
	public void clickdate() throws IOException, InterruptedException
	{
		LiveScoresUpcoming ls = new LiveScoresUpcoming(driver);
		List<WebElement> dates = ls.date();
		int count = 0;
		int failcount = 0;
		List<WebElement> dateEvents = ls.dateEvents();
		System.out.println("Total Events:"+dateEvents.size());
		for(WebElement date: dates)
		{
			date.click();
			for(WebElement dateEvent : dateEvents)
			{
				count = count + 1;
				String url = dateEvent.getAttribute("href");
				CommonMethods m = new CommonMethods();
				int status = m.brokenurl(driver, url);
				if(status == 404 || status == 400 || status == 500 || status == 200)
				{
					failcount = failcount+1;
				log.info("Page url is:"+url+"Status of the page url is:"+ status);
				}
			}
			break;
		}
		System.out.println("passcount:"+count);
		System.out.println("failcount"+failcount);
	}
	
	@Test(priority=4)
	public void TeamFilter() {
		liveScoresObject ls = new liveScoresObject(driver);
		ls.teamfilter().click();
		ls.teamOption().click();
		ls.Apply().click();
	}
	
	@Test(priority=5)
	public void calendar() throws InterruptedException
	{
		LiveScoresUpcoming lsu = new LiveScoresUpcoming(driver);
		lsu.calender().click();
		Thread.sleep(1000);
		LocalDate currentDate = LocalDate.now();
		LocalDate nextDate = currentDate.plusDays(1);
		int date = nextDate.getDayOfMonth();
		String tomorrow = Integer.toString(date);
		List<WebElement> specificDates = lsu.specificdate();
		for(WebElement specificDate : specificDates)
		{
			String dateText = specificDate.getText();
			if(dateText.equalsIgnoreCase(tomorrow))
			{
				specificDate.click();
			}
			Thread.sleep(20000);
		}
		dateurl = driver.getCurrentUrl();
		
		
		
	}
	
	@Test(priority=6)
	public void calendarclickMonth() throws InterruptedException, IOException
	{
		LiveScoresUpcoming lsu = new LiveScoresUpcoming(driver);
		lsu.calender().click();
		lsu.nextmonth();
		SimpleDateFormat sd = new SimpleDateFormat("dd");
		Date d = new Date();
		String dar = sd.format(d);
		List<WebElement> specificDates = lsu.specificdate();
		for(WebElement specificDate : specificDates)
		{
			String todayDate = specificDate.getText();
			if(dar.equalsIgnoreCase(todayDate))
			{
				specificDate.click();
			}
			Thread.sleep(20000);
		}
		
		System.out.println(driver.getCurrentUrl());
		String dateUrl = driver.getCurrentUrl();
		CommonMethods c = new CommonMethods();
		c.brokenurl(driver, dateUrl);
	}
}
