package listener;

import static utils.extentreports.ExtentManager.getExtentReports;
import static utils.extentreports.ExtentManager.setSystemInfo;

import java.net.MalformedURLException;

import org.testng.IExecutionListener;

import clm.interop.Log;
import clm.interop.webdrivermanager.Driver;
import clm.interop.webdrivermanager.Session;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import page.module.MLM;
import utils.devcon.Execution;

public class ListenerExecution implements IExecutionListener {
	
	@Override
	public void onExecutionStart() {
		Execution.disableAllVitaleReader();
	}
//	private void setJiraTestExecutionVersion() {
//		String testExecutionId = System.getProperty("jira.testexecution");
//		BasicCredentials credential = new BasicCredentials("sa_gen_ci", "C8cFi6cPF7G2auqXy787");
//		JiraClient jiraClient = new JiraClient("https://jirachs.cegedim.com/", credential);
//		
//		if (!testExecutionId.isEmpty()) {
//			try {
//				jiraClient.getIssue(testExecutionId).update().fieldAdd(Field.VERSIONS, MLM.getVersion()).execute();
//			} catch (JiraException e) {
//				Log.error("Erreur sur la jira", e);
//			}
//		}
//	}
	
	@Override
	public void onExecutionFinish() {
		if (!Session.saveSession) {
			//setJiraTestExecutionVersion();
			if (Driver.getInstance() != null) {
			//	setSystemInfo("Version", MLM.getVersion());
			//	setSystemInfo("VM", MLM.getVM());
			//	Driver.close();
			}
			getExtentReports().flush();
		}
	}
}

	