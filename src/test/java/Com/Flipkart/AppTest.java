package Com.Flipkart;


import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class AppTest 
{
	public static void main(String[] args) 
	{
		String product = "mobiles";
		String site    = "https://flipkart.com";
		String cartUrl = "https://www.flipkart.com/viewcart?otracker=PP_GoToCart";
		try {
			
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(site);
		System.out.println("Opened Filpkart website");
		Thread.sleep(4000);
		WebElement logInWindow= driver.findElement(By.className("_2doB4z"));
		if(logInWindow.isDisplayed())
		{
			if(logInWindow.isEnabled())
			{
				logInWindow.click();
			}	
		}
		searchProduct(driver,product);		
		addToCart(driver,cartUrl);
		driver.quit();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Add product to cart
	static void addToCart(WebDriver driver,String cartUrl) throws InterruptedException
	{
	    String UrlToClick=driver.findElement(By.xpath("//a[@class=\"_1fQZEK\"][1]")).getAttribute("href");
	    driver.findElement(By.xpath("(//div[@class=\"_13oc-S\"])[1]")).click();
	    String currentHandle= driver.getWindowHandle();
	    Thread.sleep(5000);
	    Set<String> handles=driver.getWindowHandles();
	    for (String actual: handles) {
	         if (!actual.equalsIgnoreCase(currentHandle)) {
	             //Switch to the opened tab
	              driver.switchTo().window(actual);
	             //opening the URL saved.
	              driver.get(UrlToClick);
	         }
	     }
	     System.out.println("Price of product:"+driver.findElement(By.xpath("//div[@class=\"_30jeq3 _16Jk6d\"]")).getText());
	     driver.findElement(By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]")).click();
	     System.out.println("Added Product to the cart" );
	     Thread.sleep(2000);
	     driver.get(cartUrl);
	     Thread.sleep(5000);
	     driver.findElement(By.xpath("//div[@class=\"_1uc2IE\"]//child::button[2]")).click();
	     Thread.sleep(2000);
	     System.out.println("Increased Product Quantity" );
	     System.out.println("Price of 2 Quantities:"+driver.findElement(By.xpath("//span[@class=\"_2-ut7f _1WpvJ7\"]")).getText());
	    
	}
	//Search Product
	static public void searchProduct(WebDriver driver,String productName) throws InterruptedException
	{
		WebElement searchBox=driver.findElement(By.className("_3704LK"));
		if(searchBox.isDisplayed())
		{
			if(searchBox.isEnabled())
			{
				searchBox.sendKeys(productName);
				System.out.println("Entered "+productName+" in the searched bar" );
				driver.findElement(By.className("L0Z3Pu")).click();
				Thread.sleep(4000);
			}
			else
			{
				System.err.println("Search text box is not enabled");
			}
		} 
		else
		{
			   System.err.println("Search text box is not present in the webpage");  

		}
		System.out.println("Searched "+productName);
	}
}