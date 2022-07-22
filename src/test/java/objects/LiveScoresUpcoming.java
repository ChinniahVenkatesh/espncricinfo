package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LiveScoresUpcoming extends browser{
	
	public WebDriver driver;
	
	public LiveScoresUpcoming(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver = driver;
	}
	
	@FindBy(xpath="//div[@class='ds-flex ds-items-center ds-rounded-xl ds-px-4']/div[1]/div")
	WebElement findByoption;
	
	public WebElement findByoption()
	{
		return findByoption;
	}
	
	@FindBy(xpath="//div[@class='ds-cursor-pointer']/div")
	List<WebElement> date;
	
	public List<WebElement> date()
	{
		return date;
	}
	
	@FindBy(xpath="//div[@class='ds-border ds-border-line ds-rounded-xl ds-overflow-hidden']/a")
	List<WebElement> dateEvents;
	
	public List<WebElement> dateEvents()
	{
		return dateEvents;
	}
	
	@FindBy(css="div[class*='hover:ds-bg-ui-fill-hover']:nth-child(1) span")
	WebElement clickonoption;
	
	public WebElement clickonoption()
	{
		return clickonoption;
	}
	
	@FindBy(css="button[class*='ds-shadow-elevated ds-border-0']")
	WebElement backToCurrent;
	
	public WebElement backToCurrent()
	{
		return backToCurrent;
	}
	
	@FindBy(css="i[class*='icon-calendar_today-outlined']")
	WebElement calender;
	
	public WebElement calender()
	{
		return calender;
	}
	
	@FindBy(xpath="//div[@class='react-datepicker__week']/div")
	 List<WebElement> specificdate;
	
	public List<WebElement> specificdate()
	{
		return specificdate;
	}
	
	By nextmonth = By.xpath("//button[@aria-label='Next Month']");
	
	public WebElement nextmonth()
	{
		return driver.findElement(nextmonth);
	}
	
}
