package Luma.shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SanityMain {
	
	 static WebDriver driver;
	
	 public static void logStatus(String message ,boolean status) {
			
			String logStatus=String.format(message+": %s", status);
		
			System.out.println(logStatus);
		}
	
	public WebDriver createDriver() {
		 
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
		 System.out.println("maximizing window");
		 driver.manage().window().maximize();
		 return driver;
	}
	
	public static boolean TestCase01(WebDriver driver) throws InterruptedException {
		boolean status=false;
		
		System.out.println("opening website");
		Home home= new Home(driver);
		home.getHome("https://magento.softwaretestingboard.com/");
		Register register= new Register(driver);
		status=register.registerUser("Devdatta", "Devdatta@1234");
		logStatus("user registered successfully", status);
		status=register.verifyRegisterUserLoggedIn();
		logStatus("verified registered user logged in successfully", status);
	    AddToCart addToCart= new AddToCart(driver);
	    Thread.sleep(2000);
		status=addToCart.searchForProduct("Montana Wind Jacket");
		logStatus("navigated to product successfully", status);
		logStatus("product has been added to cart successfully", status);
		Thread.sleep(4000);
		Checkout checkout= new Checkout(driver);
		checkout.checkCart();
		Thread.sleep(5000);
		status=checkout.addAddress();
		logStatus("address added successfully", status);
		Thread.sleep(3000);
		status=checkout.shippingMethod();
		logStatus("shipping method selected successfully :", status);
		status=checkout.placeOrder();
		logStatus("order placed successfully", status);
		Thread.sleep(2000);
		status=home.performLogout();
		logStatus("loggedout successfully", status);
		Thread.sleep(4000);
		driver.quit();
		return status;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		int count=0;
		SanityMain main= new SanityMain();
		WebDriver driver= main.createDriver();
		
		boolean val1=SanityMain.TestCase01(driver);
		if(val1) {
			count++;
		}
		System.out.println("Testcase01 has been passed :"+val1);
		
	}
}


