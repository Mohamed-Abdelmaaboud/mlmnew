package test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import clm.interop.webdrivermanager.Driver;
import listener.annotation.Xray;
import page.models.OrdonnancePDF;
import page.models.PageAccueil;
import page.models.PageConnexion;
import page.models.PageDossierAdministratif;
import page.models.PageDossierMedical;
import page.models.PageDossierPatients;
import page.models.PageListePatient;
import page.models.PageTraitementsComplementaires;
import page.models.TextEditorPage;

public class TxtCtrl_Utilisation_de_l_éditeur_de_texte_depuis_la_Prescription_Complémentaire extends BaseTest{
	
	LocalDateTime date_de_jour =LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 LocalDateTime age = date_de_jour.minusYears(23);
	 String Age = formatter.format(age);
	
	
	@Test
	@Xray(test="CQ-1118")
	public void UtilisationEditeurDeTexteDepuisLaPrescriptionComplémentaire() throws InterruptedException, IOException {
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
		pageDossierAdministratif.renseignerPrenom("CQ1118");
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
		pageDossierMedical.prescriptionsComplementaires();
		PageTraitementsComplementaires pageTraitementsComplementaires= new PageTraitementsComplementaires();
		pageTraitementsComplementaires.gerer_les_ordonnances_en_format_editeur_text();
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Arthroscopie");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Arthroscopie de l'épaule");
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Radiographie");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Radiographie du membre inférieur");
		pageTraitementsComplementaires.cocher_ALD("Arthroscopie");
		pageTraitementsComplementaires.validerEtImprimer();
		Thread.sleep(5000);
		TextEditorPage textEditorPage = new TextEditorPage();
		textEditorPage.switchTowindow(1);
		Thread.sleep(5000);
		textEditorPage.valider_et_ouvrir_le_document();
		Thread.sleep(5000);
		OrdonnancePDF ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("Radiographie du membre inférieur");
		ordonnancePDF.readpdfItext("Arthroscopie de l'épaule");
		textEditorPage.switchTowindow(1);
		Thread.sleep(5000);
		textEditorPage.saisie_des_informations_dans_le_textediteur("CQ1118");
		textEditorPage.verifierContenuDansLeTextEditor("CQ1118");
		textEditorPage.switchTowindow(0);
		pageDossierMedical.prescriptionsComplementaires();
		pageTraitementsComplementaires.gerer_les_ordonnances_en_format_PDF();
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Anticorps");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Anticorps anti-ADN dénaturé");
		pageTraitementsComplementaires.ajout_des_commentaire_dans_l_ordonnance("une calcémie (corrigée en fonction de l’albuminémie protidémie) si IgA ou IgG, dosage des LDH si IgM");
		pageTraitementsComplementaires.validerEtImprimer();
        ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("une calcémie");
		ordonnancePDF.switchTowindow(0);
		pageDossierMedical.prescriptionsComplementaires();
		pageTraitementsComplementaires.gerer_les_ordonnances_en_format_editeur_text();
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Anticorps");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Anticorps anti-ADN natif");
		pageTraitementsComplementaires.ajout_des_commentaire_dans_l_ordonnance("une calcémie (corrigée en fonction de l’albuminémie protidémie) si IgA ou IgG, dosage des LDH si IgM ");
		pageTraitementsComplementaires.validerEtImprimer();
		Thread.sleep(5000);
		textEditorPage = new TextEditorPage();
		textEditorPage.switchTowindow(1);
		textEditorPage.valider_et_ouvrir_le_document();
        ordonnancePDF = new OrdonnancePDF();
		ordonnancePDF.readpdfItext("une calcémie");
		ordonnancePDF.switchTowindow(0);
		pageDossierMedical.prescriptionsComplementaires();
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Anticorps");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Anticorps anti-ADN natif");
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Alpha");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Alpha 1-4 glucosidase séminale");
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Figure complexe de Rey");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Figure complexe de Rey");
		pageTraitementsComplementaires.rechercherTraitementComplementaire("Le code");
		pageTraitementsComplementaires.cliquerBoutonRechercher();
		pageTraitementsComplementaires.selectionnerTraitementsComplementaires("Le code");
		pageTraitementsComplementaires.validerEtImprimer();
		Thread.sleep(5000);
	    textEditorPage.switchTowindow(1);
	    textEditorPage.valider_et_retour_le_document();
	//	ordonnancePDF.readpdfItext("Anticorps anti-ADN natif");
	//	ordonnancePDF.readpdfItext("Alpha 1-4 glucosidase séminale");
	//	ordonnancePDF.readpdfItext("Le code");
		ordonnancePDF.switchTowindow(0);
	    pageDossierMedical.verifierExistenceOrdonnanceDansSectionPrescriptionComplementaire("Prescription complémentaires","Ordonnance autre");
	    pageDossierMedical.verifierExistenceOrdonnanceDansSectionPrescriptionComplementaire("Prescription complémentaires","Ordonnance de laboratoire");
	
	}
}