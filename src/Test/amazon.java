package Test;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class amazon {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		
		//Search
		WebElement searchText = driver.findElement(By.id("twotabsearchtextbox"));
		searchText.sendKeys("Samsung");
		
		WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));
		searchBtn.click();
		
		//Print Products and price
		List<WebElement> AllProducts = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> AllPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price-whole']"));
		
		for(int i=0; i<AllProducts.size();i++) {
			System.out.println(AllProducts.get(i).getText()+" ₹"+AllPrice.get(i).getText());	
		}
		String ParentWin= driver.getWindowHandle();
		String ExpectedValue = AllProducts.get(0).getText();
		
		//Clicking 1st product link
		AllProducts.get(0).click();
		
		//Switching window
		Set<String> AllWindowHandler =	driver.getWindowHandles();
		for(String win : AllWindowHandler ) {
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);
				}
			}

			WebElement title = driver.findElement(By.id("productTitle"));

			String str = title.getText();

			//Title Verification
			if(str.equals(ExpectedValue)) {
				System.out.println("Passed: Title matched");
			}
			else {
				System.out.println("Failed: Title did not match");
			}
			driver.quit();
		}
}
