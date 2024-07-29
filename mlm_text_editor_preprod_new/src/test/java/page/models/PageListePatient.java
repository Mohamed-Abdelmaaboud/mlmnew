package page.models;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clm.interop.Log;

public class PageListePatient extends BasePage {
   JavascriptExecutor js = (JavascriptExecutor)driver;
	By retourAccueil = By.xpath("//a[@title='Accueil']");
	
   public PageListePatient () {
		switchToFrame("iframeListado");
	}
	public void acceder_au_dossier_medical_du_premier_patient_par_prenom(String prenom) {
		prenom = prenom.toUpperCase();
		By patient = By.xpath(String.format("//a/strong[text() = '%s'] | //*[@class = 'listaDePacientes_col_nombre']/a", prenom));

		Log.info(String.format("J'accède au dossier patient de '%s'", prenom));
		click(patient);
	}
	public void acceder_au_dossier_medical_du_premier_patient() {
		By first_patient = By.xpath("//table[@id='listaDePacientes']/tbody/tr/td[3]/a[contains(text(),'HPRIM')]");
		Log.info("J'accède au premier dossier patient");
		wait.until(ExpectedConditions.visibilityOfElementLocated(first_patient));
		js.executeScript("arguments[0].click()",driver.findElement(first_patient));
		//click(first_patient);
		driver.switchTo().defaultContent();
	}
	public void retourAccueil() {
		Log.info("On retourne à l'accueil");
		wait.until(ExpectedConditions.visibilityOfElementLocated(retourAccueil));
		click(retourAccueil);
	}
}
