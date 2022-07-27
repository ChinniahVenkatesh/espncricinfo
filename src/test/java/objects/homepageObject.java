package objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepageObject {
	
	public WebDriver driver;

	public homepageObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//div[@class='wzrk-alert-body']/following-sibling::div/button[@id='wzrk-cancel']")
	WebElement alert;
	
	public WebElement alert()
	{
		return alert;
	}
	
	@FindBy(css="div[class*='ds-scrollbar-hide'] div div div")
	List<WebElement> hsb;
	
	public List<WebElement> hsb()
	{
		return hsb;
	}
	
	@FindBy(xpath="//div[@class='ci-hsb-carousel']/div/div/div/div/div/div/a")
	List<WebElement> hsbScorecells;
	
	public List<WebElement> hsbScorecells()
	{
		return hsbScorecells;
	}
	
	@FindBy(xpath="//div[@class='ci-hsb-carousel']/div/div/div/div/div/div/a/div/div/div[3]/a")
	List<WebElement> scoreCellLinks;
	
	public List<WebElement> scoreCellLinks()
	{
		return scoreCellLinks;
	}
}
