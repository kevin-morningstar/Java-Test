package testcases;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testbrowser<e> {

	// declare the Webdriver and Wait as public
	public WebDriver driver;
	public WebDriverWait wait = new WebDriverWait(driver, 120);
	
	//The Constructor who loads all the elements from the object.properties
	public testbrowser() throws IOException {
		FileReader reader = new FileReader("browser\\object.properties");	//Access the properties file
		Properties prop = new Properties(); //make the object of Properties
		prop.load(reader); //access the properties file with object
	}

	//Method to access the Chrome browser
	@BeforeTest
	@Parameters({ "browser" })
	public void beforeclass(String browser) throws Exception {
		System.out.println("Before Class");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	//Method to open flipkart.coma and login with valid credentials
	@Test
	@Parameters({ "username", "password" })
	public void login(String username, String password, Properties prop) throws IOException {
		System.out.println("Inside Test");
		driver.get("https://www.flipkart.com"); //Access the flipakrt.com
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS); //Wait till flipkart.com loads
		driver.findElement(By.xpath(prop.getProperty("userid"))).click();  //Click on User ID box to allow type the login id
		driver.findElement(By.xpath(prop.getProperty("userid"))).sendKeys(username);	//Enter the login id
		driver.findElement(By.xpath(prop.getProperty("userpassword"))).click();		 //Click on Password box to allow type the password
		driver.findElement(By.xpath(prop.getProperty("userpassword"))).sendKeys(password);	//Enter the password
		driver.findElement(By.xpath(prop.getProperty("login"))).click();		//Click on Login Button
	}

	//Method to find product in search box and click on appropriate product
	@Test(priority = 1, dependsOnMethods = { "login" })
	@Parameters({ "product", "product_name", "price" })
	public void find_product(String product, String product_name, Properties prop) throws InterruptedException {
		Thread.sleep(1000); //Sleep for 1 Second
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS); // Page load time for 120 seconds
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("searchBox")))).sendKeys(product); //Enter the product in search box
		driver.findElement(By.xpath("searchButton")).click(); //Click on Search button to search product
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("product_name")))).click();	//In product list click on appropriate product
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());	//Array created to handle multiple tabs in chrome browser
		driver.switchTo().window(tabs.get(1)); //Switch to next Tab in chrome browser
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);	// Page load time for 120 Seconds
	}

	@Test(priority = 2)
	@Parameters({ "price" })
	public void compare_price(int price, Properties prop) {
		String online_price = driver.findElement(By.xpath(prop.getProperty("onlinePrice"))).getText(); //Storing flipkart price as text in a variable
		int product_price = Integer.parseInt((online_price.substring(1))); //Removing rupee symbol and converting the price into integer
		System.out.println("Online Price: " + product_price); // Printing Online price
		System.out.println("My Price: " + price); // Printing my price
		if (price <= product_price) { //Comparing the both price if my price is less or equal then click on buy else print "Price is Different"
			System.out.println(product_price);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("buyNow")))).click(); //Click on Buy Now Button
		} else {
			System.out.println("Price is Diffrent");
		}
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Closing Driver");
		driver.quit();
	}
}