package config;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class setup {
	
	public static WebDriver getWebDriver(){
		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("-incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(capabilities);*/
		return new InternetExplorerDriver();
	}
	public static void captureScreenShot(WebDriver driver, String strName) throws IOException{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\vthond001c\\Downloads\\Selenium_Screenshots\\"+strName+System.currentTimeMillis()+".png"));
	}
	public static void takeScreenshot(WebDriver driver, String strName) throws IOException{
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(10000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "PNG", new File("C:\\Users\\vthond001c\\Downloads\\Selenium_Screenshots\\"+strName+System.currentTimeMillis()+".png"));
	}
}
