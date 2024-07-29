package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import listener.annotation.Xray;
import page.models.OrdonnancePDF;
import page.models.PageAccueil;
import page.models.PageConnexion;
import page.models.PageDossierAdministratif;
import page.models.PageDossierMedical;
import page.models.PageDossierPatients;
import page.models.PageListePatient;
import page.models.PageTraitements;
import page.models.TextEditorPage;
import page.module.ModuleAjoutProduits;

public class CQ_2750_Fonctionnement_de_la_mention_A_partir_du extends BaseTest{
	
	LocalDateTime date_du_jour = LocalDateTime.now().plusDays(1);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String date = date_du_jour.format(formatter);
	LocalDateTime date_du_jour2 = LocalDateTime.now();
	 LocalDateTime age = date_du_jour2.minusYears(23);
	 String Age = formatter.format(age);

	@Xray(test = "CQ-2750")
	@Test(description = "impression de la mension a partir du sur les prescriptions de medicaments")
	public void CQ_2750_Fonctionnement_d_la_mention_A_partir_du() throws InterruptedException {
		PageConnexion pageConnexion = new PageConnexion();
		PageAccueil pageAccueil = new PageAccueil();
		pageConnexion.seConnecterParLoginEtMotDePasse();
		pageAccueil.accepterNoteCentre();
		pageAccueil.ouvrirMenuDeGauche("Dossier Patients");
		PageDossierPatients pageDossierPatients = new PageDossierPatients();
		pageDossierPatients.créerNouveauDossier();
		PageDossierAdministratif pageDossierAdministratif = new PageDossierAdministratif();
		pageDossierAdministratif.recueilDuConsentementDuPatient();
		String nomPatient = pageDossierAdministratif.renseignerNom("TEXTCTRL");
		pageDossierAdministratif.renseignerNomDeNaissance("TEXTCTRL");
		pageDossierAdministratif.renseignerPrenom("CQ2750");
		pageDossierAdministratif.choisirSexe("Masculin");
		pageDossierAdministratif.renseignerLocalitDeNaissance("paris");
		pageDossierAdministratif.renseignerDateDeNaissance(Age);
		pageDossierAdministratif.validerCreationNouveauDossier();
		pageDossierAdministratif.validerCriteresDeVerification();
		pageDossierPatients = new PageDossierPatients();
		pageDossierPatients.retourAccueil();
		pageAccueil.rechercherLePatientAvecLeNomEtOuvrirSonDossierMedical(nomPatient);
		PageDossierMedical pageDossierMedical = new PageDossierMedical();
		pageDossierMedical.prescrireMedicaments();
		PageTraitements pageTraitements = new PageTraitements();
		pageTraitements.gerer_les_ordonnances_en_format_editeur_text();
		pageTraitements.optionImpressionEtValidationSansRetourDossierMedical();
		pageTraitements.commencerNouveauTraitement();
		ModuleAjoutProduits moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.rechercherUnProduit("Doliprane");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements.commencerNouveauTraitement();
		moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.rechercherUnProduit("Kardegic");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements.commencerNouveauTraitement();
		moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.rechercherUnProduit("Pivalone");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements = new PageTraitements();
		pageTraitements.changerLaDateDePrescription(date);
		pageTraitements.accepterEtImprimerOrdonnance();
		Thread.sleep(10000);
		TextEditorPage textEditorPage = new TextEditorPage();
		textEditorPage.switchTowindow(1);
		textEditorPage.valider_et_ouvrir_le_document();
		Thread.sleep(10000);
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("À partir du");
	}

	
	
}