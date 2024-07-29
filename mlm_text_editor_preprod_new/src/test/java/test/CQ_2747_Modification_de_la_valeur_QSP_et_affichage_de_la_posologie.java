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

public class CQ_2747_Modification_de_la_valeur_QSP_et_affichage_de_la_posologie extends BaseTest {
	LocalDateTime date_de_jour =LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 LocalDateTime age = date_de_jour.minusYears(23);
	 String Age = formatter.format(age);
	 
	
	@Test
	@Xray(test="CQ-2747")
	public void CQ_2747_Modification_de_la_valeur_QSP_et_affichage_de_la_posologi() throws InterruptedException  {
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
		pageDossierAdministratif.renseignerPrenom("CQ2747");
		pageDossierAdministratif.choisirSexe("Masculin");
		pageDossierAdministratif.renseignerLocalitDeNaissance("paris");
		pageDossierAdministratif.renseignerDateDeNaissance(Age);
		pageDossierAdministratif.validerCreationNouveauDossier();
		pageDossierAdministratif.validerCriteresDeVerification();
		PageListePatient pageListePatients = new PageListePatient();
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
		moduleAjoutProduits.rechercherUnProduit("DOLIPRANE");
		moduleAjoutProduits.ajouterProduitAvecPosologieUsuell();
		moduleAjoutProduits.fermerModuleAjoutProduit();
		pageTraitements.ouvrirMedicamentDejaPrescrit("DOLIPRANE");
		moduleAjoutProduits.choixPendant("5","semaine");
		moduleAjoutProduits.modifierEtFermer();
		pageTraitements.verifierPosologieDansLOrdonnance("1 comprimé 1 à 3 fois par jour selon besoin, en espaçant les prises de 4h minimum pendant 5 semaines.");
		pageTraitements.accepterEtImprimerOrdonnance();
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("DOLIPRANE");
		
		
	}
	

}
