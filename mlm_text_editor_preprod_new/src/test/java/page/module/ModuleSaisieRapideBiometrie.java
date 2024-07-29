package page.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clm.interop.Log;
import page.models.BasePage;

public class ModuleSaisieRapideBiometrie extends BasePage {
	By frame_saisie_rapide_biometrie = By.id("biodEntry");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	By champ_TA_max = By.id("ext-comp-1002");
	By bouton_OK = By.xpath("//button[contains(text(),'OK')]");

	public ModuleSaisieRapideBiometrie() {
		Log.info("On ouvre la popup pour commencer la saisie de la biométrie");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_saisie_rapide_biometrie));
		Log.info("La popup saisie biométrie est affichée");
	}
	
	public void ajouterValeurBiometrie(String biometrie,String valeur) {
		Log.info("On va ajouter la valeur " + valeur + "à la biométrie :" + biometrie);
		for (int i = 0; i < 10; i++) {
			try {
				WebElement element = driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]/..//following::div[1]",biometrie)));
			//	click(element);
				js.executeScript("arguments[0].click()", element);
				break;
			} catch (StaleElementReferenceException e) {
			}
			// Pour éviter exception element StaleElementReferenceException
		}
		input(valeur, champ_TA_max);
		//click(By.xpath("//div[contains(text(),'TA Max')]"));
		Log.info("On a bien ajouté la valeur " + valeur + "à la biométrie :" + biometrie);
		
	}
	

	public void ajouterValeurDateBiometrie(long semaines) {
		LocalDateTime date_jour = LocalDateTime.now();
      LocalDateTime date = LocalDateTime.now().minusWeeks(semaines);
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	  String dureeGrossesse = formatter.format(date);
	   String existant_date = formatter.format(date_jour);
	   click(By.xpath("//div[contains(text(),'Grossesse (Oui/Non)')]"));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",existant_date))));
	   click(By.xpath(String.format("//div[text()='%s']",existant_date)));
	   By date_field = By.xpath("//input[@id='fechaEditor']");
	   clearInput(date_field);
	   input(dureeGrossesse,date_field);
	   Log.info(String.format("on a renseigne la duree de la grossesse %s",dureeGrossesse));
	
}
	public void validerSaisie() {
		Log.info("On clique sur le bouton Ok pour valider la saisie de la biométrie");
		switchToDefault();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_OK));
		click(bouton_OK);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_OK));
		Log.info("On a validé la saisie");
	}
	
	
	
	private void wait(int temps) {
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
