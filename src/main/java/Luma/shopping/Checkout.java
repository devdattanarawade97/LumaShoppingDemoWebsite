package Luma.shopping;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {

	static WebDriver driver;
	public Checkout(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean checkCart() {
		
		try{ 
			
			WebElement checkoutKartElement=driver.findElement(By.xpath("//a[@class='action showcart']"));
		    checkoutKartElement.click();
		    Thread.sleep(3000);
		    WebElement checkElement=driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']"));
		    checkElement.click();
		return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("checkout cart failed :"+e.getMessage());
			return false;
		}
	}
	
	public boolean addAddress() {
		
		try {
		
		WebElement StreetAddress=driver.findElement(By.xpath("//input[@name='street[0]']"));
		StreetAddress.sendKeys("A wing");
		Thread.sleep(2000);
		WebElement cityElement=driver.findElement(By.xpath("//input[@name='city']"));
		cityElement.sendKeys("columbia");
		Thread.sleep(2000);
		WebElement stateElement=driver.findElement(By.xpath("//select[@name='region_id']"));
		Select select= new Select(stateElement);
		select.selectByVisibleText("New York");
		Thread.sleep(2000);
		WebElement zipcodeElement=driver.findElement(By.xpath("//input[@name='postcode']"));
		zipcodeElement.sendKeys("1111");
		Thread.sleep(2000);
//		WebElement country=driver.findElement(By.xpath("//select[@name='country_id']"));
//		Select selectCountry= new Select(country);
//		select.selectByVisibleText("United States");
		Thread.sleep(2000);
		WebElement numberElement=driver.findElement(By.xpath("//input[@name='telephone']"));
		numberElement.sendKeys("0101010101");
		Thread.sleep(2000);
		return true;
		
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to add adress :"+e.getMessage());
			return false;
		}
		
	}
	
	
	public  boolean  shippingMethod() {
		try {
			WebElement shipElement=driver.findElement(By.xpath("//input[@value='flatrate_flatrate']"));
			shipElement.click();
			Thread.sleep(2000);
			WebElement nextElement=driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextElement.click();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unble to select address :"+e.getMessage());
			return false;
		}
	}
	
	public  boolean  placeOrder() throws InterruptedException {
		boolean status=false;
		try {
			Thread.sleep(5000);
		WebElement placeOrdElement=driver.findElement(By.xpath("//span[text()='Place Order']//parent::button"));
		placeOrdElement.click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("/success"));
		String successOrderUrl="https://magento.softwaretestingboard.com/checkout/onepage/success/";
		if(driver.getCurrentUrl().equals(successOrderUrl)) {
			status=true;
		}
		else {
			status=false;
		}
		return status;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("failed while placing order :"+e.getMessage());
		return status;
		}
	}
	
	
}
