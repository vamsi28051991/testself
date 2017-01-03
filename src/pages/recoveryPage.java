package pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import config.setup;

public class recoveryPage {
	WebDriver driver;
	
	By btnAskMeLater = By.id("askMeLater");
	
	public recoveryPage(WebDriver driver){
		this.driver=driver;
	}
	
	public String checkPageTitle(){
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
	public void clickAskMeLater() throws InterruptedException, IOException{
		String pageTitle = driver.getTitle();
		if(pageTitle.equals("Recovery Options")){
			Thread.sleep(5000);
			driver.findElement(btnAskMeLater).click();
		}
		else{
			Thread.sleep(5000);
			setup.captureScreenShot(driver,"RecoveryPageNOTFound_");
			//driver.quit();
		}
	}	
}
