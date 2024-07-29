package test;

import java.awt.AWTException;
import java.io.IOException;
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
import page.models.TextEditorPage;

public class CQ_1116_TxtCtrl_Utilisation_de_l_éditeur_de_texte_depuis_les_Documents extends BaseTest{
	LocalDateTime date_du_jour = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 LocalDateTime age = date_du_jour.minusYears(23);
	 String Age = formatter.format(age);
	@Test
	@Xray(test="CQ-1116")
	public void CQ_1116_Utilisation_de_l_éditeur_de_texte_depuis_les_Documents() throws AWTException, IOException, InterruptedException {
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
		pageDossierAdministratif.renseignerPrenom("CQ1116");
		pageDossierAdministratif.choisirSexe("Masculin");
		pageDossierAdministratif.renseignerLocalitDeNaissance("paris");
		pageDossierAdministratif.renseignerDateDeNaissance(Age);
		pageDossierAdministratif.validerCreationNouveauDossier();
		Thread.sleep(2000);
		pageDossierAdministratif.validerCriteresDeVerification();
		pageDossierPatients = new PageDossierPatients();
		pageDossierPatients.retourAccueil();
		pageAccueil.rechercherLePatientAvecLeNomEtOuvrirSonDossierMedical(nomPatient);
		PageDossierMedical pageDossierMedical = new PageDossierMedical();
		pageDossierMedical.cree_un_document_vierge_depuis_les_documents();
		TextEditorPage textEditorPage = new TextEditorPage();
		Thread.sleep(5000);
		textEditorPage.switchTowindow(1);
		textEditorPage.ajout_signets("Prénom");
		textEditorPage.saisie_des_informations_dans_le_textediteur("Fucidine");
	    textEditorPage.renseigner_type_de_document("Attestation de dépistage");
		//textEditorPage.inserer_image_dans_textediteur("C:\\Users\\mabdelmaaboud\\Desktop\\R");
	    textEditorPage.inserer_image_dans_textediteur("C:\\Users\\QA_CLM\\Desktop\\R.png");
		textEditorPage.valider_et_retour_le_document();
		pageDossierMedical.switchTowindow(0);
		pageDossierMedical.validerAccesDMPetINS();
		pageDossierMedical.ouvrir_le_document_pdf("Courrier vierge");
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("Fucidine");
		ordonnancePDF.verifierPresenceImageDansLePDF();
		pageDossierMedical.switchTowindow(0);
		pageDossierMedical.cree_un_document_vierge_depuis_les_documents();
		textEditorPage.switchTowindow(1);
		textEditorPage.ajout_signets("Nom de naissance");
		textEditorPage.renseigner_type_de_document("Attestation de dépistage");
		textEditorPage.valider_et_ouvrir_le_document();
		ordonnancePDF.readpdfItext("TEXTCTRL");
		
		
	
	
	
	}
	

}
