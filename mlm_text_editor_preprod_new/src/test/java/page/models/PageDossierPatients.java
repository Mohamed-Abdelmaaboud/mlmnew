package page.models;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clm.interop.Log;

public class PageDossierPatients extends BasePage {
	String urlPageDossierPatients = "https://mlmpreprod.monlogicielmedical.com/jenomi/listadoPacientes.htm";
	By bouton_nouveau_dossier = By.id("actionButton_NEW_PATIENT");
	By frame_liste_patient = By.id("iframeListado");
	By logoMLM = By.id("img_logo");
	By champ_recherche_par_le_nom = By.id("apellido1");
	By bouton_rechercher = By.id("btnSearchPat");
	By bouton_inactiver_dossier = By.cssSelector("[data-id='deletePatientButton']");
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	
	public PageDossierPatients() {
		wait.until(ExpectedConditions.urlContains(urlPageDossierPatients));
	}
	
	public void créerNouveauDossier() {
		Log.info("Je clique sur le bouton nouveau dossier");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_nouveau_dossier));
		click(bouton_nouveau_dossier);
	}
	
	public void verifierPatientBienCréé(String nom ) {
		Log.info("On vérifie que le patient a été bien crée et est actif");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_liste_patient));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/..//following::i[@data-original-title='Actif']",nom))));
		Log.info("Le patient a été bien crée et est actif");
		switchToDefault();
	}
	
	public void retourAccueil() {
		Log.info("On retourne à l'accueil");
		wait.until(ExpectedConditions.visibilityOfElementLocated(logoMLM));
		click(logoMLM);
		Log.info("On est bien retourné à l'accueil");
		
	}
	
	public void recherchePatient(String patient) {
		Log.info("on recherche le patient");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_par_le_nom));
		input(patient, champ_recherche_par_le_nom);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_rechercher));
		click(bouton_rechercher);
	}
	public void InactiverPatient(String nomPatient) {
		Log.info("On va inactiver le dossier du patient");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeListado")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//td[@class='listaDePacientes_col_nombre']/a[contains(text(),'%s')]/following::td[13]//div[@data-original-title='Autres options']",nomPatient))));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//td[@class='listaDePacientes_col_apellido1']//a[contains(text(),'%s')]/following::td[11]/div[@data-original-title='Autres options']",nomPatient))));
		//WebElement patient = driver.findElement(By.xpath(String.format("//td[@class='listaDePacientes_col_apellido1']//a[contains(text(),'%s')]/following::td[11]/div[@data-original-title='Autres options']//button",nomPatient)));
		List  patients = driver.findElements(By.xpath(String.format("//td[@class='listaDePacientes_col_nombre']/a[contains(text(),'%s')]",nomPatient)));
		By autre_option =By.xpath(String.format("//td[@class='listaDePacientes_col_nombre']/a[contains(text(),'%s')]/following::td[13]//div[@data-original-title='Autres options']//button",nomPatient));
		if(patients.size()>0) {
		click(autre_option);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_inactiver_dossier));
		js.executeScript("arguments[0].click()", driver.findElement(bouton_inactiver_dossier));
		
		//click(bouton_inactiver_dossier);
		switchToDefault();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Oui']")));
		js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//button[text()='Oui']")));
	//	click(By.xpath("//button[text()='Oui']"));
		Log.info("On a inactive le patient");	
		}else {
			
			Log.info("y a pas de patients HPRIM pour inactiver");
			switchToDefault();
		}
	}
	
}
