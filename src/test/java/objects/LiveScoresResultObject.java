package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LiveScoresResultObject {

	public WebDriver driver;
	
	public LiveScoresResultObject(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath="//div[@class='ds-popper-wrapper ds-grow ds-flex ds-items-center ds-w-1/2']/div/div/i")
	WebElement seriesFilter;
	
	public WebElement seriesFilter()
	{
		return seriesFilter;
	}
}
