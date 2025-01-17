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

public class CQ_2691_Vérification_de_la_mise_en_page_d_une_prescription_libre_de_médicament extends BaseTest{
	LocalDateTime date = LocalDateTime.now();
	DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yy");
	DateTimeFormatter formatter2 =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String DATE_DE_JOUR = formatter.format(date);
	String DATE_DE_JOUR2 = formatter2.format(date);
	 LocalDateTime age = date.minusYears(23);
	 String Age = formatter2.format(age);
	@Test
	@Xray(test="CQ-2691")
	public void CQ_2691_Vérification_de_la_mise_en_page_d_une_prescription_libre_de_médicamen() throws InterruptedException {
		
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
		pageDossierAdministratif.renseignerPrenom("CQ2691");
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
		pageTraitements.optionImpressionEtValidationSansRetourDossierMedical();
		pageTraitements.gerer_les_ordonnances_en_format_PDF();
		pageTraitements.commencerNouveauTraitement();
		ModuleAjoutProduits moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.prescriptionLibre("Elisor","25 1/j");
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("Elisor");
		pageTraitements.switchTowindow(0);
		pageTraitements.verifier_existence_medicaments_prescrits_dans_historique2(DATE_DE_JOUR2,"Elisor");
		pageTraitements.gerer_les_ordonnances_en_format_editeur_text();
		pageTraitements.commencerNouveauTraitement();
		 moduleAjoutProduits = new ModuleAjoutProduits();
		moduleAjoutProduits.prescriptionLibre("Cardensiel","80c 3/j");
		TextEditorPage textEditorPage = new TextEditorPage();
		textEditorPage.switchTowindow(1);
		textEditorPage.valider_et_ouvrir_le_document();
		 ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("Cardensiel");
		textEditorPage.verifier_contenu_de_l_ordonnace("Cardensiel");
		pageTraitements.switchTowindow(0);
		pageTraitements.verifier_existence_medicaments_prescrits_dans_historique2(DATE_DE_JOUR2,"Cardensiel");
		
	}
	
	
	
	
	

}
