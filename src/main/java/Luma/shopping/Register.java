package Luma.shopping;

import java.sql.Timestamp;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Register {

	WebDriver driver;
	String registeredUsername;
	public Register(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public boolean registerUser(String username, String password) throws InterruptedException{
		
		boolean status=true;
		try {
		WebElement createAccountElement=driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']"));
		createAccountElement.click();
	    Thread.sleep(2000);
		Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		WebElement firstName=driver.findElement(By.id("firstname"));
		String uniqueUsername="Devdatta_"+String.valueOf(timestamp.getTime());
		registeredUsername=uniqueUsername;
		firstName.sendKeys(uniqueUsername);
		 Thread.sleep(2000);
		WebElement lastName=driver.findElement(By.id("lastname"));
		lastName.sendKeys("n");
		 Thread.sleep(2000);
		WebElement emailIdElement= driver.findElement(By.id("email_address"));
		emailIdElement.sendKeys(String.format("dev%s@gmail.com", String.valueOf(timestamp.getTime())));
		 Thread.sleep(2000);
		WebElement passElement=driver.findElement(By.id("password"));
		passElement.sendKeys(password);
		 Thread.sleep(2000);
		WebElement confrmPassElement=driver.findElement(By.id("password-confirmation"));
		confrmPassElement.sendKeys(password);
		 Thread.sleep(2000);
		WebElement buttonElement=driver.findElement(By.xpath("//button[@title='Create an Account']"));
		buttonElement.click();
		Thread.sleep(2000);
		String accountUrl="https://magento.softwaretestingboard.com/customer/account/";
		if(!driver.getCurrentUrl().equals(accountUrl)) {
			status=false;
		}
		
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println("failed to register :"+e.getMessage());
	}
		return status;
}
       
	   public boolean verifyRegisterUserLoggedIn() {
		   boolean status=false;
		   
		  try {
			  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
			  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='action skip contentarea']//following::span[@class='logged-in'][1]")));
			  WebElement usernamElement=driver.findElement(By.xpath("//a[@class='action skip contentarea']//following::span[@class='logged-in'][1]"));
			   if(usernamElement.getText().contains(registeredUsername)) {
				   status=true;
			   }
			   else {
				   status=false;
			   }
			   
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("verify registered user logged in failed :"+e.getMessage());
		} 
		  return status;
	   }

}

