package test;

import java.awt.AWTException;

import org.testng.annotations.Test;

import listener.annotation.Xray;
import page.models.TextEditorPage;
import page.models.ListesDesModeles;
import page.models.PageAccueil;
import page.models.PageConnexion;

public class CQ_1112_TxtCtrl_Creating_a_new_model extends BaseTest {

	
	
	
	@Test()
	@Xray(test="CQ-1112")
	public void TxtCtrl_Creating_a_new_model() throws AWTException, InterruptedException {
		PageConnexion pageConnexion = new PageConnexion();
		PageAccueil pageAccueil = new PageAccueil();
		pageConnexion.seConnecterParLoginEtMotDePasse();
		pageAccueil.choisir_gestion_des_documents_types();
		ListesDesModeles listesDesModeles = new ListesDesModeles();
		listesDesModeles.ajouter_un_modele();
		TextEditorPage textEditorPage = new TextEditorPage();
		textEditorPage.switchTowindow(1);
		textEditorPage.creer_un_modele("Administratif","Autre document du patient","CQ-1112");
		textEditorPage.ajout_signets("Prénom");
		//textEditorPage.ajout_signets("Nom");
		textEditorPage.saisie_des_informations_dans_le_textediteur("CQ-1112");
		textEditorPage.verifierContenuDansLeTextEditor("CQ-1112");
		//textEditorPage.inserer_image_dans_textediteur("C:\\Users\\mabdelmaaboud\\Desktop\\R");
		textEditorPage.inserer_image_dans_textediteur("C:\\Users\\QA_CLM\\Desktop\\R.png");
		textEditorPage.valider_la_creation_du_model_avec_oui();
		textEditorPage.valider_la_creation_du_model_avec_Non();
		listesDesModeles.verifier_editeur_de_text_ouvert_apres_choisir_non();
		listesDesModeles.switchTowindow(0);
		listesDesModeles.verifier_exitence_du_modele("Modèle","Éditeur de texte","CQ-1112");
	}
	
}
