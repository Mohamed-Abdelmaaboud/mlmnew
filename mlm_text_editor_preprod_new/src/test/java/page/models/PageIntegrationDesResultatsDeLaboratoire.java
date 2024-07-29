package page.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import clm.interop.Log;
import clm.interop.webdrivermanager.Driver;

public class PageIntegrationDesResultatsDeLaboratoire extends BasePage {
	String urlPageIntegration = "https://mlmpreprod.monlogicielmedical.com/lab-webapp/laboratory/labHprim/list.html";
	By bouton_importer_un_fichier = By.id("labHprim-import-button");
	By popup_import_fichier = By.id("hprimUpLoadForm");
	By champ_import_fichier = By.id("fileupload-file");
	JavascriptExecutor js = (JavascriptExecutor)driver;
	By bouton_valider = By.xpath("//button[contains(.,'Valider')]");
	By confirmation_message = By.id("confirmMessage");
	By bouton_ok_confirmation_message = By.xpath("//span[contains(@class, 'ui-button-text') and text() = 'OK']");
	By resultat_affiche = By.id("recordLengthBbar");
	By bouton_integrer = By.xpath("//button[text()='Intégrer']");
	By bouton_Tout_integrer = By.xpath("//button[text()='Tout intégrer']");
	By popup_intergation_du_fichier_hprim = By.id("windowPatient");
	By select_probleme_de_sante = By.id("comboEpisode");
	By popup_date_examen = By.id("examDate");
	By list_probleme_sante = By.cssSelector("[class='x-layer x-combo-list ']");
	By logoMLM = By.id("showLogo");
	By bouton_Profil = By.id("userOptionsBtn");
	By bouton_deconnexion = By.xpath("//a[contains(text(),'Déconnexion')]");
	By bouton_nouveau_dossier =By.xpath("//button[text()='Nouveau Dossier']");
	public PageIntegrationDesResultatsDeLaboratoire() {
		wait.until(ExpectedConditions.urlContains(urlPageIntegration));
	}
	
	public void créerNouveauDossier() {
		Log.info("Je clique sur le bouton nouveau dossier");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_nouveau_dossier));
		click(bouton_nouveau_dossier);
	}
	
	public void importerUnFichierHPrim(String cheminFichier) {
		Log.info("Nous allons importer un fichier HPRIM");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_importer_un_fichier));
		click(bouton_importer_un_fichier);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_import_fichier));
		WebElement element = driver.findElement(By.id("fileupload-file"));
		js.executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_import_fichier));
		Log.info("Le champ est visible OK");
		try {
			((RemoteWebDriver) element).setFileDetector(new LocalFileDetector());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		input(cheminFichier,champ_import_fichier);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_valider));
		click(bouton_valider);
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation_message));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ok_confirmation_message));
		click(bouton_ok_confirmation_message);
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultat_affiche));
		Log.info("Le fichier HPrim a été importé correctement");
