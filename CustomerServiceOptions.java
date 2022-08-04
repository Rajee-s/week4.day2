package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class CustomerServiceOptions {

	public static void main(String[] args) throws IOException {

				// call WDM
				WebDriverManager.chromedriver().setup();
				
				//Disable notifications
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				
				
				//launch URL
				ChromeDriver driver = new ChromeDriver(options);
				driver.get("https://login.salesforce.com/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
				// Login with Provided Credentials
				driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
				driver.findElement(By.id("password")).sendKeys("Password$123");
				driver.findElement(By.id("Login")).click();
				
				String windowHandle = driver.getWindowHandle();
			
				
				// Click on Learn More link in Mobile Publisher
				driver.findElement(By.xpath("//span[text()='Learn More']")).click();
				
				
				//Switch to the next window using Windowhandles
				Set<String> mobPublisher = driver.getWindowHandles();
				List<String> lstMobile = new ArrayList<String>(mobPublisher);
				String newWindow = lstMobile.get(1);
				System.out.println("New Window: " +newWindow);
				driver.switchTo().window(newWindow);
				
				//click on the confirm button in the redirecting page
				driver.findElement(By.xpath("(//div[@class='dialog']//button)[2]")).click();
				
				//Click on products
				Shadow dom = new Shadow(driver);
				WebElement products = dom.findElementByXPath("//span[text()='Products']");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(products));
				products.click();	
				
				//hover on service
				Shadow dom1 = new Shadow(driver);
				WebElement service = dom1.findElementByXPath("//span[text()='Service']");
				Actions builder = new Actions(driver);
				builder.moveToElement(service).perform();
				
				//Select customer service
				WebElement customerService = dom1.findElementByXPath("//a[text()='Customer Service']");
				builder.moveToElement(customerService).perform();
				customerService.click();
				
				//Get the names of services available
				//Take screenshot
				WebElement service1 = driver.findElement(By.id("workforce-engagement"));
				builder.scrollToElement(service1).perform();
				File source = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
				
				//save it to local dir
				
				File dest = new File("./customerservices.png");
				FileUtils.copyFile(source, dest);
				
				
				
				//Verify the title
				String title = driver.getTitle();
				if(title.contains("Customer Service Tools"));
					System.out.println("Title: "+title);

	}

}
