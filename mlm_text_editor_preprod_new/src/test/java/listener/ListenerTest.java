 package listener;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import clm.interop.Log;
import clm.interop.actions.Screenshot;
import clm.interop.webdrivermanager.Driver;
import clm.interop.webdrivermanager.Session;
import listener.annotation.Xray;
import page.module.MLM;
import utils.extentreports.ExtentTestManager;
import utils.properties.PropertiesReader;	

public class ListenerTest implements IInvokedMethodListener, ITestListener, Screenshot {
	static String browser = (System.getProperty("execution.browser").length() == 0) ? PropertiesReader.openFile("test_execution").getProperty("browser").toUpperCase() : System.getProperty("execution.browser").toUpperCase();
	static int test_numéro = 1;
	boolean testSuccess = true;

	/* (non-Javadoc)
	 * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod, org.testng.ITestResult)
	 */
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod() && annotationPresent(method, Xray.class) ) {
			testResult.setAttribute("requirement", method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Xray.class).requirement()); 
			testResult.setAttribute("test", method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Xray.class).test());
			testResult.setAttribute("labels", method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Xray.class).labels());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean annotationPresent(IInvokedMethod method, Class clazz) {
	    boolean retVal = method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(clazz) ? true : false;
	    return retVal;
	}

	/* (non-Javadoc)
	 * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod, org.testng.ITestResult)
	 */
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod()) {
			if (!testSuccess) {
				testResult.setStatus(ITestResult.FAILURE);
			}
		}
	}

	public void onFinish(ITestContext context) {
	}
	
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription(), result.getMethod().getGroups());
		Log.info(String.format("Test n°%o démarrage : %s", test_numéro++, result.getMethod().getMethodName()));
	}
	
	public void onTestSuccess(ITestResult result) {
		Log.info("Le test est réussi");
		System.out.println("\n");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed", getScreenshotMedia());
		if (Session.saveSession == true) {
			Session.saveSession = false;
			Session.deleteSessionFile();
		}
		driverManagement();
	}
	
	public void onTestFailure(ITestResult result) {
		Log.info("Le test est fail");
		System.out.println("\n");
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		ExtentTestManager.getTest().log(Status.FAIL, "Test failed", getScreenshotMedia());
		driverManagement();
	}
	
	public void onTestFailedWithTimeout(ITestResult result) {
		Log.info("Le test est fail du à un timeOut");
		System.out.println("\n");
		if (ExtentTestManager.getTest() != null) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test failed du to a timeout", getScreenshotMedia());
		}
		driverManagement();
	}
	
	public void onTestSkipped(ITestResult result) {
		Log.info("Le test a été skippé");
		System.out.println("\n");
		ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
		driverManagement();
	}
	
	private void driverManagement() {
		WebDriver driver = Driver.getInstance();
		if (driver != null) {
			Log.info("Je ferme le navigateur");
			//MLM.getVersion();
			//MLM.getVM();
			//Driver.close();
		}
	}
}
