package page.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import clm.interop.Log;

public class PageDossierAdministratif extends BasePage{
	String urlPageDossierAdministratif = "https://mlmpreprod.monlogicielmedical.com/jenomi/formularioPersonas.htm";
	By menu_dossier_administratif = By.xpath("//li[contains(text(),'Dossier Administratif')]");
	By radio_bouton_tous_les_professionels_de_sante = By.id("checkAllCenter");
	By radio_button_seulment_moi = By.id("checkOnlyUser");
	By bouton_ok_valider_consentement = By.id("consentPopupAccept");
	By champ_nom = By.id("paciente.apellido1");
	By champ_nom_naissance = By.id("paciente.apellido2");
	By champ_prenom = By.xpath("//div[@class='is-extended-field']//input[@id='paciente.nombre']");
	By localiteDeNaissanceChamps = By.xpath("//input[@id='city-description']");
	By select_sexe_patient = By.xpath("//div[@class='is-extended-field']//select[@id='paciente.sexo']");
	By champ_date_naissance = By.id("nacimiento");
	By onglet_adresse = By.xpath("//ul[@id='stepperMenu']//span[contains(text(),'Adresses/Contact')]");
	By onglet_medecin_traitant = By.xpath("//ul[@id='stepperMenu']//span[contains(text(),'Médecin traitant')]");
	By champ_adresse_premiere_ligne = By.id("paciente.domicilio.addressLineOne");
	By champ_code_postal = By.id("addressPostalCodeAutocompleteInputCombobox");
	By bouton_je_suis_le_medecin_traitant = By.id("formularioPersonas.paciente.button.medicinTraitant");
	By date_debut_medecin_traitant = By.id("medTraitant.fechaIni");
	By champ_situation_professionnelle = By.id("paciente.situation");
	//By bouton_OK_valider_nouveau_dossier = By.cssSelector("[onclick='doSubmitOK()']");
	By bouton_OK_valider_nouveau_dossier=By.xpath("//div[@class='text-right']//button");
	By bouton_valider_la_creation = By.xpath("//button[contains(text(),'Valider la création')]");
	By popup_criteres_verification = By.id("checkHomonymsId");
	By frame_popup_criteres_verification = By.id("iframe-check-homonyms");
	
	public PageDossierAdministratif() {
	wait.until(ExpectedConditions.urlContains(urlPageDossierAdministratif));
	}
	
