package pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import config.excelProgram;
import config.setup;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;
import javax.imageio.ImageIO;


public class HomePage {
	
	WebDriver driver;
	
	By listDevices = By.cssSelector("div[data-action='displayDetails']");
	By listPasses = By.cssSelector("div[data-action='displayUsage']");
		
	public HomePage(WebDriver driver){
		this.driver=driver;
	}
	public void clickDevices() throws IOException, InterruptedException{
		driver.findElement(listDevices).click();
		Thread.sleep(5000);
		setup.captureScreenShot(driver,"devicesPage_");
		setup.takeScreenshot(driver,"fullDevices_");
	}
	public void getMACID() throws IOException{
		List<String> macValues = new ArrayList<>();
		int i;
		for(i=1; 1<100; i++){
			try{
				String strXpath = ".//*[@id='APP']/div/div[2]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/ul/li["+i+"]/div[1]/div[2]/span[2]";
				String strMac = driver.findElement(By.xpath(strXpath)).getText();
				macValues.add(strMac);
			}
			catch (NoSuchElementException e){
				break;
			}
		}
		String userID = excelProgram.getExcelStringData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", "TestSheet", 1, 0);
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 0, 0, "User ID");
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 0, 1, "Number of Devices");
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 0, 2, "Number of Active Passes");
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 0, 3, "Device Details");
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 0, 4, "Pass Details");
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 1, 0, userID);
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 1, 1, String.valueOf(macValues.size()));
		for(int j=0;j<macValues.size();j++){
			excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, j+1, 3, macValues.get(j));			
		}		
	}
	public void clickPasses() throws IOException, InterruptedException{
		driver.findElement(listPasses).click();
		Thread.sleep(5000);
		setup.captureScreenShot(driver,"passesPage_");
		setup.takeScreenshot(driver,"fullPasses_");
	}
	public void getPassDetails() throws IOException{
		List<String> passDetails = new ArrayList<>();
		int i;
		for(i=1;i<100;i++){
			try{
				String passXpath = ".//*[@id='APP']/div/div[2]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div/ul/li["+i+"]/ul/li/div/div/span";
				String strPassname = driver.findElement(By.xpath(passXpath)).getText();
				passDetails.add(strPassname);
			}
			catch(NoSuchElementException e1){
				try{
					String passXpath1 = ".//*[@id='APP']/div/div[2]/div/div/div[1]/div/div[2]/div[3]/div/div/div/div/ul/li["+i+"]/ul/li/div/div/span[1]";
					String strPassname1 = driver.findElement(By.xpath(passXpath1)).getText();
					passDetails.add(strPassname1);	
				}
				catch(NoSuchElementException e2){
					break;
				}
			}
		}
		String userID = excelProgram.getExcelStringData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", "TestSheet", 1, 0);
		excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, 1, 2, String.valueOf(passDetails.size()));
		for(int j=0;j<passDetails.size();j++){
			excelProgram.setExcelData("C:\\Users\\vthond001c\\Desktop\\SelfService_TestData.xlsx", userID, j+1, 4, passDetails.get(j));			
		}		
	}	
}
