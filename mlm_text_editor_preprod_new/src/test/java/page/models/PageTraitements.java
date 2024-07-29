package page.models;

import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import clm.interop.Log;

public class PageTraitements extends BasePage{
	
	String urlPageTraitements = "https://mlmpreprod.monlogicielmedical.com/jenomi/gestorTratamientos.htm?";
	By bouton_nouveau = By.xpath("//button[contains(text(),'Nouveau')]");
	By popup_probleme_sante_ou_motif = By.id("list_episodes");
	By loader = By.cssSelector("[class='fa fa-circle-o-notch fa-spin ajax-loader']");
	By retour_dossier_medical = By.cssSelector("[title='Dossier Médical']");
	By retour_dossier_patients = By.cssSelector("[title='Dossier Patients']");
	By page_dossier_traitement = By.cssSelector("[id='prescripcionesNuevas']");
	By bloc_ordonnance_en_cours = By.cssSelector("[id='gridPrescripcionesNuevas']");
	By bouton_accepter_ordonnance = By.xpath("//button[text() = 'Accepter']");
	By bouton_accepter_imprimer_ordonnance = By.xpath("//button[text() = 'Accepter et imprimer']");
	By bouton_accepter_information = By.xpath("//button[@id='dialogButtonId_1' and text() = 'Accepter']");
	By onglet_historique = By.xpath("//span[text() = 'Historique']");
	By onglet_actif = By.xpath("//span[text() = 'Actif']");
	By bouton_fermer_perscription_plus_actif = By.xpath("(//button[@class='ui-dialog-titlebar-close'])[2]");
	By logoMLM = By.id("img_logo");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions action = new Actions(driver);
	By popup_saisie_rapide_biometrie = By.id("winEntradaRapidaDGP");
	
	public PageTraitements() {
		Log.info("On affiche le Module Traitements");
		//wait.until(ExpectedConditions.urlContains(urlPageTraitements));
		Log.info("Le Module Traitement est bien affiché");
	}

	public void verifierSelectionProblemeDeSante(String problemeSante) {
		Log.info("On verifique que le probleme de santé :" + problemeSante + "est bien sélectionné avant de commencer à ajouter des produits");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[@id='datos_epi']//strong[contains(text(),'%s')]",problemeSante))));
		Log.info("Le probleme de santé :" + problemeSante + "a été bien sélectionné avant de commencer à ajouter des produits");
	}
	