	public void recueilDuConsentementDuPatient() {
		Log.info("On choist le consentement du patient");
		wait.until(ExpectedConditions.elementToBeSelected(radio_button_seulment_moi));
		click(bouton_ok_valider_consentement);
		Log.info("On valide le consentement du patient");
	}
	
	
	public String renseignerNom(String nom) {
		Log.info("On remplit le nom");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_nom));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime date = LocalDateTime.now();
		nom = nom + formatter.format(date) ;
		//input(nom +"-"+ formatter.format(date), champ_nom);
		input(nom , champ_nom);
		Log.info("Le nom a été rempli : " + nom);
		return nom ;
	}
	
	public void renseignerNomDeNaissance(String nomNaissance) {
		Log.info("On remplit le nom de naissance");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_nom_naissance));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime date = LocalDateTime.now();
		nomNaissance = nomNaissance +"-"+ formatter.format(date) ;
		input(nomNaissance + formatter.format(date), champ_nom_naissance);
		//input(nomNaissance , champ_nom);
		Log.info("Le nom a été rempli : " + nomNaissance);
		//return nomNaissance ;
	}
	public String renseignerPrenom(String prenom) {
		Log.info("On remplit le prenom");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_prenom));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime date = LocalDateTime.now();
		input(prenom + formatter.format(date), champ_prenom);
		Log.info("Le prenom a été rempli");
		return prenom +"-"+ formatter.format(date);
	}
	public void renseignerLocalitDeNaissance(String LocaliteDeNaissance) throws InterruptedException {
		Log.info("on remplit le lieu de naissance");
		wait.until(ExpectedConditions.visibilityOfElementLocated(localiteDeNaissanceChamps));
		input(LocaliteDeNaissance,localiteDeNaissanceChamps);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='ui-id-2']//li//div[text()='PARIS 01 (PARIS)']")));
		click(By.xpath("//ul[@id='ui-id-2']//li//div[text()='PARIS 01 (PARIS)']"));
		Log.info("On a bien renseigne la Localite De Naissance");
		Thread.sleep(5000);
		
	}
	
	public void choisirSexe(String sexe) {
		Log.info("On choisit le sexe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(select_sexe_patient));
		Select sexe_patient = new Select(driver.findElement(select_sexe_patient ));
		sexe_patient.selectByVisibleText(sexe);
		Log.info("Le sexe a été bien choisi");	
	}
	
	public void renseignerDateDeNaissance(String dateNaissance) throws InterruptedException {
		Log.info("On renseigne la date de naissance");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_date_naissance));
		input(dateNaissance, champ_date_naissance);
		Thread.sleep(10000);
		Log.info("On a bien renseigné la date de naissance");

	}
	
	public void renseignerSituation(String situation) {
		Log.info("On renseigne la situation ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_situation_professionnelle));
		input(situation, champ_situation_professionnelle);
		Log.info("On a bien renseigné la situation");
	}
	
	public void renseignerAdresse(String adresse) {
		Log.info("On renseigne l'adresse");
		wait.until(ExpectedConditions.visibilityOfElementLocated(onglet_adresse));
		click(onglet_adresse);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_adresse_premiere_ligne));
		input(adresse, champ_adresse_premiere_ligne);
		Log.info("On a bien renseigné l'adresse");
		
	}
	
	public void renseignerCodePostal(String codePostal,String codePostalAutoCompletion) {
		Log.info("On renseigne le code postal");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_code_postal));
		input(codePostal, champ_code_postal);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]",codePostalAutoCompletion))));
		click(By.xpath(String.format("//div[contains(text(),'%s')]",codePostalAutoCompletion)));
		Log.info("Le code postal a été bien renseigné");
	}
	
	public void declarerMedecinTraitant() {
		Log.info("On renseigne le medecin traitant");
		wait.until(ExpectedConditions.visibilityOfElementLocated(onglet_medecin_traitant));
		click(onglet_medecin_traitant);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_je_suis_le_medecin_traitant));
		wait.until(ExpectedConditions.elementToBeClickable(bouton_je_suis_le_medecin_traitant));
		wait.until(ExpectedConditions.presenceOfElementLocated(bouton_je_suis_le_medecin_traitant));
		wait.until(ExpectedConditions.elementToBeClickable(bouton_je_suis_le_medecin_traitant));
		// Sleep solution par défaut en attendant de trouver une solution d'attente que le scroll finisse avant de cliquer sur l'element bouton_je_suis_le_medecin_traitant
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		click(bouton_je_suis_le_medecin_traitant);
		Log.info("Le medecin traitant a été bien renseigné");
	}
	
	public void renseignerDateDebutMedecinTraitant(String dateDebut) {
		Log.info("On renseigne la date de debut du medecin traitant");
		wait.until(ExpectedConditions.visibilityOfElementLocated(date_debut_medecin_traitant));
		input(dateDebut, date_debut_medecin_traitant);
		Log.info("La date de debut du medecin traitant a été renseigné");
	}
	
	public void validerCreationNouveauDossier() {
		Log.info("On clique sur le bouton OK pour valider la création du nouveau dossier");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_OK_valider_nouveau_dossier));
		click(bouton_OK_valider_nouveau_dossier);
		Log.info("le nouveau dossier a été créé avec succès");
	}
	
	public void validerCriteresDeVerification() {
		if (elementIsPresentOnDOM(popup_criteres_verification)) {
			Log.info("On valide les Critères de vérification");
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_popup_criteres_verification));
			wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_valider_la_creation));
			click(bouton_valider_la_creation);
		} else {
			Log.info("Pas besoin de valider les Critères de vérification");
		}
		Log.info("les Critères de vérification ont été validés");
		switchToDefault();
	}
	
	
	
}
