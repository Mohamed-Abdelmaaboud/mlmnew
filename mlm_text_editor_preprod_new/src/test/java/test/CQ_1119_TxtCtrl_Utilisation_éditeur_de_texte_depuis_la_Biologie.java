package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import listener.annotation.Xray;
import page.models.OrdonnancePDF;
import page.models.PageAccueil;
import page.models.PageConnexion;
import page.models.PageDossierAdministratif;
import page.models.PageDossierMedical;
import page.models.PageDossierPatients;
import page.models.PageListePatient;
import page.models.PageResultatDesOrdonnances;
import page.models.TextEditorPage;

public class CQ_1119_TxtCtrl_Utilisation_éditeur_de_texte_depuis_la_Biologie extends BaseTest{
	LocalDateTime date_de_jour =LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 LocalDateTime age = date_de_jour.minusYears(23);
	 String Age = formatter.format(age);
	
	@Test
	@Xray(test="CQ-1119")
	public void CQ_1119_Utilisation_éditeur_de_texte_depuis_la_Biologie() throws InterruptedException {
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
		pageDossierAdministratif.renseignerPrenom("CQ119");
		pageDossierAdministratif.choisirSexe("Masculin");
		pageDossierAdministratif.renseignerLocalitDeNaissance("paris");
		pageDossierAdministratif.renseignerDateDeNaissance(Age);
		pageDossierAdministratif.validerCreationNouveauDossier();
		pageDossierAdministratif.validerCriteresDeVerification();
		Thread.sleep(2000);
		pageDossierPatients = new PageDossierPatients();
		pageDossierPatients.retourAccueil();
		pageAccueil.rechercherLePatientAvecLeNomEtOuvrirSonDossierMedical(nomPatient);
		PageDossierMedical pageDossierMedical = new PageDossierMedical();
		pageDossierMedical.ouvrirExamenDeLaboratoire();
		PageResultatDesOrdonnances pageResultatDesOrdonnances =new PageResultatDesOrdonnances();
		//pageResultatDesOrdonnances.configurerOrdonnance();
		pageResultatDesOrdonnances.creer_nouveau_prelevement();
		pageResultatDesOrdonnances.recherche_un_examen("Hemoglobine");
		pageResultatDesOrdonnances.choisir_examen("Hémoglobine (Hb)");
		pageResultatDesOrdonnances.saisie_valeur_en_UI("9","8","11","Hémoglobine (Hb)");
		pageResultatDesOrdonnances.recherche_un_examen("Temps de coagulation");
		pageResultatDesOrdonnances.choisir_examen("Temps de coagulation");
		pageResultatDesOrdonnances.saisie_valeur_en_UI("10","8","12","Temps de coagulation");
		pageResultatDesOrdonnances.imprimer_les_examens_en_editeur_texte();
		TextEditorPage textEditorPage = new TextEditorPage();
		Thread.sleep(5000);
		textEditorPage.switchTowindow(1);
	    textEditorPage.imprimer_le_document_en_pdf();
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("1) Hémoglobine (Hb) 9.0 mmol/L");
		ordonnancePDF.readpdfItext("2) Temps de coagulation 10.0 min");
		
	}
	
	
	
	
	

}
