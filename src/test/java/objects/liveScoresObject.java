package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class liveScoresObject {
	
	
	public ChromeDriver driver;
	
	public liveScoresObject(ChromeDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='ds-flex ds-items-center']/div/span")
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
}
