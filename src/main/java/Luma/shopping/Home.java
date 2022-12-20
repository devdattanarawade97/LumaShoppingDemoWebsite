package Luma.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Hello world!
 *
 */
public class Home 
{
   
	WebDriver driver;
	public Home(WebDriver driver) {
		this.driver=driver;
	}
	
	public void getHome(String url) {
		
		if(!driver.getCurrentUrl().equals(url)) {
			
			driver.get(url);
		}
	}
	
	public boolean performLogout() throws InterruptedException {
		boolean status=false;
		try {
		WebElement logoutElement=driver.findElement(By.xpath("//div[@data-target='dropdown']//preceding::ul//button"));
	    logoutElement.click();
	    Thread.sleep(2000);
		//WebElement logoutElement2=driver.findElement(By.xpath("//li[@class='link wishlist']//preceding::div[@class='customer-menu']//following-sibling::li//a[contains(text(),'Sign Out')]"));
	    logoutElement.findElement(By.xpath(".//following::li[@class='authorization-link'][1]")).click();
		Thread.sleep(2000);
	    if(!driver.getCurrentUrl().endsWith("logoutSuccess/")) {
	    	return status;
	    }
	    status=true;
	    
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println("logout failed :"+e.getMessage());
		return status;
	}
		return status;
}
}

