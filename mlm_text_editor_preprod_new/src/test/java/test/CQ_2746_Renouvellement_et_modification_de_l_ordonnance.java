package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import listener.annotation.Xray;
import page.models.BasePage;
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

public class CQ_2746_Renouvellement_et_modification_de_l_ordonnance extends BaseTest {
	LocalDateTime date_de_jour =LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 LocalDateTime age = date_de_jour.minusYears(23);
	 String Age = formatter.format(age);
	 
	
	@Test
	@Xray(test="CQ-2746")
	public void CQ_2746_Renouvellement_et_modification_de_l_ordonnanc() throws InterruptedException  {
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
		pageDossierAdministratif.renseignerPrenom("CQ2746");
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
		pageTraitements.gerer_les_ordonnances_en_format_PDF();
		pageTraitements.optionImpressionEtValidationSansRetourDossierMedical();
		pageTraitements.commencerNouveauTraitement();
		ModuleAjoutProduits moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.rechercherUnProduit("CARDENSIEL");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements.commencerNouveauTraitement();
        moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.rechercherUnProduit("DOLIPRANE");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements.accepterEtImprimerOrdonnance();
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("TEXTCTRL");
		ordonnancePDF.readpdfItext("DOLIPRANE");
		ordonnancePDF.readpdfItext("CARDENSIEL");
		ordonnancePDF.switchTowindow(0);
		pageTraitements.commencerNouveauTraitement();
        moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.rechercherUnProduit("CELESTENE");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements.accepterEtImprimerOrdonnance();
		ordonnancePDF.readpdfItext("CELESTENE");
		
		
	}
	

}
