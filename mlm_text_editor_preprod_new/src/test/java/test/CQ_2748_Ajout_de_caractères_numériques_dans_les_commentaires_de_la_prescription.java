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

public class CQ_2748_Ajout_de_caractères_numériques_dans_les_commentaires_de_la_prescription extends BaseTest{
	LocalDateTime date = LocalDateTime.now();
	DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yy");
	DateTimeFormatter formatter2 =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String DATE_DE_JOUR = formatter.format(date);
	String DATE_DE_JOUR2 = formatter2.format(date);
	 LocalDateTime age = date.minusYears(23);
	 String Age = formatter2.format(age);
	@Test
	@Xray(test="CQ-2748")
	public void CQ_2748_Ajout_de_caractères_numériques_dans_les_commentaire_de_la_prescription() throws InterruptedException {
		
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
		pageDossierAdministratif.renseignerPrenom("CQ2748");
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
		moduleAjoutProduits.rechercherUnProduit("HAVLANE");
		moduleAjoutProduits.ouvrirChoixDeLaPosologie("HAVLANE");
		moduleAjoutProduits.editerPosologie("Traitement symptomatique");
		moduleAjoutProduits.ajoutCommentaireDansEditerLaPosologie("+");
		moduleAjoutProduits.prescrireEtImprimer();
		Thread.sleep(10000);
		TextEditorPage textEditorPage = new TextEditorPage();
		textEditorPage.switchTowindow(1);
		textEditorPage.valider_et_ouvrir_le_document();
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("+");
		
		
	}
	
	
	
	
	

}
