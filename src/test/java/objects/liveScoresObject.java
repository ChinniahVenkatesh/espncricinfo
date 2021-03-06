package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class liveScoresObject {
	
	
	public WebDriver driver;
	
	public liveScoresObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class*='ds-flex ds-items-center'] div[class='ds-flex'] span:nth-child(1)")
	List<WebElement> quickFilter;
	
	public List<WebElement> quickFilter()
	{
		return quickFilter;
	}
	
	By reset = By.xpath("//div[text()='Reset']");
	
	public WebElement reset()
	{
		return driver.findElement(reset);
	}
	
	@FindBy(xpath = "//div[@class='ds-mb-4'][1]/div/div/div/div/div/div/a")
	
	List<WebElement> topEvents;
	
	public List<WebElement> topEvents()
	{
		return topEvents;
	}
	
	@FindBy(xpath="//div[@class='ds-p-0']/div/div/div/div/a")
	List<WebElement> Events;
	
	public List<WebElement> Events()
	{
		return Events;
	}
	
	
		@FindBy(xpath="//div[@class='ds-popper-wrapper'][1]/a")
		WebElement LiveScorestab;
		
		public WebElement LiveScorestab()
		{
			return LiveScorestab;
		}
	
		@FindBy(css="div[class*='ds-w-96 ds-flex ds-shadow-elevated'] div div div i")
		WebElement teamfilter;
		
		public WebElement teamfilter()
		{
		return teamfilter;
		}
		
		@FindBy(xpath="//ul[@class='ds-flex ds-flex-col ds-grid ds-grid-cols-2 ds-gap-x-4 ds-px-2']/li")
		WebElement teamOption;
		
		public WebElement teamOption()
		{
			return teamOption;
		}
		
		@FindBy(xpath="//span[text()='Apply']")
		WebElement Apply;
		
		public WebElement Apply()
		{
			return Apply;
		}
		
		@FindBy(xpath="//span[text()='See all']/parent::a")
		List<WebElement> viewallMatches;
		
		public List<WebElement> viewallMatches()
		{
			return viewallMatches;
		}
		
		@FindBy(xpath="//span[text()='Clear All']")
		WebElement clearAll;
		
		public WebElement clearAll()
		{
			return clearAll;
		}
		
		@FindBy(css="button[class*='active:ds-bg-ui-fill-hover focus:ds-ring-4']")
		WebElement viewnext30days;
		
		public WebElement viewnext30days()
		{
			return viewnext30days;
		}
}