//		pageIntegrationDesResultatsDeLaboratoire.importerUnFichierHPrim(System.getProperty("user.dir") +File.separator+ "src" +File.separator+"test" +File.separator+ "resources" +File.separator+ "Devcon" +File.separator+ "ESSAI1_LABO1.HP2");
	}
	
	public void integrerLeResultatEtCreerNouveauPatient() throws InterruptedException {
		Log.info("Nous allons integrer le resultat importé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='BIOSITES1']")));
		try {
		click( By.xpath("//span[text()='BIOSITES1']"));
		}catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_integrer));
		click(bouton_integrer);
		Log.info("Je clique sur le bouton nouveau dossier");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_nouveau_dossier));
		click(bouton_nouveau_dossier);
	}
	
	
	public void integrerLeResultat() throws InterruptedException {
		Log.info("Nous allons integrer le resultat importé");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_integrer));
		click(bouton_integrer);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		click(bouton_valider);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		Log.info("Nous avons bien integrer le resultat importé");
	}
	
	
	
	public void validerIntegrationResultatApresCreerPatient(String nomNaissance) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime date = LocalDateTime.now();
        Log.info("Nous allons integrer le resultat importé");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='BIOSITES1']")));
		wait_avec_sleep(3000);
		click( By.xpath("//span[text()='BIOSITES1']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_integrer));
		click(bouton_integrer);
		Log.info("on recherche le patient cree");
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		input(nomNaissance +"-"+ formatter.format(date),By.id("textSearchPatient"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		click(bouton_valider);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		Log.info("Nous avons bien integrer le resultat importé");
		//Log.info("on change le nom de document");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("examTitleField")));
		//clearInput(By.id("examTitleField"));
		//input(documentTitle,By.id("examTitleField"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_valider));
		wait_avec_sleep(3000);
		click(bouton_valider);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='OK']")));
		click(By.xpath("//span[text()='OK']"));
		Log.info("Nous avons bien integrer le resultat importé");
	}
	
	
	public void validerIntegrationResultatEtAjouterProblemeDeSante(String nomNaissance,String problemeDeSante) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime date = LocalDateTime.now();
        Log.info("Nous allons integrer le resultat importé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='BIOSITES1']")));
		wait_avec_sleep(3000);
		click( By.xpath("//span[text()='BIOSITES1']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_integrer));
		click(bouton_integrer);
		Log.info("on choisit le patient cree");
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		//input(nomNaissance +"-"+ formatter.format(date), By.id("textSearchPatient"));
		click(By.xpath(String.format("//div[contains(text(),'%s')]",nomNaissance)));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		click(bouton_valider);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(popup_intergation_du_fichier_hprim));
		Log.info("Nous avons bien integrer le resultat importé");
		Log.info("on lie probleme de sante avec l'examen labo");
		click(By.xpath("//input[@id='comboEpisode']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",problemeDeSante))));
		click(By.xpath(String.format("//div[text()='%s']",problemeDeSante)));
	
		wait_avec_sleep(3000);
		click(bouton_valider);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='OK']")));
		click(By.xpath("//span[text()='OK']"));
		Log.info("Nous avons bien integrer le resultat importé et lie le probleme de sante");
	}
	
	
	
	public void integrerTousLesFichiers() throws InterruptedException {
		Log.info("Nous allons integrer le resultat importé");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_Tout_integrer));
		wait_avec_sleep(3000);
		click(bouton_Tout_integrer);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Oui']")));
		click(By.xpath("//span[text()='Oui']"));
	}
	
	
	public void selectionnerProblemeDeSante(String problemeSante) {
		Log.info("Nous allons selectionner le probleme de santé : "  + problemeSante);
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup_date_examen));
		wait.until(ExpectedConditions.visibilityOfElementLocated(select_probleme_de_sante));
		click(select_probleme_de_sante);
		wait.until(ExpectedConditions.visibilityOfElementLocated(list_probleme_sante));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class, 'x-combo-list-item') and text() = '%s']",problemeSante))));
		click((By.xpath(String.format("//div[contains(@class, 'x-combo-list-item') and text() = '%s']",problemeSante))));
		click(bouton_valider);
		Log.info("Nous avons bien selectionner le probleme de santé : " + problemeSante);	
	}
	
	public void verifierQueResultatsBienIntegres(String resultat) {
		Log.info("Nous verifions que les resultats ont été bien intégrés");
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation_message));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//center[text() = '%s']",resultat))));
		click(bouton_ok_confirmation_message);
		Log.info("Les resultats ont été bien intégrés");
	}
	
	public void retourAccueil() {
		Log.info("Nous retournons à l'accueil apres avoir avoir intégré les resultats.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(logoMLM));
		click(logoMLM);
		Log.info("Nous avons bien quitté la page intégration des resultats");
	}
	
	public void seDeconnecter() {
		Log.info("le patient a été bien créé alors on peut se deconnecter");
		click(bouton_Profil);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_deconnexion));
		click(bouton_deconnexion);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_deconnexion));
		Log.info("On est bien deconnecté");
	}
	
	
	
	
	
	public void verifier_inexistence_examen_labo_supprimer_les_en_cas_existence(){
		wait_avec_sleep(3000);
		List <WebElement> examens = driver.findElements(By.xpath("//span[contains(text(),'BIOSITES')]"));
		for(int i =0;i<examens.size();i++) {
		  if(examens.size()>0) {
			click( By.xpath("//span[text()='BIOSITES1']"));
			click(By.xpath("//button[text()='Supprimer']"));
			click(By.xpath("//span[text()='Oui']"));
			wait_avec_sleep(3000);
		   }else {
			Log.info("y a pas des examens");
		}
		} 
		
	}
	
	
	public void verifier_que_fichier_marque_non_lu() {
	Log.info("on verifie l'existence des fichier importe");	
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'BIOSITES')]")));
	  Log.info("on verifie que le fichier importe est pas lu");
	  
	  WebElement pastille_couleur = driver.findElement(By.xpath("//img[@title='Marqué comme non lu']"));
      wait.until(ExpectedConditions.visibilityOf(pastille_couleur));
	  Assert.assertTrue(pastille_couleur.isDisplayed(),"le fichier importe est lu");
       
	}
	public void modifier_le_statut_en_non_lu() {
		Log.info("on verifie l'existence des fichier importe");	
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'BIOSITES')]")));
		  Log.info("on modifie le statut en non lu");
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Marqué comme lu']")));
		  Log.info("on clique button pastille gris marque lu");
		  click(By.xpath("//img[@title='Marqué comme lu']"));
		  WebElement pastille_couleur = driver.findElement(By.xpath("//img[@title='Marqué comme non lu']"));
	      wait.until(ExpectedConditions.visibilityOf(pastille_couleur));
		  Assert.assertTrue(pastille_couleur.isDisplayed(),"le fichier importe est non lu");
	       
		}
	public void modifier_le_statut_en_lu(){
		Log.info("on verifie l'existence des fichier importe");	
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'BIOSITES')]")));
		  Log.info("on verifie que le fichier importe est pas lu");
		  Log.info("on modifie le statut en lu");
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Marqué comme non lu']")));
		  Log.info("on clique button pastille bleu marque non lu");
		  click(By.xpath("//img[@title='Marqué comme non lu']"));
		  WebElement pastille_couleur_gris = driver.findElement(By.xpath("//img[@title='Marqué comme lu']"));
	      wait.until(ExpectedConditions.visibilityOf(pastille_couleur_gris));
		  Assert.assertTrue(pastille_couleur_gris.isDisplayed(),"le fichier importe est lu");
	       
		}
		
	
	public void verifier_existence_date_de_naissance_et_nom_et_prenom_dans_la_colone_patient_unique(String nom_prenom_date) {
		Log.info("on verifie l' existence de la date de naissance et nom et prenom du patient dans la colone patient unique");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='Patient unique correspondant dans votre base']/following::a[text()='%s']",nom_prenom_date))));
		
	}
	
	public void acceder_au_dossier_medical_du_patient_depuis_la_colone_patien_unique(String nom_prenom_date) {
		Log.info("on clique sur le patient dans la colone patient unique pour acceder son dossier medical");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='Patient unique correspondant dans votre base']/following::a[text()='%s']",nom_prenom_date))));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",driver.findElement(By.xpath(String.format("//div[text()='Patient unique correspondant dans votre base']/following::a[text()='%s']",nom_prenom_date))));
		//click(By.xpath(String.format("//div[text()='Patient unique correspondant dans votre base']/following::a[text()='%s']",nom_prenom_date)));
		
	
	}
	
	public void chercher_fichier_par_nom(String nom) {
		Log.info("on cherche fichier par nom de naissance");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-patient")));
		input(nom,By.id("search-patient"));
		
		wait_avec_sleep(3000);
		List <WebElement> examens = driver.findElements(By.xpath("//span[contains(text(),'BIOSITES')]"));
	    Assert.assertTrue(examens.size()>0,"y a  pas des fichiers");
	}
	
	public void chercher_fichier_par_date(String date) {
		Log.info("on cherche fichier par date");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minFilterDate")));
		input(date,By.id("minFilterDate"));
		
		wait_avec_sleep(3000);
		List <WebElement> examens = driver.findElements(By.xpath("//span[contains(text(),'BIOSITES')]"));
	    Assert.assertTrue(examens.size()>0,"y a  pas des fichiers");
	}
	
	public void chercher_fichier_par_prescripteur(String prescripteur) {
		Log.info("on cherche fichier par prescripteur");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recepient")));
		Select select_prescripteur = new Select(driver.findElement(By.id("recepient")));
		select_prescripteur.selectByValue(prescripteur);
		wait_avec_sleep(3000);
		List <WebElement> examens = driver.findElements(By.xpath("//span[contains(text(),'BIOSITES')]"));
	    Assert.assertTrue(examens.size()>0,"y a  pas des fichiers");
	}
	
	public void chercher_fichier_deja_integre(String integre_status) {
		Log.info("Tester la recherche de résultats déjà intégrés");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("integerated")));
		Select select_integre_status = new Select(driver.findElement(By.id("integerated")));
		select_integre_status .selectByVisibleText(integre_status);
		wait_avec_sleep(3000);
		List <WebElement> examens = driver.findElements(By.xpath("//span[contains(text(),'BIOSITES')]"));
	    Assert.assertTrue(examens.size()==0,"y a  des fichiers integres");
	    select_integre_status .selectByVisibleText("Non");
	    wait_avec_sleep(3000);
	}
	
	public void reintialiser_la_recherche() {
		Log.info("on reintialise la recherche");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset")));
		click(By.id("reset"));
		
		wait_avec_sleep(3000);
		List <WebElement> examens = driver.findElements(By.xpath("//span[contains(text(),'BIOSITES')]"));
	    Assert.assertTrue(examens.size()>0,"y a  pas des fichiers");
	}
	
	public void verifier_existence_alerte_dans_la_partie_donnee_importantes() {
		Log.info("on verifie existence alerte dans la partie donnee importante");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='BIOSITES1']")));
		try {
		click( By.xpath("//span[text()='BIOSITES1']"));
		}catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='imported-data']")));
	}
	
	
	
	
	
	
private void wait_avec_sleep(long seconds) {
	try {
		Thread.sleep(seconds);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
