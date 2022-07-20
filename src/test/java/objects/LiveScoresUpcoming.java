package objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LiveScoresUpcoming extends browser{
	
	public ChromeDriver driver;
	
	public LiveScoresUpcoming(ChromeDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[@class='ds-flex ds-items-center ds-px-4']/div[1]/div")
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
	
	@FindBy(css="div[class*='hover:ds-bg-ui-fill-hover']:nth-child(1)")
	WebElement clickonDay;
	
	public WebElement clickonDay()
	{
		return clickonDay;
	}
	
	@FindBy(css="button[class*='ds-shadow-elevated ds-border-0']")
	WebElement backToCurrent;
	
	public WebElement backToCurrent()
	{
		return backToCurrent;
	}
}
