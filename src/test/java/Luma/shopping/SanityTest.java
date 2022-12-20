package Luma.shopping;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SanityTest 
{
	SanityMain main= new SanityMain();
	 WebDriver driver=main.createDriver();
	  @Test
   public void Testcase01() throws InterruptedException {
	   
	   boolean status=SanityMain.TestCase01(driver);
	   if(!status) {
		   System.out.println("Testcase01 failed");
	   }
	   else{
	   System.out.println("Testcase01 has been passed");
	   }
   }
	
  
 

   
}

