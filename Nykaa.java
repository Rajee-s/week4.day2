package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		
		// call WDM
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
				
		//launch URL
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
		//Mouseover on Brands and Search L'Oreal Paris
		Thread.sleep(3000);
		WebElement brands = driver.findElement(By.xpath("//div[@id='category_arrowUp']//following::a"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		
		
		Thread.sleep(3000);
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		
		//Click L'Oreal Paris
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='css-ov2o3v']//a")).click();
		
		
		//Check the title contains L'Oreal Paris
		String title = driver.getTitle();
			if(title.contains("Buy L'Oreal Paris products")){
				System.out.println("The title has L'Oreal Paris");
		}
		
		//Click sort By and select customer top rated
		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
			
		
		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//div[@class='filter-open css-1kwl9pj']//span")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		
		//Click->Concern->Color Protection
		WebElement concern = driver.findElement(By.xpath("//span[text()='Concern']"));
		builder.scrollToElement(concern);
		concern.click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.className("css-xrzmfa")).click();
		
		
		//GO to the new window
		Set<String> selectShampoo = driver.getWindowHandles();
		List<String> lstWindow = new ArrayList<String>(selectShampoo);
		String secondWindow = lstWindow.get(1);
		
		driver.switchTo().window(secondWindow);
		
		//select size as 175ml
		WebElement selSize = driver.findElement(By.xpath("//select[@class='css-2t5nwu']"));
		Select dropdown = new Select(selSize);
		dropdown.selectByValue("0");
		
		//Print the MRP of the product
		WebElement price = driver.findElement(By.xpath("//span[@class='css-1jczs19']"));
		String mrp = price.getText();
		String actual=mrp.substring(1, 4);
		System.out.println("MRP Rs: "+actual);
		
		//Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		
		//Go to Shopping Bag 
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		
		
		//Print the Grand Total amount
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='css-acpm4k']")));
		Thread.sleep(3000);
		
		WebElement grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/following::div"));
		String totalPrice=grandTotal.getText();
		String actualPrice=totalPrice.substring(1, 4);
		System.out.println("Grand total amount Rs: "+actualPrice);
		
	
		//Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		driver.switchTo().defaultContent();
		
		//Click on Continue as Guest
		driver.findElement(By.xpath("//div[text()='Checkout as guest']/following-sibling::button")).click();

		//Check if this grand total is the same in step 14
		WebElement finalTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following::span"));
		String total = finalTotal.getText();
		
		System.out.println("Final amount Rs: "+total);
			if(total.equals(actualPrice)) {
			
				System.out.println("The prices are same");
			
		}
			else {
				
				System.out.println("The prices differ");	
				
		}
		
		//Close all windows
		driver.quit();
	}

}
