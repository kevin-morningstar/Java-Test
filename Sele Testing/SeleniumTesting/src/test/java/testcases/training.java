package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class training {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com");
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		FileInputStream fis = new FileInputStream(new File("D://test1.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		XSSFRow row = sh.getRow(1);
		String uname = row.getCell(0).getStringCellValue();
		String pwd = row.getCell(1).getStringCellValue();

		driver.findElement(By.cssSelector("input._2zrpKA._1dBPDZ")).click();
		driver.findElement(By.cssSelector("input._2zrpKA._1dBPDZ")).sendKeys(uname);
		driver.findElement(By.cssSelector("input._2zrpKA._3v41xv._1dBPDZ")).click();
		driver.findElement(By.cssSelector("input._2zrpKA._3v41xv._1dBPDZ")).sendKeys(pwd);
		driver.findElement(By.cssSelector("button._2AkmmA._1LctnI._7UHT_c")).click();
	}

}
