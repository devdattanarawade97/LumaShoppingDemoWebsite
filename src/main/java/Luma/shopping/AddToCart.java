package Luma.shopping;

/**
 * Hello world!
 *
 */
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

	WebDriver driver;
	public AddToCart(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean searchForProduct(String product) throws InterruptedException {
		boolean status=false;
		
		try{
			WebElement searchBox= driver.findElement(By.xpath("//input[@id='search']"));
		searchBox.sendKeys(product);
		WebElement searchButton= driver.findElement(By.xpath("//button[@title='Search']"));
				searchButton.click();
		List<WebElement> parentelement=driver.findElements(By.xpath("//li[@class='item product product-item']"));
        Thread.sleep(3000);
         for(WebElement ele:parentelement) {
        	String actualProduct= ele.findElement(By.xpath(".//a[@class='product-item-link']")).getText();
        	 if(product.contains(actualProduct)){
        		 JavascriptExecutor executor= (JavascriptExecutor)driver;
        		 executor.executeScript("arguments[0].scrollIntoView();", ele);
        		 Thread.sleep(2000);
        		ele.findElement(By.xpath(".//div[text()='XL']")).click();
        		ele.findElement(By.xpath(".//div[@option-label='Red']")).click();
        		 Actions action= new Actions(driver);
        		 action.moveToElement(ele).perform();
        	     action.moveToElement(ele.findElement(By.xpath(".//button"))).click().perform();
        		 status=true;
        	 }
         }
         return status;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("failed to add product :"+e.getMessage());
			return false;
		}
		
	}
}

