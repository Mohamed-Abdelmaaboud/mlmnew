package page.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import clm.interop.actions.Click;
import clm.interop.actions.Element;
import clm.interop.actions.Frame;
import clm.interop.actions.Input;
import clm.interop.actions.Present;
import clm.interop.actions.Screenshot;
import clm.interop.actions.Scroll;
import clm.interop.actions.Wait;
import clm.interop.webdrivermanager.Driver;

public abstract class BasePage implements Click, Scroll, Frame, Wait, Screenshot, Input, Present, Element {
	protected static WebDriver driver = null;
	protected static WebDriverWait wait = null;
	
	public BasePage() {
		driver = Driver.getInstance();
		wait = Driver.getWait();
		switchToDefault();
		waitUntilPageLoaded();
	}
}
