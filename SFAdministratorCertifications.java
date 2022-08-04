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

public class SFAdministratorCertifications {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// call WDM
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//launch URL
		ChromeDriver driver = new ChromeDriver(options);
		driver.get(" https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		
		
		//Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");
		
		
		//click on the login button
		driver.findElement(By.id("Login")).click();
		
		String windowHandle = driver.getWindowHandle();
		//System.out.println(windowHandle);
		
		//click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		
		//Switch to the next window using Windowhandles
		Set<String> mobPublisher = driver.getWindowHandles();
		List<String> lstMobile = new ArrayList<String>(mobPublisher);
		String newWindow = lstMobile.get(1);
		System.out.println("New Window: " +newWindow);
		driver.switchTo().window(newWindow);
		
		//click on the confirm button in the redirecting page
		driver.findElement(By.xpath("(//div[@class='dialog']//button)[2]")).click();
		
		
		//Click on resource
		Shadow dom = new Shadow(driver);
		WebElement resource = dom.findElementByXPath("//span[text()='Learning']");
		resource.click();	
		
		
		//Mouse hover on learning on trailhead
		WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions builder = new Actions(driver);
		builder.moveToElement(trailHead).perform();
		//builder.click();
		
		//Click on Salesforce certifications
		Thread.sleep(30);
		WebElement salesForce = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.moveToElement(salesForce).perform();
		salesForce.click();
		
		//Click Certification Administrator
		WebElement Certification = driver.findElement(By.linkText("Administrator"));
		builder.scrollToElement(Certification).perform();
		
		
		
		WebElement advancedAdministrator = driver.findElement(By.linkText("Advanced Administrator"));
		builder.scrollToElement(advancedAdministrator).perform();
		String text = advancedAdministrator.getText();
		if(text.equals("Advanced Administrator")) {
			System.out.println("Certifications available");
		}
		
		
		//Take screenshot
		File source = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		
		//save it to local dir
		File dest = new File("./salesadmin.png");
		FileUtils.copyFile(source, dest);

		
		

	}

}
