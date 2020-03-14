package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class anno {
	
	@BeforeTest
	public void beforetest() {
		System.out.println("Before Test");
	}
	
	@BeforeSuite
	public void beforesuite() {
		System.out.println("Before Suite");
	}
	@BeforeClass
	public void beforeclass() {
		System.out.println("Before Class");
	}
	@BeforeMethod
	public void beforemethod() {
		System.out.println("Before Method");
	}
	
	@AfterTest
	public void aftertest() {
		System.out.println("After Test");
	}
	
	@AfterSuite
	public void aftersuite() {
		System.out.println("After Suite");
	}
	@AfterClass
	public void afterclass() {
		System.out.println("After Class");
	}
	@AfterMethod
	public void aftermethod() {
		System.out.println("After Method");
	}
	
	
	
}