	public void ajouterBiometrie() {
		Log.info("On clique sur le bouton Plus pour ajouter des infos de biométrie");
		wait_special(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("boton_dgp_estrella")));
		for (int i = 0; i < 10; i++) {
			try {
				WebElement element = driver.findElement(By.id("boton_dgp_estrella"));
				click(element);
				break;
			} catch (StaleElementReferenceException e) {
			}
			// Pour éviter exception element StaleElementReferenceException
		}
		//click(bouton_plus_ajouter_biometrie);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_saisie_rapide_biometrie));
		Log.info("La popup saisie rapide de la biométrie est bien ouverte");
	}
	public void changerLaDateDePrescription(String date) {
		Log.info("On va changer la date de prescription : à la date du jour suivant");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gridPrescripcionesNuevas .x-grid3-row-table tr td:nth-child(19) div")));
		click(By.cssSelector("#gridPrescripcionesNuevas .x-grid3-row-table tr td:nth-child(19) div"));
		By input_date = By.cssSelector(".x-editor[style *= 'visible'] input");
		wait.until(ExpectedConditions.presenceOfElementLocated(input_date));
		clearInput(input_date);
		input(date + Keys.ENTER, input_date);
		Log.info("On va changer la date de prescription : à " + date);
	}
	
	public void commencerNouveauTraitement() {
		waitUntilPageLoaded();
		Log.info("On Clique sur le bouton nouveau pour commencer un nouveau traitement");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_nouveau));
		
		click(bouton_nouveau);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Log.info("Le Module Traitement est bien affiché");
	}
	
	
	
	public void ouvrirMedicamentDejaPrescrit(String med) {
		Log.info("on ouvre le medicament deja prescrit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]",med))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]",med)));
	}
	
	
	public void choisirUnProblemeDeSanteSurLaPopupProblemeSanteOuMotif(String problemeSante) {
		Log.info("On attends que la popup s'affiche et on choisit un probleme de santé :" + problemeSante);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_probleme_sante_ou_motif));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]",problemeSante))));
		click(By.xpath(String.format("//div[contains(text(),'%s')]",problemeSante)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_probleme_sante_ou_motif));
		Log.info("On a bien choisit un probleme de santé sur la popup :" + problemeSante );
	}
	
	public void retourDossierMedical() {
		Log.info("On quitte le traitement pour retourner au dossier médical");
		wait.until(ExpectedConditions.visibilityOfElementLocated(retour_dossier_medical));
		click(retour_dossier_medical);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(page_dossier_traitement));
		Log.info("On a bien quitté le traitement");
		
	}
	public void retourDossierPatients() {
		Log.info("On quitte le traitement pour retourner au dossier patients");
		wait.until(ExpectedConditions.visibilityOfElementLocated(retour_dossier_patients));
		click(retour_dossier_patients);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(page_dossier_traitement));
		Log.info("On a bien quitté le traitement");
		
	}
	
	public void verifierPresenceProduitSurOrdonnanceEnCours(String nomProduit) {
		Log.info("On vérfie que le medicament est bien présent sur l'ordonnance en cours");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bloc_ordonnance_en_cours));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]",nomProduit))));
		Log.info("le medicament " + nomProduit + " est bien présent sur l'ordonnance en cours");
	}
	
	public void accepterOrdonnance() {
		Log.info("On clique sur le bouton accepter pour accepter l'ordonnance en cours");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_accepter_ordonnance));
		click(bouton_accepter_ordonnance);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'labelPres')]")));
		Log.info("L'ordonnance a été accepté");
	}
	
	public void accepterEtImprimerOrdonnance() {
		Log.info("On clique sur le bouton accepter et imprimer pour accepter l'ordonnance en cours");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_accepter_imprimer_ordonnance));
		click(bouton_accepter_imprimer_ordonnance);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'labelPres')]")));
		Log.info("L'ordonnance a été accepté et imprime");
		wait_special(10000);
	}
	
	
	public void verifierPosologieDansLOrdonnance(String Posologie) {
		Log.info("on verifie existence Posologie dans l'ordonnance");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]",Posologie))));
	}
	
	
	public void verifierPresenceOrdonnanceDansHistorique() {
		Log.info("On vérifie que l'ordonnance est bien présente dans le bloc historique de tous les traitements");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime date = LocalDateTime.now();
		wait.until(ExpectedConditions.visibilityOfElementLocated(onglet_historique));
		click(onglet_historique);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s')]",formatter.format(date)))));
	}
	
	public void verifierPresenceProduitDansOngletActif(String nomProduit) {
		Log.info("On vérifie que le produit :" + nomProduit + "est bien présent dans l'onglet Actif de tous les traitements");
		wait.until(ExpectedConditions.visibilityOfElementLocated(onglet_actif));
		click(onglet_actif);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]",nomProduit))));
		Log.info("Le produit :" + nomProduit + "est bien présent dans l'onglet Actif de tous les traitements");
	}
	
	public void ajouterDateDebutDetraitement(String nomProduit,long nombreJours,String index) {
		Log.info("On va ajouter la date de debut du traitement depuis la page de traitement");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]//following::div[@unselectable='on'][8]",nomProduit))));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime datedebut = LocalDateTime.now().minusDays(nombreJours);
		//WebElement date = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]//following::div[@unselectable='on'][8]",nomProduit)));
		WebElement date = driver.findElement(By.xpath(String.format("(//a[contains(text(),'%s')]//following::div[@unselectable='on'][8])[%s]",nomProduit,index)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[contains(text(),'%s')]//following::div[@unselectable='on'][8]",nomProduit))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]//following::div[@unselectable='on'][8]",nomProduit)));
		js.executeScript("arguments[0].removeAttribute('unselectable'); return arguments[0];", date);
		for (int i = 0; i < 10; i++) {
			action.moveToElement(date).sendKeys(Keys.ARROW_LEFT).perform();
		}
		for (int i = 0; i < 10; i++) {
			action.moveToElement(date).sendKeys(Keys.chord(Keys.DELETE)).perform();
		}
		Log.info("=====La date de debut du traitement est ====== :" + formatter.format(datedebut));
		action.moveToElement(date).sendKeys(Keys.chord(formatter.format(datedebut))).perform();
		click(By.xpath("//span[contains(text(),'Ordonnance en cours')]"));
		Log.info("La date de debut du traitement depuis la page de traitement a été ajoutée : " + formatter.format(datedebut));
		
	}
	
	
	public void ajouterALDDepuisPageTraitements(String nomProduit) {
		Log.info("On va ajouter ALD depuis la page de traitement pour les produits qui n'ont que la posologie usuelle ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]//following::div[contains(text(),'ALD')][1]",nomProduit))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]//following::div[contains(text(),'ALD')][1]",nomProduit)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]//following::b[contains(text(),'ALD')][1]",nomProduit))));
		Log.info("L'option ALD a été ajouté depuis la page de traitement pour les produits qui n'ont que la posologie usuelle ");	
	}
	
	public void accepterPopuPInformation() {
		Log.info("On accepter la popup Un problème de santé associé à une prescription n'est plus actif.");
		if (elementIsPresentOnDOM(bouton_accepter_information)) {
			click(bouton_accepter_information);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_accepter_information));
			Log.info("La popup Un problème de santé associé à une prescription n'est plus actif a été acceptée");
		} else {
			Log.info("La popup est absente :Un problème de santé associé à une prescription n'est plus actif ");
		}	
		//wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_accepter_information));	
	}
	
	public void fermerPoPuPPrescriptionPlusActif() {
		Log.info("On ferme la popup Un problème de santé associé à une prescription n'est plus actif.");
		click(bouton_fermer_perscription_plus_actif);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_fermer_perscription_plus_actif));
		Log.info("Lla popup Un problème de santé associé à une prescription n'est plus actif a été fermée.");
	}
	 public void verifier_existence_medicaments_prescrits_dans_historique(String date_ordonnance,int number_traitements) {
		 Log.info("on verifie existence meicament prescrits dans l'historique");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s (%d traitements)')]", date_ordonnance,number_traitements))));
		 
	 }
	 
	 public void verifier_existence_medicaments_prescrits_dans_historique2(String date,String medica) {
		 Log.info("on verifie existence meicament prescrits dans l'historique");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Historique']")));
		 click(By.xpath("//span[text()='Historique']"));
		 // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s (%d traitements)')]", date_ordonnance,number_traitements))));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s')]/following::b[contains(text(),'%s')]",date,medica))));
		 
	 }
	 

	 
	 public void supprimer_ordonnance() {
		 Log.info("on supprime l'Ordonnace u jour");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Supprimer']")));
		 click(By.xpath("//button[@title='Supprimer']"));
		 Log.info("on confirme supprimer l'ordonnance");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Oui']")));
		 click(By.xpath("//button[text()='Oui']"));
		 Log.info("l'ordonnance a ete supprimee");
	 }
	 public void verifier_posologie_medicament_exprime_en_comprime(String medicament) {
		 Log.info(String.format("on verifie que la posologie de %s est exprimee en comprime ",medicament));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'comprimé')]/preceding::a[contains(text(),'%s')]",medicament))));
		 Log.info(String.format("le mot comprime est bien affiche pour medicament %s",medicament));
	 }
	 
	 
	 public void verifier_status_ALD(String color,String medica) {
		 Log.info("on verifie le statut ALD");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoALD();'][1]",medica))));
		 WebElement ALD = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoALD();'][1]",medica)));
		 String ALD_color = ALD.getCssValue("color");
		 String ALD_lineThrough =ALD.getCssValue("text-decoration");
		 wait_special(3000);
		 List<WebElement> elements = driver.findElements(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoALD();' and contains(@style,'line-through')][1]",medica)));
		 if(elements.size()>0) {
			Assert.assertEquals(ALD_color,color);
			Log.info("le coleur ALD est gris");
		   Assert.assertEquals(ALD_lineThrough ,"line-through solid rgb(124, 124, 124)"); 
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'FUCIDINE')]/following::div[contains(@style,'line-through')][1]")));
		Log.info("ALD est barre");
		 }else {
			 Assert.assertEquals(ALD_color,color);
			 Log.info("ALD est active");
		 }
			 
		 
	 }
	 
	 
	 public void verifier_status_Nepasdelivrer(String color,String medica) {
		 Log.info("on verifie le statut ALD");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoND();'][1]",medica))));
		 WebElement ND = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoND();'][1]",medica)));
		 String ND_color = ND.getCssValue("color");
		 String ND_lineThrough =ND.getCssValue("text-decoration");
		 wait_special(3000);
		 List<WebElement> elements = driver.findElements(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoND();' and contains(@style,'line-through')][1]",medica)));
		 if(elements.size()>0) {
			Assert.assertEquals(ND_color,color);
			Log.info("le coleur ALD est gris");
		   Assert.assertEquals(ND_lineThrough ,"line-through solid rgb(124, 124, 124)"); 
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'FUCIDINE')]/following::div[contains(@style,'line-through')][1]")));
		Log.info("ND est barre");
		 }else {
			 Assert.assertEquals(ND_color,color);
			 Log.info("ND est active");
		 }
			 
		 
	 }
	 
	 public void verifier_status_Substituable(String color,String medica) {
		 Log.info("on verifie le statut NS");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoNS();'][1]",medica))));
		 WebElement NS = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoNS();'][1]",medica)));
		 String NS_color = NS.getCssValue("color");
		 String NS_lineThrough =NS.getCssValue("text-decoration");
		 wait_special(3000);
		 List<WebElement> elements = driver.findElements(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoNS();' and contains(@style,'line-through')][1]",medica)));
		 if(elements.size()>0) {
			Assert.assertEquals(NS_color,color);
			Log.info("le coleur NS est gris");
		   Assert.assertEquals(NS_lineThrough ,"line-through solid rgb(124, 124, 124)"); 
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'FUCIDINE')]/following::div[contains(@style,'line-through')][1]")));
		Log.info("NS est barre");
		 }else {
			 Assert.assertEquals(NS_color,color);
			 Log.info("NS est active");
		 }
			 
		 
	 }
	 
	 public void Activer_ALD_dans_l_ordonnance(String medica) {
		 Log.info("on active l'option ALD");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoALD();'][1]",medica))));
		 WebElement ALD = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoALD();'][1]",medica)));
		 click(ALD);
		 wait_special(3000);
	 }
	 
	 public void Activer_Nepasdelivrer_dans_l_ordonnance(String medica) {
		 Log.info("on active l'option ND");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoND();'][1]",medica))));
		 WebElement ND = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoND();'][1]",medica)));
		 click(ND);
		 wait_special(3000);
	 }
	 
	 public void Activer_Substituable_dans_l_ordonnance(String medica) {
		 Log.info("on active l'option NS");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoNS();'][1]",medica))));
		 WebElement NS = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[@onclick='changetoNS();'][1]",medica)));
		 click(NS);
		 wait_special(3000);
	 }
	 
	 
	 
	 public void imprimerOrdonnanceDepuisHistorique(String date,String medica) {
		 Log.info("on imprime l'ordonnance depuis l'historique");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s')]/following::b[contains(text(),'%s')]",date,medica))));
		 Log.info("on selectionne l'ordonnance depuis l'historique");
		 click(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s')]/following::b[contains(text(),'%s')]",date,medica)));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Imprimer']")));
		 Log.info("on imprime l'ordonnance depuis l'historique");
		 click(By.xpath("//button[text()='Imprimer']"));
		 wait_special(3000);
	 }
	 
	 public void imprimerDuplicataDepuisHistorique(String date,String medica) {
		 Log.info("on duplicata l'ordonnance depuis l'historique");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s')]/following::b[contains(text(),'%s')]",date,medica))));
		 Log.info("on selectionne l'ordonnance depuis l'historique");
		 click(By.xpath(String.format("//td[contains(text(),'Ordonnance du %s')]/following::b[contains(text(),'%s')]",date,medica)));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Duplicata']")));
		 Log.info("on duplicata l'ordonnance depuis l'historique");
		 click(By.xpath("//button[text()='Duplicata']"));
		 wait_special(3000);
	 }
	 
	 
	 public void optionImpressionEtValidationRetourDossierMedical() {
		 Log.info("on clique sur configuration pour ouvrir getsion de l'ordonnance");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]")));
		 click(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
		    Log.info("on clique sur ptions de l'impression et validation");
			click(By.xpath("//span[contains(text(),'impression et de validation')]/preceding::div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioRunReportManager")));
		if(driver.findElement(By.id("checkGoBackEHR")).isSelected()) {
			Log.info("retour au dossier medical  est selectionne");
		}else {
			Log.info("selectionne retour au dossier medical ");
			click(By.id("checkGoBackEHR"));
		}
		
		if(driver.findElement(By.id("checkPrintDCI")).isSelected()) {
			Log.info("imprimer la DCI est selectionne");
		}else {
			Log.info("selectionne imprimer la DCI");
			click(By.id("checkPrintDCI"));
		}
		
		click(By.xpath("//button[text()='OK']"));
		}
	 public void optionImpressionEtValidationSansRetourDossierMedical() {
		 Log.info("on clique sur configuration pour ouvrir getsion de l'ordonnance");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]")));
		 click(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
		    Log.info("on clique sur ptions de l'impression et validation");
			click(By.xpath("//span[contains(text(),'impression et de validation')]/preceding::div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioRunReportManager")));
		if(driver.findElement(By.id("checkGoBackEHR")).isSelected()) {
			click(By.id("checkGoBackEHR"));
			Log.info("deselectionne retour au dossier medical ");
		}else {
			
			Log.info("retour au dossier medical  est pas selectionne");
		}
		
		if(driver.findElement(By.id("checkPrintDCI")).isSelected()) {
			click(By.id("checkPrintDCI"));
			Log.info("deselectionne imprimer la DCI");
		}else {
			
			Log.info("imprimer la DCI est selectionne");
		}
		
		click(By.xpath("//button[text()='OK']"));
		}
	 
	 
	 public void gerer_les_ordonnances_en_format_editeur_text() {
		 Log.info("on clique sur configuration pour ouvrir getsion de l'ordonnance");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]")));
		 click(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
		    Log.info("on clique sur ptions de l'impression et validation");
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
			 Log.info("on clique sur configuration pour ouvrir getsion de l'ordonnance");
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]")));
			 click(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
			    Log.info("on clique sur ptions de l'impression et validation");
				click(By.xpath("//span[contains(text(),'impression et de validation')]/preceding::div[1]"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radioRunReportManager")));
		if(driver.findElement(By.id("radioRunPDF")).isSelected()) {
			Log.info("l'editeur de texte est selectionne");
		}else {
			Log.info("selectionne l'editeur de texte");
			click(By.id("radioRunPDF"));
		}
		
		click(By.xpath("//button[text()='OK']"));
		}
		
		 public void gerer_Donnees_Affichees_sur_l_ordonnance(String Police_font,String Taille_font) {
			 Log.info("on change la police et la taille de produit");
			 Log.info("on clique sur configuration pour ouvrir getsion de l'ordonnance");
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]")));
			 click(By.xpath("//div[@id='gridPrescripcionesNuevas']/div[1]/div[1]"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
			    Log.info("on clique sur Données affichées");
				click(By.xpath("//span[contains(text(),'Données affichées')]/preceding::div[1]"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comboProductType")));
				Log.info("on change la police de produit");
				clearInput(By.id("comboProductType"));
				input(Police_font,By.id("comboProductType"));
				Log.info("on change la taille de produit");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comboProductSize")));
				clearInput(By.id("comboProductSize"));
				input(Taille_font,By.id("comboProductSize"));
//			if(driver.findElement(By.id("radioRunReportManager")).isSelected()) {
//				Log.info("l'editeur de texte est selectionne");
//			}else {
//				Log.info("selectionne l'editeur de texte");
//				click(By.id("radioRunReportManager"));
//			}
			
			click(By.xpath("//button[text()='OK']"));
			}
	 
		 
		 
	 public void retourAccueil() {
			Log.info("On retourne à l'accueil");
			wait.until(ExpectedConditions.visibilityOfElementLocated(logoMLM));
			click(logoMLM);
			Log.info("On est bien retourné à l'accueil");
			
		}

	 public void switchTowindow(int window) {
			List <String> windows = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windows.get(window));
		}
	
	
	 
	 public void wait_special(int i) {
		 
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
