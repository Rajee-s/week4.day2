package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertifications {

	public static void main(String[] args) throws InterruptedException {
		
		// call WDM
		WebDriverManager.chromedriver().setup();
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
		
		
		//Click on resource
		Shadow dom = new Shadow(driver);
		WebElement resource = dom.findElementByXPath("//span[text()='Learning']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(resource));
		resource.click();	
		
		
		// Select SalesForce Certification Under Learnings
		WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions builder = new Actions(driver);
		builder.moveToElement(trailHead).perform();
		
		
		//Click on Salesforce certifications
		Thread.sleep(30);
		WebElement salesForce = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.moveToElement(salesForce).perform();
		salesForce.click();
		
		// Choose Your Role as Salesforce Architect
		
		WebElement architect = driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]"));
		builder.moveToElement(architect).perform();
		architect.click();
		
		
		// Get the Text(Summary) of Salesforce Architect 
		String salesforceArchitect = driver.findElement(By.xpath("//div[contains(@class,'cert-site_text slds-text-align--center')]")).getText();
		System.out.println("SalesForce Architect Summary: "+salesforceArchitect);
		
		
		// Get the List of Salesforce Architect Certifications Available
		
		List<WebElement> archCertification = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
			for(int i = 0; i<archCertification.size(); i++) {
				String text = archCertification.get(i).getText();
					System.out.println("Saleforce Architect Certification: "+text);
		
			}
		
	
		// Click on Application Architect 
		driver.findElement(By.linkText("Application Architect")).click();
		
		//Get the List of Certifications available
		List<WebElement> AppCertification = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
		for(int j = 0; j<AppCertification.size(); j++) {
			String certification = AppCertification.get(j).getText();
				System.out.println("Application Architect Certification: "+certification);
		}

	}

}
