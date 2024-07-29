package page.module;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clm.interop.Log;
import page.models.BasePage;

public class BioServeur extends BasePage{
	
	
	String url_bioserveur = "https://secure.bioserveur.com";
	
	 
	public void seconnecte_au_bioserveur() {
		Log.info("on se connecte au bioserveur");
		driver.get(url_bioserveur);
		
	}
	
	public void seConnecterParLoginEtMotDePasseBioServeur(String login_bioserveur,String password_bioserveur) {
		Log.info("on login on bioserveur page");
		input(login_bioserveur,By.name("logincode"));
		input(password_bioserveur,By.name("password"));
		click(By.name("btnConnect"));
	}
	
	public void envoyer_resultats_labratoire_au_MLM() {
		Log.info("on envoie des resultat au mlm");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("menu"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menuPARAMETRES_V2")));
		click(By.id("menuPARAMETRES_V2"));
		driver.switchTo().parentFrame();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("body"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Si vous souhaitez recevoir vos résultats par Messagerie sécurisée, cliquez ici']")));
		click(By.xpath("//a[text()='Si vous souhaitez recevoir vos résultats par Messagerie sécurisée, cliquez ici']"));
		
		Log.info("on clique sur envoyer 3 fichier pour envoyer les fichier en MLM");
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(windows.get(1));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Cliquez ici pour envoyer 3 dossiers de test sur votre adresse HPRIM Net.']")));
		click(By.xpath("//a[text()='Cliquez ici pour envoyer 3 dossiers de test sur votre adresse HPRIM Net.']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Annulation']")));
		click(By.xpath("//input[@value='Annulation']"));
		driver.switchTo().window(windows.get(0));
	
	}
	
}