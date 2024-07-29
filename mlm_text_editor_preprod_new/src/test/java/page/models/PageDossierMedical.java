package page.models;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import clm.interop.Log;

public class PageDossierMedical extends BasePage{
	
	String urlDossierMedical = "https://mlmpreprod.monlogicielmedical.com/jenomi/tapizTheme.htm";
	By bouton_confirmer_automatisation_hr = By.id("dialogButtonId_AUTOMATIC_HR_CONSENT_OK");
	By bouton_annuler_automatisation_hr = By.id("dialogButtonId_AUTOMATIC_HR_CONSENT_NOK");
	By bouton_ne_plus_afficher_automatisation_hr = By.id("dialogButtonId_AUTOMATIC_HR_CONSENT_NOKNAA");
	By popup_automatisation_hr = By.id("automaticHRConsentDialog");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	By bouton_reponse_oui_creation_probleme_sante = By.xpath("//div[contains(@class, 'padding') and text()='Oui']");
	By popup_creation_probleme_sante = By.id("winAvisoAnyadirEpis");
	By popup_parametre_recherche_probleme_sante = By.id("winSelectorCie");
	By bouton_Profil = By.id("userOptionsBtn");
	By bouton_deconnexion = By.xpath("//a[contains(text(),'Déconnexion')]");
	By menu_dossier_medical = By.xpath("//li[contains(text(),'Dossier Médical')]");
	By bouton_plus_prescription_de_medicaments = By.xpath("//div[contains(@class,'x-tool x-tool-plus prescripciones')]");
	By bouton_plus_prescription_complentaires = By.xpath("//div[contains(@class,'x-tool x-tool-plus NonDrugPrescription')]");
	By bouton_plus_prescription_vaccins = By.xpath("//div[contains(@class,'x-tool x-tool-plus vacunas')]");
	By link_prescription_de_medicaments = By.xpath("//a[contains(text(),'Prescriptions de médicaments')]");
	By page_dossier_medical = By.cssSelector("[id='cuerpo']");
	By bouton_plus_ajout_probleme_sante = By.xpath("//div[@id='episodiosPan']//div[contains(@class,'x-tool x-tool-plus')]");
	By bouton_afficher_tous_les_problemes_de_sante = By.cssSelector("[id='btnEpiTodos']");
	By bouton_plus_ajout_allergies = By.xpath("//div[contains(@class,'x-tool x-tool-plus antecedentes')]");
	By popup_prescription_vaccins = By.cssSelector("[id='winPopup192882']");
	By bouton_plus_ajout_formulaire_specifiques = By.xpath("//div[contains(@class,'x-tool x-tool-plus protocolos')]");
	By bouton_plus_ajout_examen_laboratoire = By.xpath("//div[contains(@class,'x-tool x-tool-plus LabResults')]");
	By popup_selection_formulaire = By.id("winNuevo192885");
	By champ_recherche_formulaire = By.id("txtSearch");
	By bouton_module_diabetique = By.xpath("//button[@title='Module diabétique']");
	By bouton_rcv = By.id("btnEpiRCV");
	By bouton_plus_ajouter_biometrie = By.id("boton_dgp_estrella");
	By popup_saisie_rapide_biometrie = By.id("winEntradaRapidaDGP");
	By frame_saisie_rapide_biometrie = By.id("biodEntry");
	By popup_rcv = By.id("winRCVSelector");
	By bouton_ok_calcul_rcv = By.xpath("//span[contains(text(),'Ok')]");
	By alert_FH = By.xpath("//div[@aria-describedby='alertMessage']//div[@id='alertMessage']");
	By bouton_accepter_FH = By.id("dialogButtonId_1");
	By champ_rechercher_patient = By.id("searchPatientInput");
	By retourAccueil = By.id("img_logo");
	By bouton_accepter_rcv = By.id("modifRCV");
	By annuler_rcv = By.id("cancelRCV");
	LocalDateTime date_du_jour = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String date = formatter.format(date_du_jour);
	
