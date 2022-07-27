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
	
	@FindBy(xpath="//div[@class='ds-popper-wrapper ds-flex ds-w-full']/div/div[2]/div/i")
	WebElement seriesFilter;
	
	public WebElement seriesFilter()
	{
		return seriesFilter;
	}
}
