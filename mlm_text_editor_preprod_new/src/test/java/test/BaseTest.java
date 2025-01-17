 package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import clm.interop.Log;
import clm.interop.enumerator.Browser;
import clm.interop.webdrivermanager.Driver;
import clm.interop.webdrivermanager.Session;
import listener.annotation.Xray;
//import page.models.Driver;
import utils.properties.PropertiesReader;

@Listeners({listener.ListenerExecution.class, listener.ListenerTest.class})
public abstract class BaseTest {
	static String browser = (System.getProperty("execution.browser").length() == 0) ? PropertiesReader.openFile("test_execution").getProperty("browser").toUpperCase() : System.getProperty("execution.browser").toUpperCase();
	static String url = (System.getProperty("execution.url").length() == 0) ? PropertiesReader.openFile("test_execution").getProperty("url") : System.getProperty("execution.url");

	@BeforeSuite
	public void beforeSuite() {
	}
	
	@BeforeGroups
	public void beforeGroup() {
	}
	
	@BeforeMethod(alwaysRun=true)
	public void BaseTest_Access_URL(Method method) {
		WebDriver driver = Driver.getInstance(Browser.valueOf(browser), method.getAnnotation(Xray.class).test());
		if (Session.saveSession == false || !Session.isSessionFileExist()) {
			Log.debug(String.format("Accéder à '%s' avec '%s'", url, browser));
			driver.get(url);
		}
	}
}
