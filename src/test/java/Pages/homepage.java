package Pages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;

import java.time.Duration;
import java.util.ArrayList;

import java.util.List;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.browser;

import objects.homepageObject;
import utilities.CommonMethods;

import utilities.testData;


public class homepage extends browser {
	
	public WebDriver driver;
	public  Logger log = LogManager.getLogger(homepage.class);
	public CommonMethods c = new CommonMethods();
	
	
	@Test(priority=0)
	public void Homepagestate() throws IOException, InterruptedException
	{
		driver = browserInstallation();
		driver.get("https://pp.espncricinfo.com/?hsci=true");
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
			System.out.println(e);
		}
		log.info(driver.getTitle());
		driver.navigate().refresh();
	}
	
	
	@Test(priority=1)
	public void hsb() throws MalformedURLException, IOException, InterruptedException
	{
		 homepageObject ho = new homepageObject(driver);
		List<WebElement> hsbOptions = ho.hsb();
		ArrayList<String> url = new ArrayList<String>();
		for(WebElement hsbOption: hsbOptions)
		{
			hsbOption.click();
			List<WebElement> hsbScorecells = ho.hsbScorecells();
			for(WebElement hsbScorecell: hsbScorecells)
			{
				url.add(hsbScorecell.getAttribute("href"));
				String gameurl = hsbScorecell.getAttribute("href");
				int Status = c.brokenurl(driver,gameurl);
				System.out.println("Page url is:"+gameurl+"Status of the page url is:"+ Status);
				if(Status == 404 || Status == 504)
				{
				log.info("Page url is:"+gameurl+"Status of the page url is:"+ Status);
				Assert.assertFalse(true);
				}
			}
		}
		
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\testingSource\\GamePage";
		File file = new File(path);
		file.canWrite();
		FileOutputStream fos = new FileOutputStream(path);
		for(int i =0 ; i < url.size();i++)
		{
		String gameUrl = url.get(i);
		byte[] b = gameUrl.getBytes();
		System.out.println(b);
		fos.write(b);
		fos.write("\n".getBytes());
		fos.flush();
		}
	}
	

	
	
	
	

}
