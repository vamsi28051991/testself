package pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import config.setup;

public class LoginPage {
	
	WebDriver driver;
	
	By lblXfinitylogo = By.className("xfinity-logo no-error");
	By textUsername = By.id("user");
	By textPassword = By.id("passwd");
	By btnSignin = By.id("sign_in");
	By loginError = By.id("error");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean checkPageTitle(){
		String loginPageTitle = driver.getTitle();
		boolean retVal;
		if(loginPageTitle.equals("Sign in to XFINITY")){
			retVal = true;
		}
		else
			retVal = false;
		return retVal;
	}
	public void setUsername(String strUsername){
		driver.findElement(textUsername).sendKeys(strUsername);
	}
	public void setPassword(String strPassword){
		driver.findElement(textPassword).sendKeys(strPassword);
	}
	public void clickSignin(){
		driver.findElement(btnSignin).click(); 
	}
	public void loginToSelfservice(String strUsername, String strPassword) throws InterruptedException, IOException{
		//boolean pageValue = this.checkPageTitle();
		String loginPageTitle = driver.getTitle();
		//if(pageValue=true){
		if(loginPageTitle.equals("Sign in to XFINITY")){
			this.setUsername(strUsername);
			this.setPassword(strPassword);
			Thread.sleep(5000);
			this.clickSignin();
			Thread.sleep(5000);
			if(driver.getTitle().equals("Sign in to XFINITY")){
				if(driver.findElement(loginError).getText().equals("The username and password entered do not match. Please try again.")){
					setup.captureScreenShot(driver,"InvalidLogin_");
					driver.quit();
				}
			}
		}
		else{
			Thread.sleep(5000);
			setup.captureScreenShot(driver,"PageNOTFound_");
			driver.quit();
		}
		/*else{
			Thread.sleep(5000);
			captureScreenShot(driver,"UnknownError_");
			driver.quit();
		}*/
	}	
}