	public PageDossierMedical() {;
		Log.info("On affiche le dossier medical");
		wait.until(ExpectedConditions.urlContains(urlDossierMedical));
		Log.info("Le dossier medical est bien affiché");
	}
	
	public void confirmerAutomatisatonHR() {
		if (elementIsPresentOnDOM(bouton_confirmer_automatisation_hr)) {
			Log.info("Oui la popup de l'automatisation de l'HR est présente");
			click(bouton_confirmer_automatisation_hr);
		} else {
			Log.info("Non la popup de l'automatisation de l'HR n'est pas présente");
			//click(By.xpath("//div[contains(text(),'Non')]"));
		} 
	}
	
	public void rechercherLePatientAvecDateDeNaissanceEtOuvrirSonDossierMedical(String date_de_naissance,String patientName) {
		Log.info("On recherche le patient avec le nom : "+ patientName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_rechercher_patient));
		input(date_de_naissance, champ_rechercher_patient);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName))));
		click(By.xpath(String.format("//li[@class='list-group-item'][1]//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName)));
		Log.info("On a bien trouvé le patient avec la date de naissance : "+ patientName);	
	}
	
	
	public void repondreQuestionPopupCreationProblemeDeSante(String reponse) {
		Log.info("Nous allons repondre à la question sur la popup Création d´un problème de santé : " + reponse);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_creation_probleme_sante));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class, 'padding') and text()='%s']",reponse))));
		click(By.xpath(String.format("//div[contains(@class, 'padding') and text()='%s']",reponse)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_creation_probleme_sante));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format("//div[contains(@class, 'padding') and text()='%s']",reponse))));
		Log.info("Nous bien avons repondu à la question sur la popup Création d´un problème de santé : " + reponse);
	}
	
	
	public void attendreAffichagePopupParametreRechercheProblemeSante() {
		Log.info("Nous allons attendre que la popup des parametres de recherche de probleme de santé s'affiche" );
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_parametre_recherche_probleme_sante));
		Log.info("La popup des parametres de recherche de probleme de santé s'est bien affichée,on peut donc ajouter les problèmes de santé" );
	}
	
	public void afficherTousLesProblemesDeSante() {
		Log.info("Nous allons afficher tous les problemes de santé en cliquant sur le bouton Tous" );
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_afficher_tous_les_problemes_de_sante));
		click(bouton_afficher_tous_les_problemes_de_sante);
		wait.until(ExpectedConditions.attributeContains(bouton_afficher_tous_les_problemes_de_sante, "class", "x-item-disabled"));
		Log.info("Tous les problemes de santé ont été bien affiché" );
	}
	
	public void verifierDeclenchementRCV() {
		Log.info("On vérifie que la RCV est déclanché automatiquement" );
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_rcv));
		Log.info("La RCV est bien déclanché automatiquement" );
	}
	public void validerCalculRisqueCardioVasculaireRCV() {
		Log.info("On va valider le calcul de la RCV" );
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ok_calcul_rcv));
		click(bouton_ok_calcul_rcv);
		Log.info("On a validé le calcul de la RCV" );
	}
	public void validerRisqueCardioVasculaireRCV() {
		Log.info("On valide la RCV" );
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_accepter_rcv));
		click(bouton_accepter_rcv);
		Log.info("On a validé la RCV" );
	}
	
	public void annulerRisqueCardioVasculaireRCV() {
		Log.info("On annule la RCV" );
		wait.until(ExpectedConditions.visibilityOfElementLocated(annuler_rcv));
		click(annuler_rcv);
		Log.info("On a annulé la RCV" );
	}
	
	
	
	public void verifierProblemeSantéPrésentDansLeDossierMedical(String problemeSante) {
		Log.info("On vérfie que le probleme santé ajouté est bien présent dans le dossier medical" + problemeSante);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//font[contains(text(),'%s')])[1]",problemeSante))));
		Log.info("Le probleme santé est bien présent dans le dossier medical" + problemeSante);
	}
	
	public void seDeconnecter() {
		Log.info("On peut se deconnecter");
		click(bouton_Profil);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_deconnexion));
		click(bouton_deconnexion);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_deconnexion));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(menu_dossier_medical));
		Log.info("On est bien deconnecté,");
	}
	
	
	public void prescrireMedicaments() {
		Log.info("On va prescrire des medicaments");
		//wait.until(ExpectedConditions.presenceOfElementLocated(bouton_plus_prescription_de_medicaments));
		//js.executeScript("arguments[0].click();",driver.findElement(bouton_plus_prescription_de_medicaments));
		click(bouton_plus_prescription_de_medicaments);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_plus_prescription_de_medicaments));
		Log.info("La prescription  des medicaments peut commencer");
	}
	
	public void selectionnerProblemeDesante(String problemeDeSante) {
		Log.info("On va selectionner le probleme de santé " +problemeDeSante+ " pour y rajouter une prescription");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//font[contains(text(),'%s')])[1]",problemeDeSante))));
		click(By.xpath(String.format("(//font[contains(text(),'%s')])[1]",problemeDeSante)));
		wait.until(ExpectedConditions.attributeContains(By.xpath(String.format("(//font[contains(text(),'%s')])[1]/../../../..",problemeDeSante)), "class", "x-tree-selected"));
		Log.info("Le probleme de santé " +problemeDeSante+ " a été sélectionné on peut donc commencer la prescription");
	}
	
	public void ajouterNouveauProblemeDeSante() {
		Log.info("On clique sur le bouton plus pour ajouter un nouveau probleme de santé");
		click(bouton_plus_ajout_probleme_sante);
		Log.info("On peut commencer l'ajout du nouveau probleme de santé");		
	}
	public void ajouterDesAllergiesOuAntecedentsOuFacteursDeSante() {
		Log.info("On clique sur le bouton plus pour ajouter une allergie");
		click(bouton_plus_ajout_allergies);
		Log.info("On peut commencer l'ajout des allergies");		
	}	
	
	
	public void prescriptionsComplementaires() {
		Log.info("On va prescrire des complementaires");
		click(bouton_plus_prescription_complentaires);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_plus_prescription_complentaires));
		Log.info("La prescription  des complementaires peut commencer");	
	}
	
	public void verifierQueLaPrescriptionComplementaireEstBienAjouteeDansLeDossierMedical(String prescription,String pbSante ) {
		Log.info("On vérifie que la prescription de complementaires :" + prescription + "est bien ajoutée dans le dossier médical" );
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@class, 'spaceEmptyDescription') and text() = '%s']",prescription))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//span[contains(@class, 'spaceEmptyDescription') and text() = '%s']//following::div[text() = '%s'])[1]",prescription,pbSante))));
		Log.info("Lla prescription de complementaires :" + prescription + "est bien ajoutée dans le dossier médical" );
	}
	
	
	public void verifierQueLaPrescriptionMedicamenteuseEstBienAjouteeDansLeDossierMedical(String prescription,String date_prescription) {
		Log.info("On vérifie que la prescription de medicamenteuse :" + prescription + "est bien ajoutée dans le dossier médical" );
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@class, 'spaceEmptyDescription') and text() = '%s']",prescription))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//span[contains(@class, 'spaceEmptyDescription') and contains(text(),'%s')]//following::div[text() = '%s'])[1]",prescription,date_prescription))));
		Log.info("La prescription de medicamenteuse :" + prescription + "est bien ajoutée dans le dossier médical" );
	}
	
	
	public void verifierQueAllergieEstBienAjouteeDansLeDossierMedical(String age,String allergie) {
		Log.info("On vérifie que l'allergie : " + allergie + " est bien ajoutée dans le dossier médical" );
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//div[@title='%s--> %s']/span",age,allergie))));
		Log.info("L'allergie : " + allergie + " est bien ajoutée dans le dossier médical" );
	}
	
	public void prescriptionsVaccins() {
		Log.info("On va prescrire des vaccins");
		click(bouton_plus_prescription_vaccins);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_prescription_vaccins));
		Log.info("La popup de precsription des vaccins est affiché");	
	}
	
	public void ajouterDesFormulaireSpecifiques() {
		Log.info("On clique sur le bouton plus pour ajouter des formulaire spécifiques");
		click(bouton_plus_ajout_formulaire_specifiques);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_selection_formulaire));
		Log.info("On peut commencer l'ajout des formulaire spécifiques");		
	}	
	
	public void rechercherFormulaire(String nomFormulaire) {
		Log.info("On recherche un formulaire :" + nomFormulaire);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_formulaire));
		input(nomFormulaire, champ_recherche_formulaire);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//span[contains(text(),\"%s\")])[1]",nomFormulaire))));
		WebElement element = driver.findElement(By.xpath(String.format("(//span[contains(text(),\"%s\")])[1]",nomFormulaire)));
		js.executeScript("arguments[0].click();",element);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_selection_formulaire));
		Log.info("On a trouvé le formulaire :" + nomFormulaire);
	}

	public void verifierQueLeFormulaireEstBienPresentDansLeDossierMedical(String formulaire) {
		Log.info("On vérifie que le formulaire a été bien ajouté et est présent dans le dossier medical :" + formulaire);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),\"%s\")]",formulaire))));
		Log.info("Le formulaire : " + formulaire + " a été bien ajouté et est présent dans le dossier medical :" );
	}
	
	public void ouvrirModuleDiabetique() {
		Log.info("On clique sur le bouton d'accès au module diabétique pour ouvrir le module diabetique");
		wait.until(ExpectedConditions.elementToBeClickable(bouton_module_diabetique));
		click(bouton_module_diabetique);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_module_diabetique));
		Log.info("On a bien quitté le dossier medical");
	} 
	
	public void ouvrirExamenDeLaboratoire() {
		Log.info("On clique sur le bouton plus pour ouvrir la liste des examens de laboratoire");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_plus_ajout_examen_laboratoire));
		click(bouton_plus_ajout_examen_laboratoire);
		Log.info("On peut commencer l'ajout des examens de laboratoire");		
	}	
	
	public void ouvrirPrescriptionsMedicamenteuse(String prescription,String date_prescription) {
		Log.info("on selectionne une prescrptions et l'ouvrir");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@class, 'spaceEmptyDescription') and contains(text(),'%s')]//following::div[contains(text(),'%s')][1]",prescription,date_prescription))));
		rightClick(By.xpath(String.format("//span[contains(@class, 'spaceEmptyDescription') and contains(text(),'%s')]//following::div[contains(text(),'%s')][1]",prescription,date_prescription)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Nouveau']")));
		click(By.xpath("//a[text()='Nouveau']"));
	}
	
	
	
	public void verifierRCVActif() {
		Log.info("On vérifie que RCV est activé dans le dossier médical");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_rcv));
		Log.info("RCV est activé dans le dossier médical");
	}
	
	public void ajouterBiometrie() {
		Log.info("On clique sur le bouton Plus pour ajouter des infos de biométrie");
		wait(3000);
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
	
	public void verifierPresenceDonneesBiometriques(String donnee,String valeur) {
		Log.info("On vérifie que la valeur " + valeur + " de la biométrie : " + donnee + " est bien présente dans le dossier médicale ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]/../following::td[2]/div[contains(text(),'%s')]",donnee,valeur))));
		Log.info(" Ok La valeur " + valeur + " de la biométrie : " + donnee + " est bien présente dans le dossier médicale ");
	}
	
	public void verifierPresenceExamenLaboratoire(String examen) {
		Log.info("On vérifie que les prelevements de laboratoire sont bien présents dans le dossier médical");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='spaceEmptyDescription']")));
		Assert.assertEquals(getText(By.cssSelector("[class='spaceEmptyDescription']")), examen,"les prelevements de laboratoire ne sont pas présents dans le dossier médical");
		Log.info("Les prelevements de laboratoire sont bien présents dans le dossier médical");
	}
	
	
	public void verifierPresenceScoreRisqueCardiovasculaireRCVDansLesDocumentsExternes() {
		Log.info("On vérifie que le score du Risque cardio vasculaire est présent dans le dossier médical");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime date = LocalDateTime.now();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//div[contains(text(),'Risque cardiovasculaire du %s')]",formatter.format(date)))));
		Log.info("Les prelevements de laboratoire sont bien présents dans le dossier médical");
	}
	public void verifierPresenceScoreHypercholesterolemieFamilialeFHDansLesFormulaires(String score) {
		Log.info("On vérifie que le Score " + score + " est présent dans le dossier médical");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//span[contains(@class, 'spaceEmptyDescription') and text() = '%s']",score))));
		Log.info("Le Score Hypercholestérolémie Familiale est bien présent dans le dossier médical");
	}
	
	public void rechercherLePatientAvecLeNomEtOuvrirSonDossierMedical(String patientName) {
		Log.info("On recherche le patient avec le nom : "+ patientName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_rechercher_patient));
		input(patientName, champ_rechercher_patient);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName)));
		Log.info("On a bien trouvé le patient avec le nom : "+ patientName);	
	}
	
	
	public void verifierDeclenchementFHEtAccepter() {
		Log.info("On vérifie que le declenchement automatique de la FH");
		wait.until(ExpectedConditions.visibilityOfElementLocated(alert_FH));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_accepter_FH));
		click(bouton_accepter_FH);
		Log.info("La FH protocole Score hypercholestérolémie Familiale a été bien déclanché automatiquement");
	}
	
	public void retourAccueil() {
		Log.info("On retourne à l'accueil");
		wait.until(ExpectedConditions.visibilityOfElementLocated(retourAccueil));
		click(retourAccueil);
	}
	
	private void wait(int temps) {
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void fermer_note_e_patient_fenetre() {
		
		List<WebElement> paiement_attente_0 = driver.findElements(By.xpath("//li[text()='Paiements en attente 0 €']"));
		//List<WebElement> button_fermer = driver.findElementsByXPath("//div[@class='postit-footer']//button[text()='Fermer']");
		if(paiement_attente_0.size()!=0) {
			Log.info("le paiement en attente est a zero donc le note de patients doit pas apparaitre");
		}else {
			Log.info("on ferme le note de patients");
			click(By.xpath("//div[@class='postit-footer']//button[text()='Fermer']"));
			
		}
		}
	
	public void verifierExistenceOrdonnanceDansSectionPrescriptionComplementaire(String section,String ordonnance) {
		Log.info("on verifie l'existence des examens labo dans plusieurs sections dans l' EHR");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']/following::span[contains(text(),'%s')]",section,ordonnance))));
	    Log.info(String.format("les ordonnances existent dans %s",section));
	}
	
	public void verifierContenuOrdonnanceDepuisSectionPrescriptionComplementaire(String section,String ordonnance) {
		Log.info("on verifie l'existence des examens labo dans plusieurs sections dans l' EHR");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']/following::span[contains(text(),'%s')]",section,ordonnance))));
	    Log.info(String.format("les ordonnances existent dans %s",section));
        click(By.xpath(String.format("//a[text()='%s']/following::span[contains(text(),'%s')]",section,ordonnance)));
    	//wait(2000);
        //driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Imprimer']")));
		click(By.xpath("//button[text()='Imprimer']"));
		
	}
	
	
	public void verifier_existance_des_examens_labo_dans_plusieurs_sections(String section,String examen) {
		Log.info("on verifie l'existence des examens labo dans plusieurs sections dans l' EHR");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']/following::span[contains(text(),'%s')]",section,examen))));
	    Log.info(String.format("les examens existent dans %s",section));
	
	}
	
	
	
	public void verifier_existance_des_examens_labo_dans_documents_externes(String section,String examen) {
		Log.info("on verifie l'existence des examens labo dans plusieurs sections dans l' EHR");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']/following::div[contains(text(),'%s')]",section,examen))));
	    Log.info(String.format("les examens existent dans %s",section));
	}
	
	
	
	
	
	

	public void ouvrir_documents_ajoutes_depuis_documents_externes(String section,String examen) {
		Log.info("on verifie l'existence des examens labo dans plusieurs sections dans l' EHR");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']/following::div[contains(text(),'%s')]",section,examen))));
	   click(By.xpath(String.format("//a[text()='%s']/following::div[contains(text(),'%s')]",section,examen)));
		Log.info(String.format("les examens existent dans %s",section));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iframe-doc-popup")));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Aperçu']/preceding::div[contains(@class,'tool-close')]")));
	    click(By.xpath("//span[text()='Aperçu']/preceding::div[contains(@class,'tool-close')]"));
	}
	
	
	
	public void supprimer_documents_ajoutes_depuis_documents_externes(String section,String examen) {
		Log.info("on supprime les documents recemment ajoutes");
		rightClick(By.xpath(String.format("//a[text()='%s']/following::div[contains(text(),'%s')]",section,examen)));
		WebElement supprimer = driver.findElement(By.xpath("//a[text()='Supprimer']"));
		Actions action = new Actions(driver);
		action.moveToElement(supprimer).click().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Oui']")));
		click(By.xpath("//button[text()='Oui']"));
		
		
		
	}
	
	
	public void verifier_existance_de_probleme_de_sante_dans_examen_de_laboratoire(String section,String problemeDeSante) {
		Log.info("on verifie l'existence des examens labo dans plusieurs sections dans l' EHR");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']/following::div[contains(text(),'%s')]",section,problemeDeSante))));
	    Log.info(String.format("les examens existent dans %s",section));
	}
	
	public void retour_page_integration_documents() {
		Log.info("on retourn au page integrations des documents medicaux");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'Gestion des documents')]")));
		click(By.xpath("//a[contains(@title,'Gestion des documents')]"));
	}
	
	public void creer_un_document_avec_un_existent_modele(String modele_num) {
		 String modele =  "Modèle2" + date + modele_num;
		Log.info("on cree un document avec un model deja cree");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Documents']/preceding::div[2]")));
		Log.info("on tape sur button configurer document");
		click(By.xpath("//a[text()='Documents']/preceding::div[2]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearchConfig")));
		input(modele,By.id("txtSearchConfig"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",modele))));
		WebElement select_model = driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]/preceding::input[1]",modele)));
		if(select_model.isSelected()) {
		Log.info("le modele est choisi donc on tape sur ok");
		click(By.xpath("//em[@unselectable='on']//button[text()='OK']"));
	}else {
		Log.info("le modele est pas choisi donc on choisi le modele puis on tape sur ok");
		click(select_model);		
		click(By.xpath("//em[@unselectable='on']//button[text()='OK']"));

	}
	
	Log.info("on tape sur button ajouter des medicaments");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Documents']/preceding::div[3]")));
	click(By.xpath("//a[text()='Documents']/preceding::div[3]"));
	Log.info("on cherche le document a ajouter");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
	input(modele,By.id("txtSearch"));
	Log.info("on ouvre le document cherche");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",modele))));
	click(By.xpath(String.format("//span[contains(text(),'%s')]",modele)));
	wait_avec_sleep(15000);
	
	}
	
	public void creer_un_document_avec_un_existent_modele_et_entete(String modele_entete_num) {
		 String entete =  "Entête1" + date + modele_entete_num;
		 String modele =  "Modèle" + "10/07/2023";
		Log.info("on cree un document avec un model deja cree et une entete deja cree");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Documents']/preceding::div[2]")));
		Log.info("on tape sur button configurer document");
		click(By.xpath("//a[text()='Documents']/preceding::div[2]"));
		Log.info("on cherche l'entete deja cree pour l'ajouter au modele");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("templateIdDefaultHead")));
	     click(By.id("templateIdDefaultHead"));
	     wait_avec_sleep(2000);
		Select select_entete = new Select(driver.findElement(By.id("templateIdDefaultHead")));
	
		select_entete.selectByVisibleText(entete);
		
		Log.info("on cherche le modele");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearchConfig")));
		input(modele,By.id("txtSearchConfig"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",modele))));
		WebElement select_model = driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]/preceding::input[1]",modele)));
		if(select_model.isSelected()) {
		Log.info("le modele est choisi donc on tape sur ok");
		click(By.xpath("//em[@unselectable='on']//button[text()='OK']"));
	}else {
		Log.info("le modele est pas choisi donc on choisi le modele puis on tape sur ok");
		click(select_model);		
		click(By.xpath("//em[@unselectable='on']//button[text()='OK']"));

	}
	
	Log.info("on tape sur button ajouter des medicaments");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Documents']/preceding::div[3]")));
	click(By.xpath("//a[text()='Documents']/preceding::div[3]"));
	Log.info("on cherche le document a ajouter");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
	input(modele,By.id("txtSearch"));
	Log.info("on ouvre le document cherche");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",modele))));
	click(By.xpath(String.format("//span[contains(text(),'%s')]",modele)));
	wait_avec_sleep(15000);
	
	}
	
	public void cree_un_document_vierge_depuis_les_documents() {
		Log.info("on tape sur button ajouter des medicaments");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Documents']/preceding::div[3]")));
		click(By.xpath("//a[text()='Documents']/preceding::div[3]"));
		Log.info("on tape sur button nouveau document vierge");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Nouveau document vierge']")));
		click(By.xpath("//button[text()='Nouveau document vierge']"));
		wait(10000);
	}
	

	public void validerAccesDMPetINS() {
		List<WebElement> elements = driver.findElements(By.xpath("//div[@aria-describedby='jOptionsCon']"));
		//List<WebElement> elements = driver.findElements(By.xpath("//button[text()='Accepter']"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-describedby='jOptionsCon']")));
		if(elements.size()!=0) {	
		click(By.xpath("//button[text()='Non']"));
	}else {
		Log.info("y a pas de popup");
	}
	}
	
	public void switchTowindow(int window) {
		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(window));
	}
	
	
	public void ouvrir_le_document_pdf(String titre) {
	Log.info("on verifie existence des textes dans le document PDF");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='Documents']/following::span[@class='spaceEmptyDescription' and contains(text(),'%s')]",titre))));
	Log.info("on clique sur le document PDF");
	wait_avec_sleep(3000);
	click(By.xpath(String.format("//a[text()='Documents']/following::span[@class='spaceEmptyDescription' and contains(text(),'%s')]",titre)));
	wait_avec_sleep(7000);
	}
	
	
	public void verifier_existence_texte_dans_le_document_pdf() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'TXTCTRL')]")));
		String txt = driver.findElement(By.xpath("//span[contains(text(),'TXTCTRL')]")).getText();
		Log.info(txt);
//		try {
//			String pdfContent = readPdfContent(driver.getCurrentUrl());
//			Assert.assertTrue(pdfContent.contains(text));
//		
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		Log.info("les textes ajoutes existent dans le pdf");
	}

	
   public static  String readPdfContent(String url) throws IOException {
		
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
	
	return content;
}
	
	private void wait_avec_sleep(int seconds){
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	

