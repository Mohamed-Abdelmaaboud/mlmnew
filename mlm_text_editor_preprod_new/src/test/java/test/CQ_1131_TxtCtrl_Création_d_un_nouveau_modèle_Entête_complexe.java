package test;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import listener.annotation.Xray;
import page.models.TextEditorPage;
import page.models.ListesDesModeles;
import page.models.OrdonnancePDF;
import page.models.PageAccueil;
import page.models.PageConnexion;
import page.models.PageDossierAdministratif;
import page.models.PageDossierMedical;
import page.models.PageDossierPatients;
import page.models.PageListePatient;

public class CQ_1131_TxtCtrl_Création_d_un_nouveau_modèle_Entête_complexe extends BaseTest {

	LocalDateTime date_de_jour =LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 LocalDateTime age = date_de_jour.minusYears(23);
	 String Age = formatter.format(age);
	
	
	
	@Test()
	@Xray(test="CQ-1131")
	public void CQ_1131_Création_d_un_nouveau_modèle_Entête_complexe() throws AWTException, InterruptedException, IOException {
		PageConnexion pageConnexion = new PageConnexion();
		PageAccueil pageAccueil = new PageAccueil();
		pageConnexion.seConnecterParLoginEtMotDePasse();
		pageAccueil.choisir_gestion_des_documents_types();
		ListesDesModeles listesDesModeles = new ListesDesModeles();
		listesDesModeles.ajouter_un_entete();
		TextEditorPage textEditorPage = new TextEditorPage();
		 textEditorPage.switchTowindow(1);
		textEditorPage.creer_un_modele_avec_entete("Administratif","Autre document du patient","CQ-1131");
		textEditorPage.ajout_signets("Âge");
		textEditorPage.ajout_signets("Nom de naissance");
		textEditorPage.saisie_des_informations_dans_le_textediteur("CQ-1131");
		//textEditorPage.inserer_image_dans_textediteur("C:\\Users\\mabdelmaaboud\\Desktop\\R");
		textEditorPage.inserer_image_dans_textediteur("C:\\Users\\QA_CLM\\Desktop\\R.png");
		textEditorPage.valider_la_creation_du_model_avec_oui();
		textEditorPage.valider_la_creation_du_model_avec_Non();
		textEditorPage.switchTowindow(0);
		listesDesModeles.verifier_editeur_de_text_ouvert_apres_choisir_non();
		textEditorPage.switchTowindow(0);
		listesDesModeles.verifier_exitence_du_entete("Entête","Éditeur de texte","CQ-1131");
		listesDesModeles.retourAccueil();
		pageAccueil.ouvrirMenuDeGauche("Dossier Patients");
		PageDossierPatients pageDossierPatients = new PageDossierPatients();
		pageDossierPatients.créerNouveauDossier();
		PageDossierAdministratif pageDossierAdministratif = new PageDossierAdministratif();
		pageDossierAdministratif.recueilDuConsentementDuPatient();
		String nomPatient = pageDossierAdministratif.renseignerNom("TEXTCTRL");
		pageDossierAdministratif.renseignerNomDeNaissance("TEXTCTRL");
		pageDossierAdministratif.renseignerPrenom("CQ1131");
		pageDossierAdministratif.choisirSexe("Masculin");
		pageDossierAdministratif.renseignerLocalitDeNaissance("paris");
		pageDossierAdministratif.renseignerDateDeNaissance(Age);
		pageDossierAdministratif.validerCreationNouveauDossier();
		pageDossierAdministratif.validerCriteresDeVerification();
		pageDossierPatients = new PageDossierPatients();
		pageDossierPatients.retourAccueil();
		pageAccueil.rechercherLePatientAvecLeNomEtOuvrirSonDossierMedical(nomPatient);
		PageDossierMedical pageDossierMedical = new PageDossierMedical();
		pageDossierMedical.creer_un_document_avec_un_existent_modele_et_entete("CQ-1131");
		textEditorPage.switchTowindow(2);
		textEditorPage.renseigner_type_de_document("Autre document du patient");
		textEditorPage.saisie_des_informations_dans_le_textediteur("CQ1131");
		textEditorPage.valider_et_ouvrir_le_document();
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("TEXTCTRL");
		ordonnancePDF.verifierPresenceImageDansLePDF();
	}
	
}
