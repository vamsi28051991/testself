package Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.excelProgram;
import pages.HomePage;
import pages.LoginPage;
import pages.recoveryPage;

public class TestSelfServiceLogin {
	WebDriver driver;
	LoginPage objLogin;
	recoveryPage objRecovery;
	HomePage objHome;
	
	@BeforeTest(alwaysRun=true)
	public void setup(){
		driver = config.setup.getWebDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.get("qa2.xfinity.com:46000/wod/selfservice/landing");
	}
	@Test(priority=0)
	public void clickOnLogin() throws InterruptedException, IOException{
		objLogin = new LoginPage(driver);
		objLogin.loginToSelfservice(excelProgram.getExcelStringData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", "TestSheet", 1, 0), excelProgram.getExcelStringData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", "TestSheet", 1, 1));
	}
	@Test(priority=1)
	public void proceedToHome() throws InterruptedException, IOException{
		objRecovery = new recoveryPage(driver);
		objRecovery.clickAskMeLater();
	}
	@Test(priority=2)
	public void selectDetailsinHomePage() throws InterruptedException, IOException{
		objHome = new HomePage(driver);
		objHome.getMACID();
		Thread.sleep(5000);
		objHome.clickPasses();
		Thread.sleep(5000);
		objHome.getPassDetails();
		driver.quit();
	}	
}
