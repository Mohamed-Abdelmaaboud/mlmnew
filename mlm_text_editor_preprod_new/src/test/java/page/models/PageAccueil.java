package page.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import clm.interop.Log;


public class PageAccueil extends BasePage {

	By boutonAccepterPopupNoteCentre = By.id("notePopupAceptar");
	By menuGestionFse = By.id("fse");
	By selectionCodeSpecialite = By.xpath("//strong[contains(text(),'Médecine générale')]");
	By boutonSelectionner = By.id("selectButton");
	By boutoncarteVitale = By.id("scanCardComponent");
	By barreMenuGestionFSE = By.id("fseManagementMenu");
	By champ_rechercher_patient = By.id("searchPatientInput");
	Actions action = new Actions(driver);
	LocalDateTime date_du_jour = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm-dd/MM/yyyy");

	public void accepterNoteCentre() {
		Log.info("j'accepte la note du centre");
		if (elementIsPresentOnDOM(boutonAccepterPopupNoteCentre)) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(boutonAccepterPopupNoteCentre));
			click(boutonAccepterPopupNoteCentre);
			//click(boutonAccepterPopupNoteCentre);
		}
	}
	
	
	
	public void lireCarteVitale() {
		Log.info("on lit la carte vitale");
		click(boutoncarteVitale);	
	}
	
	public void ouvrirGestionFSE(String menu) {
		Log.info("on ouvre un menu de la barre des menus");
		wait.until(ExpectedConditions.visibilityOfElementLocated(barreMenuGestionFSE));
		click(barreMenuGestionFSE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]", menu))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]", menu)));
		
	}
	
	
	public void ouvrirMenuDeGauche(String menu) {
		Log.info("on ouvre un menu de la gauche avec le fond bleu");
		click(By.xpath(String.format("//span[contains(text(),'%s')]", menu)));
		
	}

	public void selectionnerSituation() {
		Log.info("on clique sur le boton selectionner de la popup liste des situations");
		wait.until(ExpectedConditions.visibilityOfElementLocated(boutonSelectionner));
		click(boutonSelectionner);
		
		
	}
	public void rechercherLePatientAvecLeNomEtOuvrirSonDossierMedical(String patientName) {
		Log.info("On recherche le patient avec le nom : "+ patientName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_rechercher_patient));
		input(patientName, champ_rechercher_patient);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName)));
		Log.info("On a bien trouvé le patient avec le nom : "+ patientName);	
	}
	
	
	public void rechercherLePatientAvecDateDeNaissanceEtOuvrirSonDossierMedical(String date_de_naissance,String patientName) {
		Log.info("On recherche le patient avec le nom : "+ patientName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_rechercher_patient));
		input(date_de_naissance, champ_rechercher_patient);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName))));
		click(By.xpath(String.format("//li[@class='list-group-item'][1]//a[contains(text(),'%s')]/..//a[@title='Accès au dossier médical']",patientName)));
		Log.info("On a bien trouvé le patient avec la date de naissance : "+ patientName);	
	}
	public void rechercherLePatientAvecDateDeNaissanceEtOuvrirSonDossierMedical2(String date_de_naissance,String dossier) {
		Log.info("On recherche le patient avec le nom : ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_rechercher_patient));
		input(date_de_naissance, champ_rechercher_patient);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='search']")));
		click(By.xpath("//img[@alt='search']"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeListado"));
		
		Log.info("On a bien trouvé le patient avec la date de naissance : ");	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']",date_de_naissance))));
		click(By.xpath(String.format("//a[text()='%s']",date_de_naissance)));
	}
	
	public void ouvrirMenuDeLaBarreDesMenus(String menu) {
		Log.info("J'ouvre un menu de la gauche avec le fond bleu");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]",menu))));
		click(By.xpath(String.format("//a[contains(text(),'%s')]",menu)));
	}
	
	public void ouvrirSousMenuDeLaBarreDesMenus(String menu) {
		Log.info("J'ouvre un menu de la gauche avec le fond bleu");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]",menu))));
		WebElement element = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]",menu)));
		action.moveToElement(element).perform();
	}
	
	public void verifier_existence_des_fichiers_envoye_depuis_bioserveur() {
	Log.info("on rafraichit la page chaque 15 secondes pour recevoir des fichiers");
     List <WebElement> fichiers =driver.findElements(By.xpath("//h5[@class='notification-messages-con']/following::a[text()='Cliquez ici pour les visualiser']"));
//	WebDriverWait wait = new WebDriverWait(driver,15);
	for(int i = 0;i < 20; i++) {
		if(fichiers.size()==0) {
			driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfAllElements(fichiers));
		}
	}
	wait.until(ExpectedConditions.visibilityOfAllElements(fichiers));
	Log.info("on clique sur cliquez ici pour visualiser");
    click(By.xpath("//h5[@class='notification-messages-con']/following::a[text()='Cliquez ici pour les visualiser']"));
	}
	
	
	public void ajouter_un_model() {
		Log.info("on choisi gestions des documents types");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Listes et documents']")));
		click(By.xpath("//a[text()='Listes et documents']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@aria-labelledby]/li[1]//a[text()='Documents médicaux']")));
		hover(By.xpath("//ul[@aria-labelledby]/li[1]//a[text()='Documents médicaux']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gestion des documents types']")));
		//wait_avec_sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Gestion des documents types']"))).click().perform();
		//click(By.xpath("//ul[@aria-labelledby]/li[1]//a[text()='Gestions des documents types']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ajouter un modèle']")));
	   click(By.xpath("//button[text()='Ajouter un modèle']"));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Text Editor']")));
	   click(By.xpath("//a[text()='Text Editor']"));
	   wait_avec_sleep(6000);
	}
	
	
	
	
	public void verifier_editeur_de_text_ouvert_apres_choisir_non() {
		Log.info("on verifie que l'onglet editeur de texte est ouvert apres taper sur non et retourner sur mlm");
		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		Log.info("l'editeur du text reste ouvert");
	}
	
	public void verifier_exitence_du_modele(String typeModele,String typeFormat) {
		String modele ="Modèle" + formatter.format(date_du_jour);
		Log.info("on verifie existence modele sur mlm");
		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(0));
		
		Log.info("on cherche le modele par modele et type de modele et type de format");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='buscar']")));
		input(modele,By.xpath("//input[@id='buscar']"));
		
		
		Select type_modele = new Select(driver.findElement(By.id("plantillaWord.tipoPlantilla")));
		type_modele.selectByVisibleText(typeModele);
	
		Select type_format = new Select(driver.findElement(By.id("plantillaWord.templateFormat")));
		type_format.selectByVisibleText(typeFormat);
		
		click(By.xpath("//button[text()='Rechercher']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//table[@id='listadoPlantillaWord']//td[text()='s']",modele ))));
	
	}
	
	public void choisir_gestion_des_documents_types() {
		Log.info("on choisi gestions des documents types");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Listes et documents']")));
		click(By.xpath("//a[text()='Listes et documents']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@aria-labelledby]/li[1]//a[text()='Documents médicaux']")));
		hover(By.xpath("//ul[@aria-labelledby]/li[1]//a[text()='Documents médicaux']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gestion des documents types']")));
		//wait_avec_sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Gestion des documents types']"))).click().perform();
		//click(By.xpath("//ul[@aria-labelledby]/li[1]//a[text()='Gestions des documents types']"));
		
	}
	
	
	
	private void wait_avec_sleep(int seconds){
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
