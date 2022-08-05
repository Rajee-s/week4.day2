package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws IOException, InterruptedException {
		
				// call WDM
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
						
				//launch URL
				ChromeDriver driver = new ChromeDriver(options);
				driver.get("https://www.snapdeal.com/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
				//Go to Mens Fashion
				Thread.sleep(3000);
				WebElement mensFashion = driver.findElement(By.xpath("//span[@class='catText']"));
				Actions builder = new Actions(driver);
				builder.moveToElement(mensFashion).perform();
				
				
				//Go to sports shoes
				WebElement sportShoes = driver.findElement(By.xpath("//span[text()='Sports Shoes']"));
				builder.moveToElement(sportShoes).perform();
				sportShoes.click();
				
				//Get the count of sports shoes
				String count = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
				String actual=count.substring(1, 12);
				System.out.println("Count of Sports Shoes: " + actual);
				
				//Click training shoes
				driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
						
				//sort low to high
				driver.findElement(By.xpath("//div[contains(text(),'Popularity')]")).click();
				driver.findElement(By.xpath("//li[@class='search-li']")).click();
				
				//check if the items are sorted correctly
				//String first = driver.findElement(By.xpath("//span[@class='lfloat product-price']")).getText();
				//String second = driver.findElement(By.xpath("(//span[@class='lfloat product-price'])[2]")).getText();
				
				
				//Select the price range(900-1200)
				driver.findElement(By.name("fromVal")).clear();
				driver.findElement(By.name("fromVal")).sendKeys("900");
				driver.findElement(By.name("toVal")).clear();
				driver.findElement(By.name("toVal")).sendKeys("1200"); 
				driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
				
				//filter with color navy
				Thread.sleep(3000);
				//WebElement color = driver.findElement(By.xpath("//span[contains(@class,'filter-color-tile')]/following::a"));
				//WebElement color = driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label[1]"));
				//builder.scrollToElement(color).perform();
				//color.click();
				driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label[1]")).click();
				
				//Mouse hover on first resulting training shoes
				Thread.sleep(3000);
				WebElement firstResult = driver.findElement(By.xpath("//p[@class='product-title']"));
				builder.moveToElement(firstResult).perform();
				
				
				//click quick view button
				driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
				
				
				//print the cost and the discounted percentage
				Thread.sleep(3000);
				WebElement price = driver.findElement(By.xpath("//div[@class='product-desc-price pdp-e-i-PAY-r ']/span"));
				String cost = price.getText();
				WebElement newPrice = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span"));
				String discount = newPrice.getText();
				System.out.println("The cost of the product is " +cost+ " and the discounted price is: " +discount);
				
				//Take the snapshot of shoes
				
				File source = driver.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
				
				//save it to local dir
				File dest = new File("./snapdeal.png");
				FileUtils.copyFile(source, dest);
				
				//close the current window
				driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
				
				//close the main window
				//driver.close();;
	}

}
