package page.module;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import clm.interop.webdrivermanager.Driver;
import net.rcarz.jiraclient.JiraException;
import page.models.BasePage;

public class MLM extends BasePage {
	private static String version = "Undefined";
	private static String VM = "Undefined";
	
	public static String getVM() {
		if (VM.equals("Undefined")) {
			driver.switchTo().defaultContent();
			List<WebElement> VMElement = driver.findElements(By.cssSelector(".version span"));
			if (VMElement.size() > 0) {
				String[] mlmVersionTexts = VMElement.get(0).getText().split(" ");
				VM = mlmVersionTexts[mlmVersionTexts.length - 1];
			}
		}
		return VM;
	}
	
	public static String getVersion() {
		if (version.equals("Undefined")) {
			driver.switchTo().defaultContent();
		//	Driver.getInstance().get("https://mlmpreprod.monlogicielmedical.com/suite-webapp/home.html");
			List<WebElement> VMElement = driver.findElements(By.cssSelector(".version span"));
			if (VMElement.size() > 0) {
				String[] mlmVersionTexts = VMElement.get(0).getText().split(" ");
				version = mlmVersionTexts[mlmVersionTexts.length - 2].replaceAll("[^0-9.]", "");
			}
		}
		return version;
	}
}
