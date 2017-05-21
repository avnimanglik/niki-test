package Nikki_Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;


public class niki 
{
		private static AndroidDriver<AndroidElement> driver; 
	
		@BeforeClass
		public void   setup() throws MalformedURLException, InterruptedException  
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			File appDir = new File("C:/Users/avni pc/Desktop/app-test-apk.apk");
			File app = new File(appDir, "app-niki-test.apk");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
			capabilities.setCapability(CapabilityType.VERSION, "5.0.1");
			capabilities.setCapability("deviceName", "FA6AB0306427");
			capabilities.setCapability(CapabilityType.PLATFORM, "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	    }
		
		
		//Check that name of the app should be displayed correctly
		@Test(priority=1)
		public void name()throws Exception 		 
		{
			WebElement e1 = driver.findElement(By.id("com.techbins.niki.beta:id/niki_title_chat"));
			Assert.assertEquals(e1.getText(), "Niki","Niki Title is either not present or is mispelled");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
		}

		//Checking whether Prepaid Service link is enabled (Same function can be used to test all links using List of WebElements).
		@Test(priority=2)
		public void prepaidServiceLinkEnabled()throws Exception	
		{
			WebElement e1=driver.findElement(By.linkText("Prepaid Recharge"));
			if(e1.isEnabled())
			{
				e1.click();
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			}
			
		}
		
		//Check whether the chat input box has arrow send button present & is clickable
		@Test(priority=3)
		public void sendButton()throws Exception	
		{
			driver.findElement(By.id("com.techbins.niki.beta:id/edtTxtInputMessage")).sendKeys("Hello Niki");
			driver.findElement(By.id("com.techbins.niki.beta:id/btnSend")).click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		}

		//Check that menu should appear while clicking triple line button on the top left
		@Test(priority=4)
		public void menu () throws Exception 	
		{
			driver.findElement(By.id("com.techbins.niki.beta:id/nikiMenuBlink")).click();
			driver.findElement(By.className("android.widget.RelativeLayout"));
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		}

		
		//Check whether 'Niki is typing' appears when Niki is typing
		@Test(priority=5)
		public void nikTyping() throws Exception	
		{
			driver.findElement(By.id("com.techbins.niki.beta:id/edtTxtInputMessage")).sendKeys("Hello Niki");
			driver.findElement(By.id("com.techbins.niki.beta:id/btnSend")).click();
			driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
			driver.findElement(By.id("com.techbins.niki.beta:id/typingText"));
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		}

		@AfterClass
		public void NikiClose() {
			driver.closeApp();
}

}






