package page.models;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clm.interop.Log;

public class PageTraitementsComplementaires extends BasePage{
	String urlPageTraitementsComplementaires = "https://mlmpreprod.monlogicielmedical.com/jenomi/formularioNonDrugPrescriptions.htm?p";
	By champ_description_examen = By.id("textSearchExams");
	By bouton_ajouter_examen = By.id("addExamButton");
	By bouton_rechercher = By.id("buttonSearch");
	By champ_nombre_de_seances = By.id("numSession");
	By champ_date_fin_seances = By.id("dateFin");
	By label_nombre_seances = By.cssSelector("[for='numSession']");
	By bouton_valider = By.id("boton2.validar");
	By champ_date_precise = By.id("dataConcretDate");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public PageTraitementsComplementaires() {
		Log.info("On affiche le Module Traitements");
		wait.until(ExpectedConditions.urlContains(urlPageTraitementsComplementaires));
		Log.info("Le Module Traitement est bien affiché");
	}
	
	
	public void rechercherTraitementComplementaire(String descriptionTaitement) {
		Log.info("On recherche un traitement complementaire");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_description_examen));
		click(champ_description_examen);
		clearInput(champ_description_examen);
		input(descriptionTaitement, champ_description_examen);
		Log.info("On a rempli le champ description pour rechercher le traitement");
	}
	
	public void ouvrirDossierExamen(String nomDossier) {
		Log.info("On ouvre le dossier contenant l'examen :" + nomDossier);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text() = '%s']",nomDossier))));
		click(By.xpath(String.format("//span[text() = '%s']",nomDossier)));
		
	}
	
	public void cliquerBoutonRechercher() {
		Log.info("On clique sur le bouton rechercher pour afficher les resultats de recherche");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_rechercher));
		click(bouton_rechercher);
		Log.info("On attends la liste des resultats de recherche du traitement");
	}
	
	public void selectionnerTraitementsComplementaires(String traitement) {
		Log.info("On selectionne un traitement dans sur la liste des resultats : "+ traitement);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text() = \"%s\"]",traitement))));
		click(By.xpath(String.format("//span[text() = \"%s\"]",traitement)));
		//wait.until(ExpectedConditions.attributeContains(By.xpath(String.format("(//span[text() = \"%s\"])[1]/../../.",traitement)), "class", "x-tree-selected"));
		Log.info("Le traitement sélectionné sur la liste des resultats est : "+ traitement);	
	}
	
	public void cocherOptionDeLOrdonnance(String option) {
		Log.info("On va cocher une l'option de l'ordonnance : "+ option);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//label[text() = '%s']",option))));
		click(By.xpath(String.format("//label[text() = '%s']",option)));
		Log.info("L'option de l'ordonnance : " + option + "a été cochée");
	}
	
	public void selectionnerDateDeDebutDuTraitement(String moment) {
		Log.info("On va selectionner la date de debut du traitement");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//label[contains(text(),'%s')]",moment))));
		click(By.xpath(String.format("//label[contains(text(),'%s')]",moment)));
		Log.info("On a selectionner la date de debut du traitement :" + moment);
	}
	public void ajouterDatePrecise(String date) {
		Log.info("On va ajouter la date précise de debut de la prise :" + date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_date_precise));
		input(date, champ_date_precise);
		click(By.xpath("//label[contains(text(),'Date précise')]"));
		Log.info("On a bien ajouter la date précise de debut de la prise :" + date);
	}
	
	
	public void ajouterNombreDeSeances(String nbSeances) {
		Log.info("On va ajouter un nombre de seance : "+ nbSeances);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_nombre_de_seances));
		input(nbSeances, champ_nombre_de_seances);
		click(label_nombre_seances);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//div[@title='Radiographie de la mandibule']/../following-sibling::td[contains(@class,'numSessions')])[1]/div[contains(text(),'%s')]",nbSeances))));
		Log.info("Le nombre de seance : " + nbSeances + "a été ajouté");
	}
	
	public void valider() {
		Log.info("On va cliquer sur le bouton Valider ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_valider));	
		//click(bouton_valider);
		WebElement element = driver.findElement(bouton_valider);
		js.executeScript("arguments[0].click();",element);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_valider));
	}
	
	
	public void ajouterDateFin(String date) {
		Log.info("On va ajouter la date de fin : " + date);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_date_fin_seances));
		input(date, champ_date_fin_seances);
		Log.info("La date de fin " + date + "a été ajoutée");	
	}
	
	public void cocher_ALD(String exam) {
		Log.info("on coche ALD case");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]/following::img[contains(@src,'img/botones/common/i_checkNo')]", exam))));
	click(By.xpath(String.format("//div[contains(text(),'%s')]/following::img[contains(@src,'img/botones/common/i_checkNo')]", exam)));
	
	
	}
	
	public void gerer_les_ordonnances_en_format_editeur_text() {
		Log.info("on clique sur configuration ordonnances pour ouvrir fenetres gestion des ordonnances");
		click(By.xpath("//span[text()='Ordonnance(s)']/preceding::div[1]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
	    Log.info("on clique sur ptions de l'impression et validation");
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(By.xpath("//span[contains(text(),'impression et de validation')]/preceding::div[1]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioRunReportManager")));
	if(driver.findElement(By.id("radioRunReportManager")).isSelected()) {
		Log.info("l'editeur de texte est selectionne");
	}else {
		Log.info("selectionne l'editeur de texte");
		click(By.id("radioRunReportManager"));
	}
	
	
	click(By.xpath("//button[text()='OK']"));
	}
	
	public void gerer_les_ordonnances_en_format_PDF() {
		Log.info("on clique sur configuration ordonnances pour ouvrir fenetres gestion des ordonnances");
		click(By.xpath("//span[text()='Ordonnance(s)']/preceding::div[1]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
	    Log.info("on clique sur ptions de l'impression et validation");
		click(By.xpath("//span[contains(text(),'impression et de validation')]/preceding::div[1]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioRunPDF")));
	if(driver.findElement(By.id("radioRunPDF")).isSelected()) {
		Log.info("l'editeur de texte est selectionne");
	}else {
		Log.info("selectionne l'editeur de texte");
		click(By.id("radioRunPDF"));
	}
	
	click(By.xpath("//button[text()='OK']"));
	}
	
	public void validerEtImprimer() {
		Log.info("On va cliquer sur le bouton Valider et imprimer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Valider et imprimer']")));	
		//click(bouton_valider);
		WebElement element = driver.findElement(By.xpath("//button[text()='Valider et imprimer']"));
		js.executeScript("arguments[0].click();",element);
		wait(10000);
		
	
	}
	public void ajout_des_commentaire_dans_l_ordonnance(String text) {
		Log.info("on ajout des comentaires dans la partie commentaire des ordonnaces");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comentario")));
		input(text,By.id("comentario"));
	}
	
	
	private void wait(int seconds){
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
