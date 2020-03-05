package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testbrowser {

	public WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite will always execute prior to all annotations or tests in the suite.");
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void setup(String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "driver\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void beforeClass(){
		System.out.println("Before Class will always execute prior to Before Method and Test Method ");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();		
	}

	@BeforeMethod
	@Parameters({ "usename", "password"})
	public void beforeMethod(String uname, String pwd) throws IOException{
		FileInputStream fis = new FileInputStream(new File("D://test1.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		XSSFRow row = sh.getRow(1);
		uname = row.getCell(0).getStringCellValue();
		pwd = row.getCell(1).getStringCellValue();
		driver.get("https://www.flipkart.com");
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("input._2zrpKA._1dBPDZ")).click();
		driver.findElement(By.cssSelector("input._2zrpKA._1dBPDZ")).sendKeys(username);
		driver.findElement(By.cssSelector("input._2zrpKA._3v41xv._1dBPDZ")).click();
		driver.findElement(By.cssSelector("input._2zrpKA._3v41xv._1dBPDZ")).sendKeys(password);
		driver.findElement(By.cssSelector("button._2AkmmA._1LctnI._7UHT_c")).click();
	}

	@Test(priority = 1)
	@Parameters({ "product", "product_name", "price" })
	public void testCase(String product, String product_name, int price) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement searchBox, productName;
		String online_price;
		int product_price;
		Thread.sleep(1000);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("LM6RPg")));
		searchBox.sendKeys(product);
		driver.findElement(By.className("vh79eN")).click();
		productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(product_name)));
		productName.click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		
		online_price = driver.findElement(By.cssSelector("div._1vC4OE._3qQ9m1")).getText();
		product_price = Integer.parseInt((online_price.substring(1)));
		System.out.println("Online Price: " + product_price);
		System.out.println("My Price: " + price);
		if (price <= product_price) {
			System.out.println(product_price);
			wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("button._2AkmmA._2Npkh4._2kuvG8._7UHT_c")))
					.click();
		} else {
			System.out.println("Price is Diffrent");
		}

	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		driver.quit();
	}
}