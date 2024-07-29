package page.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clm.interop.Log;
import page.models.BasePage;

public class ModuleAjoutProblemeDeSante extends BasePage{

	By popup_creation_probleme_sante = By.id("winAvisoAnyadirEpis");
	By bouton_reponse_oui_creation_probleme_sante = By.xpath("//div[contains(@class, 'padding') and text()='Oui']");
	By popup_parametre_recherche_probleme_sante = By.id("winSelectorCie");
	By radio_bouton_dico_CIM10 = By.id("radio_CIM10");
	By champ_recherche_description = By.id("descripcion");
	By bouton_rechercher = By.id("boton2.buscar");
	By frame_popup_parametre_recherche_probleme_sante = By.id("frameSelectorCie");
	By popup_confirmation_ajout_probleme_sante = By.id("winConfirmEpisode");
	By message_probleme_sante_ajoute_correctement = By.xpath("//div[contains(text(),'Problème de santé ajouté correctement')]");
	By bouton_ajouter_un_nouveau_probleme_de_sante = By.xpath("//button[contains(text(),'Ajouter un nouveau')]");
	Actions action = new Actions(driver);
	By radio_bouton_dico_CISP = By.id("radio_CISP");
	By radio_bouton_dico_AMM = By.id("radio_AMM");
	By bouton_retour_au_dossier_medical = By.xpath("//button[contains(text(),'Retour au dossier médical')]");
	By bouton_ok_valider = By.id("boton2.aceptar");
	By champ_date_debut_probleme_sante = By.cssSelector("[id='epiDateIni']");
	By champ_date_fin_probleme_sante = By.cssSelector("[id='epiDateFin']");
	By checkbox_deviendra_un_antecedent = By.cssSelector("[id='epiCheckTraspas']");
	By radio_bouton_ALD = By.id("aldCheck");
	By bouton_annuler_popup_ald = By.xpath("//button[contains(@class, 'x-btn-text') and text() = 'Annuler']");
	By popup_ald = By.id("winAddEpiALD");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
	public ModuleAjoutProblemeDeSante(){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_popup_parametre_recherche_probleme_sante));	
	}
	
	
	public void ajouterUnProblemeDeSantéDuDictionnaire(String problemeSante,String codeEtProblemeSante) throws InterruptedException {
		Log.info("Nous allons ajouter le probleme de santé du dictionnaire :" + problemeSante);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_description));
	Thread.sleep(3000);
		input(problemeSante, champ_recherche_description);
		click(bouton_rechercher);			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",codeEtProblemeSante))));
		click(By.xpath(String.format("//span[contains(text(),'%s')]",codeEtProblemeSante)));
		WebElement element_selected = driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]/../..",codeEtProblemeSante)));
		//wait.until(ExpectedConditions.attributeContains(element_selected,"class","x-tree-selected"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ok_valider));
		click(bouton_ok_valider);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_confirmation_ajout_probleme_sante));
		wait.until(ExpectedConditions.visibilityOfElementLocated(message_probleme_sante_ajoute_correctement));
		Log.info("Le probleme de santé " + problemeSante + " a été ajouté avec succes" );
	
	}
	
	

	
	public void rechercherEtSelectionnerUnProblemedeSante(String problemeSante,String codeEtProblemeSante) throws InterruptedException {
		Log.info("Nous allons rechercher le probleme de santé du dictionnaire :" + problemeSante);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_description));
		input(problemeSante, champ_recherche_description);
		click(bouton_rechercher);
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text() = '%s']",codeEtProblemeSante))));
		click(By.xpath(String.format("//span[text() = '%s']",codeEtProblemeSante)));
		WebElement element_selected = driver.findElement(By.xpath(String.format("//span[text() = '%s']/../..",codeEtProblemeSante)));
		//wait.until(ExpectedConditions.attributeContains(element_selected,"class","x-tree-selected"));
		Log.info("Le probleme de santé du dictionnaire :" + problemeSante + "a été trouvé");
	}
	
	public void cliquerBoutonOKPourValiderAjoutProblemeDeSante() {
		Log.info("Nous allons cliquer sur le bouton Ok pour valider l'ajout du probleme de santé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ok_valider));
		click(bouton_ok_valider);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_confirmation_ajout_probleme_sante));
		wait.until(ExpectedConditions.visibilityOfElementLocated(message_probleme_sante_ajoute_correctement));
		Log.info("Le probleme de santé  a été ajouté correctement");
	}
	

	public void ajouterDateDebutProblemeSante(String date) {
		Log.info("Nous allons ajouter la date de début du probleme de santé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_date_debut_probleme_sante));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDateTime datedebut = LocalDateTime.now().minusDays(nBJours);
//	    driver.findElement(champ_date_debut_probleme_sante).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),formatter.format(datedebut));		
		driver.findElement(champ_date_debut_probleme_sante).sendKeys("\u0008");
		driver.findElement(champ_date_debut_probleme_sante).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),date);
		Log.info("La date de début du probleme de santé a été ajouté");
	}
	
	public void ajouterDateFinProblemeSante(String date) {
		Log.info("Nous allons ajouter la date de fin du probleme de santé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_date_fin_probleme_sante));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDateTime datedebut = LocalDateTime.now().minusDays(nBJours);
//		driver.findElement(champ_date_fin_probleme_sante).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),formatter.format(datedebut));
		driver.findElement(champ_date_fin_probleme_sante).sendKeys("\u0008");
		driver.findElement(champ_date_fin_probleme_sante).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),date);
		Log.info("La date de fin du probleme de santé a été ajouté");
	}	
	
	public void desactiverOptionDeviendraUnAntecedent() {
		Log.info("Nous allons désactiver l'option coché par défaut Deviendra un antécédent");
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox_deviendra_un_antecedent));
		click(checkbox_deviendra_un_antecedent);
		wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(checkbox_deviendra_un_antecedent),false));
		Log.info("Nous avoons désactiver l'option coché par défaut Deviendra un antécédent");
	}
	
	
	public void deselectionnerTousLesDictionnaires() {
		Log.info("On vérifie quel dictionnaire est selectionné des l'ouverture du module recherche");
		List<WebElement> radioButtons = driver.findElements(By.cssSelector("[name='idTipoBusqueda']"));
		for (WebElement radioButton : radioButtons) {
			if (radioButton.isSelected()) {
				radioButton.click();
				wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.cssSelector("[name='idTipoBusqueda']")),false));
			}
		}
		Log.info("Tous les dictionnaires selectionnés par défaut sont bien deselectionnés");
	}
	

		
	public void selectionnerDictionnaire(String dicoName) {
		Log.info("On selectionne le dictionnaire :" + dicoName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//input[@id='radio_%s']",dicoName))));
		//wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.xpath(String.format("//input[@id='radio_%s']",dicoName))),false));
		WebElement dico =driver.findElement(By.xpath(String.format("//input[@id='radio_%s']",dicoName)));
		if(dico.isSelected()) {
			Log.info(String.format("Dico  %s est selectionne",dicoName));
		}else {
		click(By.xpath(String.format("//input[@id='radio_%s']",dicoName)));
		}
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath(String.format("//input[@id='radio_%s']",dicoName))));
		Log.info("Le dictionnaire " + dicoName + " a été selectionné avec succès");
	}
	
	
	public void deSelectionnerDictionnaire(String dicoName) {
		Log.info("On deselectionne le dictionnaire :" + dicoName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//input[@id='radio_%s']",dicoName))));
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath(String.format("//input[@id='radio_%s']",dicoName))));
		click(By.xpath(String.format("//input[@id='radio_%s']",dicoName)));
		wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.xpath(String.format("//input[@id='radio_%s']",dicoName))),false));
		Log.info("Le dictionnaire " + dicoName + " a été déselectionné avec succès");
	}
	
	
	public void confirmationAjoutProblemeDeSanteOK() {
		Log.info("On verifie que le probleme de santé a été bien ajouté");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ajouter_un_nouveau_probleme_de_sante));
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_confirmation_ajout_probleme_sante));
		Log.info("Le probleme de santé a bien été ajouté");
	}
	
	public void validerPopupAjouterUnNouveauProblemeDeSante() {
		Log.info("On valide la popup pour commencer a ajouter un nouveau probleme de santé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ajouter_un_nouveau_probleme_de_sante));
		click(bouton_ajouter_un_nouveau_probleme_de_sante);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_confirmation_ajout_probleme_sante));
		Log.info(" la popup pour commencer a ajouter un nouveau probleme de santé a été validé");	
	}
	

	public void retourAuDossierMedical() {
		Log.info("On clique sur le bouton retour au dossier medical pour retourner au dossier medical");
		click(bouton_retour_au_dossier_medical);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_confirmation_ajout_probleme_sante));
		Log.info("On est bien retourné au dossier medical");
	}
	
	public void activerOptionALD() {
		Log.info("On clique sur la checkbox ALD pour activer l'option ALD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(radio_bouton_ALD));
		click(radio_bouton_ALD);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_annuler_popup_ald));
		click(bouton_annuler_popup_ald);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_annuler_popup_ald));
		wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(radio_bouton_ALD),true));
		Log.info("l'option ALD a bien été activée");
	}
	

	

}
