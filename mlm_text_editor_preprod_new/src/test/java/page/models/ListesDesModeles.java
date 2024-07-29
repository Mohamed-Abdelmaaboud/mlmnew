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


public class ListesDesModeles extends BasePage {

	By boutonAccepterPopupNoteCentre = By.id("notePopupAceptar");
	By menuGestionFse = By.id("fse");
	By selectionCodeSpecialite = By.xpath("//strong[contains(text(),'Médecine générale')]");
	By boutonSelectionner = By.id("selectButton");
	By boutoncarteVitale = By.id("scanCardComponent");
	By barreMenuGestionFSE = By.id("fseManagementMenu");
	By champ_rechercher_patient = By.id("searchPatientInput");
	By logoMLM = By.id("img_logo");
	By bouton_Profil = By.id("userOptionsBtn");
	By bouton_deconnexion = By.xpath("//a[contains(text(),'Déconnexion')]");
	Actions action = new Actions(driver);
	LocalDateTime date_du_jour = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String date = formatter.format(date_du_jour);
	
	
	
	
	public void ajouter_un_modele() {
		Log.info("on ajoute un modele");
	
	  	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ajouter un modèle']")));
		   click(By.xpath("//button[text()='Ajouter un modèle']"));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Text Editor']")));
		   click(By.xpath("//a[text()='Text Editor']"));
		   wait_avec_sleep(10000);
	}
	
	public void ajouter_un_entete() {
		
	
		   Log.info("on ajoute un entete");
	  	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ajouter un entête']")));
		   click(By.xpath("//button[text()='Ajouter un entête']"));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Editeur de texte']")));
		   click(By.xpath("//button[text()='Editeur de texte']"));
		   wait_avec_sleep(6000);
	}
	
	public void verifier_editeur_de_text_ouvert_apres_choisir_non() {
		Log.info("on verifie que l'onglet editeur de texte est ouvert apres taper sur non et retourner sur mlm");
		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		Log.info("l'editeur du text reste ouvert");
	}
	
	public void verifier_exitence_du_modele(String typeModele,String typeFormat,String modele_num) {
		 String modele =  "Modèle1" + date + modele_num;
		Log.info("on verifie existence modele sur mlm");
		Log.info("on cherche le modele par modele et type de modele et type de format");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='buscar']")));
		//clearInput(By.xpath("//input[@id='buscar']"));
		input(modele,By.xpath("//input[@id='buscar']"));
		
		
		Select type_modele = new Select(driver.findElement(By.id("plantillaWord.tipoPlantilla")));
		type_modele.selectByVisibleText(typeModele);
	
		Select type_format = new Select(driver.findElement(By.id("plantillaWord.templateFormat")));
		type_format.selectByVisibleText(typeFormat);
		
		click(By.xpath("//button[text()='Rechercher']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//table[@id='listadoPlantillaWord']//td[contains(text(),'%s')]",modele))));
	
	}
	
	public void verifier_exitence_du_entete(String typeModele,String typeFormat,String modele_num) {
		 String entete =  "Entête1" + date + modele_num;
		Log.info("on verifie existence modele sur mlm");
		Log.info("on cherche le modele par modele et type de modele et type de format");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='buscar']")));
		//clearInput(By.xpath("//input[@id='buscar']"));
		input(entete,By.xpath("//input[@id='buscar']"));
		
		
		Select type_modele = new Select(driver.findElement(By.id("plantillaWord.tipoPlantilla")));
		type_modele.selectByVisibleText(typeModele);
	
		Select type_format = new Select(driver.findElement(By.id("plantillaWord.templateFormat")));
		type_format.selectByVisibleText(typeFormat);
		
		click(By.xpath("//button[text()='Rechercher']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//table[@id='listadoPlantillaWord']//td[contains(text(),'%s')]",entete))));
	
	}
	
	public void seDeconnecter() {
		Log.info("On peut se deconnecter");
		click(bouton_Profil);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_deconnexion));
		click(bouton_deconnexion);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_deconnexion));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(menu_dossier_medical));
		Log.info("On est bien deconnecté,");
	}
	
	
	 public void switchTowindow(int window) {
			List <String> windows = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windows.get(window));
			wait_avec_sleep(10000);
		}
	
	
	public void retourAccueil() {
		Log.info("On retourne à l'accueil");
		wait.until(ExpectedConditions.visibilityOfElementLocated(logoMLM));
		click(logoMLM);
		Log.info("On est bien retourné à l'accueil");
		
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
