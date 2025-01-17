package page.module;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import clm.interop.Log;
import clm.interop.enumerator.Position;
import page.models.BasePage;

public class ModuleAjoutProduits extends BasePage{
	
	By module_ajout_produit = By.id("winPopup");
	By champ_recherche_produit = By.cssSelector("[id='productBarSearchForm:query:queryControl']");
	By bouton_rechercher = By.cssSelector("[id='productBarSearchForm:searchButton']");
	By tableau_liste_produit = By.cssSelector("[id='productSearchForm:listProducts']");
	By frame_module_ajout_produit = By.cssSelector("[id='iframe-pres-popup']");
	By formulaire_posologie = By.cssSelector("[id='posologySelectForm']");
	By label_liste_posologies = By.xpath("//span[contains(text(),'Liste posologies')]");
	By formulaire_edition_posologie = By.cssSelector("[id='posologyEditForm']");
	By champ_nombre_dose_unitaire = By.cssSelector("[id='posologyEditForm:doseQuantity1Input']");
	By champ_dose_unitaire_poids  = By.xpath("//div[@id='posologyEditForm:productUnitId']");
	By label_dose_unitaire = By.xpath("//label[@for='posologyEditForm:doseQuantity1Input']");
	By bouton_plus_dose_unitaire = By.cssSelector("[id='increaseDoseQuantity1Input-btn']");
	By bouton_moins_dose_unitaire = By.cssSelector("[id='decreaseDoseQuantity1Input-btn']");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	By bouton_matin_et_soir = By.cssSelector("[id='posologyEditForm:priseShortcut:0']");
	By bouton_matin_midi_et_soir = By.cssSelector("[id='posologyEditForm:priseShortcut:1']");
	By bouton_plus_pendant = By.cssSelector("[id='increaseDuring1-btn']");
	By bouton_moins_pendant = By.cssSelector("[id='decreaseDuring1-btn']");
	By label_pendant = By.xpath("//label[@for='posologyEditForm:during1']");
	By champ_pendant = By.cssSelector("[id='posologyEditForm:during1']");
	By select_unite_de_temps = By.cssSelector("[id='posologyEditForm:durationRange_label']");
	By loader = By.cssSelector("[class='fa fa-circle-o-notch fa-spin ajax-loader']");
	By bouton_prescrire = By.cssSelector("[id='posologyEditForm:buttonPrescribe']");
	By bouton_prescrire_et_fermer = By.cssSelector("[id='posologyEditForm:buttonPrescribeAndClose']");
	By label_confirmation_posologie = By.cssSelector("[id='posologyEditForm:listPosologyStructured_data']");
	By bouton_matin = By.cssSelector("[id='posologyEditForm:priseMomentsList:0']");
	//By bouton_puis = By.id("posologyEditForm:j_id_49");
	By recherche_button = By.xpath("//span[text()='Rechercher']");

	By bouton_puis = By.xpath("//button[@id='posologyEditForm:addNewPosologyThenButton']");
	By bouton_et = By.xpath("//button[@id='posologyEditForm:addNewPosologyAndButton']");
	//By bouton_et = By.id("posologyEditForm:j_id_48");
	By bouton_maximum = By.id("posologyEditForm:maximumEnabled");
	By bouton_fermer_module_ajout_produit = By.xpath("//div[contains(@class,'x-tool x-tool-close')]");
	By bouton_ne_pas_delivrer = By.cssSelector("[id='posologyEditForm:buttonDeliver']");
	By select_list_non_substituable = By.cssSelector("[id='posologyEditForm:nonSubstituableDropdown']");
	By bouton_ALD = By.cssSelector("[id='posologyEditForm:buttonALD']");
	By bouton_Nepasdelivrer = By.cssSelector("[id='posologyEditForm:buttonDeliver']");
	By bouton_Chronique = By.cssSelector("[id='posologyEditForm:buttonChronic']");
	By bouton_Substituable = By.cssSelector("[id='posologyEditForm:nonSubstituableDropdown']");
	By bouton_plus_renouvellement = By.cssSelector("[id='increaseRenewInput-btn']");
	By champ_renouvellement = By.cssSelector("[id='posologyEditForm:renewInput']");
	By label_renouvellement = By.cssSelector("[for='posologyEditForm:renewColumn']");
	By bouton_confirmation_prescription = By.xpath("//span[text()='Oui']");
	By bouton_confirmation_ajout_produit_nouvelle_fois = By.xpath("//button[@id='posologyEditForm:j_id_8u']");
	By bouton_confirmation_ajout_produit_oui = By.xpath("//div[contains(@id,'confirmAddDuplicateProduct')]//span[text()='Oui']");

	By bouton_confirmation_ajout_produit_nouvelle_fois2 = By.id("posologyEditForm:j_id_8u");
	By champ_nombre_boite = By.cssSelector("[id='posologyEditForm:boxes']");
	By champ_frequence_de_prise = By.cssSelector("[id='posologyEditForm:frequencyText']");
	By select_frequence = By.xpath("//*[@id='posologyEditForm:frequenceRange_label']");
	By pendant_select=By.xpath("//span[text()='semaine']");
	By select_unite_mesure_dose = By.cssSelector("[id='posologyEditForm:productUnitId_label']");
	By button_alert = By.xpath("//button[contains(@id,'contraindications_button')]");
	By button_alert_allergies = By.xpath("//button[contains(@id,'allergies_button')]");
	By button_redondance_alert = By.xpath("//button[@id='alertPrescriptionForm:overdose_button']");
	By champs_type_de_recherche = By.xpath("//div[@id='productBarSearchForm:typeSearch:typeSearchComponent']");
	By recherche_par_dc = By.xpath("//li[text()='Recherche par D.C.']");
	By recherche_par_accessoire = By.xpath("//li[text()='Accessoires génériques']");
	By recherche_par_ATC = By.xpath("//li[text()='ATC']");
	By recherche_par_Principe_Actif = By.xpath("//li[text()='Principe actif']");
	By recherche_par_Indications = By.xpath("//li[text()='Indications']");
	By recherche_par_Lab = By.xpath("//li[text()='Laboratoire']");
	By champs_recherche_produit = By.xpath("//div[@id='productBarSearchForm:query:j_id_1t']//input");
	By button_info = By.xpath("//button[@id='alertPrescriptionForm:info_button']");
	By button_jaune =By.xpath("//button[contains(@id,'posologyAlert_button')]");
	By button_rouge =By.xpath("//button[contains(@class,'highAlert')]");
	By button_controle_posolgie = By.xpath("//a[text()='Contrôle posologie']");
	By alert_IPC = By.xpath("//button[@id='alertPrescriptionForm:ipc_button' and contains(@class,'highAlert')]");
	By alert_redondance=By.xpath("//button[@id='alertPrescriptionForm:overdose_button']");
	By button_option_recherche = By.xpath("//button[@id='productBarSearchForm:searchOptions']");
	By bouton_confirmation_prescription2 = By.xpath("//button[contains(@onclick,'confirmAddDefaultPrescription')]//span[text()='Oui']");
	
	public ModuleAjoutProduits() {
		Log.info("On attends que le module ajout produit soit bein affiché ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(module_ajout_produit));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_module_ajout_produit));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_produit));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_rechercher));
		Log.info("Le module ajout produit est bien affiché ");
	}

	
	public void rechercherUnProduit(String produitNom) {
		Log.info("On recherche le produit " + produitNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_produit));
		input(produitNom, champ_recherche_produit);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	public void supprimerProduit() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_produit));
		clearInput(champ_recherche_produit);
	}
	
	public void prescriptionLibre(String med,String posology) throws InterruptedException {
		Log.info("On cree une prescription libre");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Prescription libre']")));
		click(By.xpath("//span[text()='Prescription libre']"));
		Log.info("On saisie produit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@id,'productDescription')]")));
		input(med,By.xpath("//textarea[contains(@id,'productDescription')]"));
		Log.info("On renseigne posology");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@id,'posologyDescription')]")));
	   input( posology,By.xpath("//textarea[contains(@id,'posologyDescription')]"));
	   Log.info("On clique precrire et imprimer");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Prescrire et imprimer']")));
		click(By.xpath("//span[text()='Prescrire et imprimer']"));
		Thread.sleep(5000);
	}
	
	public void click_button_biosimilaire_pour_ouvrir_une_liste_biosimilaire(String medicament_biosimilaire) {
		Log.info(String.format("on clique button biosimilaire de medicament %s",medicament_biosimilaire));
		click(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[5]/a[contains(@id,'showBiosimilarButton')]", medicament_biosimilaire)));
	    Log.info("une liste biosimilaire doit s'afficher");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Liste des biosimilaire')]//following::div[contains(@id,'listBioSimilar')]")));
	}
	public void choisirbuttonRechercheOptionHospitaliers() {
		Log.info("on choisit options recherche non hospitaliers");
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_option_recherche));
		click(button_option_recherche);
		WebElement button_hospitalier = driver.findElement(By.xpath("//div[@id='productBarSearchForm:showHospitals']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='productBarSearchForm:showHospitals']")));
		String classValue = button_hospitalier.getAttribute("class");
       // String expecValue = "checked";
        
//        if(classValue.contains("checked")) {
//        	click(button_hospitalier);
//        }else {
//        	Log.info("button n'est pas checked");
//        }
		
		
        if(classValue.contains("checked")) {
        	Log.info("button est  checked");
        	
        }else {
        	Log.info("on checke le button");
        	click(button_hospitalier);
        }
		
	}
	
	public void inclue_produit_hospitaliers() {
		Log.info("on exclut les produits dopants en activant bouton hospitaliers");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Configuration']")));
	    click(By.xpath("//button[@title='Configuration']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='productBarSearchForm:showHospitals']")));
	   click(By.xpath("//div[@id='productBarSearchForm:showHospitals']"));
	}
	public void rechercherUnProduitParAccessoire(String produitNom) {
		Log.info("On recherche le produit par Dc" + produitNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_accessoire));
		click(recherche_par_accessoire);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		try {
		click(champs_recherche_produit);
		}catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}
		input(produitNom,champs_recherche_produit);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	
	
	public void rechercherUnProduitParDC(String produitNom) {
		Log.info("On recherche le produit par Dc" + produitNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_dc));
		click(recherche_par_dc);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		try {
		click(champs_recherche_produit);
		}catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}
		input(produitNom,champs_recherche_produit);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	

	public void rechercherUnProduitParDC(String produitNom1,String produitNom2) {
		Log.info("On recherche le produit par Dc" + produitNom1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_dc));
		click(recherche_par_dc);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		//try {
		//click(champs_recherche_produit_DC);
		//}catch(StaleElementReferenceException e) {
		//e.printStackTrace();
		//}
		for (int i = 0; i < 10; i++) {
			try {
				input(produitNom1,champs_recherche_produit);
			  break;
			}catch (StaleElementReferenceException e) {
			}
		}		
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'%s')]",produitNom2))));
		Log.info("On clique le produit par DC" + produitNom2);
		click(By.xpath(String.format("//td[contains(text(),'%s')]",produitNom2)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	
	public void confirmerPrescription2() {
		Log.info("On Clique sur oui pour confirmer la prescription");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_confirmation_prescription2));
		click(bouton_confirmation_prescription2);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_confirmation_prescription));
		Log.info("La prescription a été faite");
	}
	
	
	public void rechercherUnProduitParPricipeActif(String produitNom) {
		Log.info("On recherche le produit par Dc" + produitNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_Principe_Actif));
		click(recherche_par_Principe_Actif);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		   wait(3000);
		js.executeScript("arguments[0].click()", driver.findElement(champs_recherche_produit));
	    wait(3000);
		input(produitNom,champs_recherche_produit);
		//WebElement rechercheParDC = driver.findElement(champs_recherche_produit_DC);
		//rechercheParDC.sendKeys(produitNom);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",produitNom))));
		click(By.xpath(String.format("//td[text()='%s']",produitNom)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	
	public void ajoutCommentaireDansEditerLaPosologie(String numerique) {
		Log.info("on ajoute des commentaires dans editer la posologie");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("posologyEditForm:noteBeforeId")));
		input(numerique,By.id("posologyEditForm:noteBeforeId"));
	}
	
	public void rechercherUnProduitParIndications(String produitNom) {
		Log.info("On recherche le produit par Dc" + produitNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_Indications));
		click(recherche_par_Indications);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		try {
		click(champs_recherche_produit);
		}catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}
		try {
			click(champs_recherche_produit);
			}catch(StaleElementReferenceException e) {
				e.printStackTrace();
			}
		input(produitNom,champs_recherche_produit);
		//WebElement rechercheParDC = driver.findElement(champs_recherche_produit_DC);
		//rechercheParDC.sendKeys(produitNom);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",produitNom))));
		click(By.xpath(String.format("//td[text()='%s']",produitNom)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	
	
	public void rechercherParLaboratoire(String labNom) {
		Log.info("On recherche le produit par Dc" + labNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_Lab));
		click(recherche_par_Lab);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		try {
		click(champs_recherche_produit);
		}catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}
		try {
			click(champs_recherche_produit);
			}catch(StaleElementReferenceException e) {
				e.printStackTrace();
			}
		input(labNom,champs_recherche_produit);
		//WebElement rechercheParDC = driver.findElement(champs_recherche_produit_DC);
		//rechercheParDC.sendKeys(produitNom);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",labNom))));
		click(By.xpath(String.format("//td[text()='%s']",labNom)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
		Log.info("on ouvrt la mongraphie d'un medicament");
	
		
		
	}
	
	public void verifierExistenceLabRechercheDansLaMonographie(String produitNom,String labNom) {
		Log.info("on ouvrt la mongraphie d'un medicament pour verifier l'existence de la recherche ");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//tr[@data-ri='1']//span[contains(text(),'%s')]//following::td[2]",produitNom))));
		//click(By.xpath(String.format("//tr[@data-ri='1']//span[contains(text(),'%s')]//following::td[2]",produitNom)));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[2]",produitNom))));
		click(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[2]",produitNom)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(.,'%s')]",labNom))));
	
	}
	
	
	public void exclue_produit_dopants() {
		Log.info("on exclut les produits dopants en deactivant bouton dopants");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Configuration']")));
	    click(By.xpath("//button[@title='Configuration']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='productBarSearchForm:hideDopants']")));
	   click(By.xpath("//div[@id='productBarSearchForm:hideDopants']"));
	}
	public void inclue_produit_dopants() {
		Log.info("on exclut les produits dopants en deactivant bouton dopants");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Configuration']")));
	    click(By.xpath("//button[@title='Configuration']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='productBarSearchForm:hideDopants']")));
	   click(By.xpath("//div[@id='productBarSearchForm:hideDopants']"));
	}
	
	public void ouvrir_monographie(String medicament) {
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence de la recherche ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[2]",medicament))));
		click(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[2]",medicament)));
	}
	public void ouvrir_monographie2(String medicament) {
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence de la recherche ");
		//driver.switchTo().frame("iframe-pres-popup");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-pres-popup"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[2]",medicament))));
	
		click(By.xpath(String.format("//span[contains(text(),'%s')]//following::td[2]",medicament)));
	}
	public void fermer_monographie() {
		Log.info("on ferme la monographie");
		driver.switchTo().parentFrame();
		click(By.xpath("//div[@id='productSearchForm:mongraphyPanel']/div[1]/a[@aria-label='Close']"));
	
	}
	public void verifier_existence_photo_de_la_boite() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//WebElement iframe = driver.findElement(By.xpath("//*[@id='productSearchForm:mongraphyPanel']/div[2]/iframe"));
		//driver.switchTo().frame(iframe);
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		String window = windows.get(1);
		driver.switchTo().window(window);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ancre_IMAGEPRODUIT']/table/tbody/tr/td/img")));
		 driver.switchTo().window(windows.get(0));
	}
	public void verifier_existence_info_Donnee_technico(String prix,String taux,String agrement) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	WebElement iframe = driver.findElement(By.xpath("//*[@id='productSearchForm:mongraphyPanel']/div[2]/iframe"));
	//	driver.switchTo().frame(iframe);
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		String window = windows.get(1);
		driver.switchTo().window(window);
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info donne tehnico");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ancre_DONNEESTECHREG']/a/span")));
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info prix de vente");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'%s')]",prix))));
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info taux de remboursement");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",taux))));
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info prix de vente agrement de collectivite");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",agrement))));
	    driver.switchTo().window(windows.get(0));
	}
	
	public void verifier_existence_info_Donnee_technico(String agrement) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	WebElement iframe = driver.findElement(By.xpath("//*[@id='productSearchForm:mongraphyPanel']/div[2]/iframe"));
	//	driver.switchTo().frame(iframe);
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		String window = windows.get(1);
		driver.switchTo().window(window);
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info donne tehnico");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ancre_DONNEESTECHREG']/a/span")));
		//Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info prix de vente");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'%s')]",prix))));
		//Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info taux de remboursement");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",taux))));
		Log.info("on ouvre la mongraphie d'un medicament pour verifier l'existence info prix de vente agrement de collectivite");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",agrement))));
	    driver.switchTo().window(windows.get(0));
	}
	public void rechercherUnProduitParATC(String produitNom) {
		Log.info("On recherche le produit par ATC" + produitNom );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_ATC));
		click(recherche_par_ATC);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_recherche_produit));
		try {
		click(champs_recherche_produit);
	    }catch(StaleElementReferenceException e) {
			e.printStackTrace();
	    }
		input(produitNom,champs_recherche_produit);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",produitNom))));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
	
	public void rechercherUnProduitParATC(String produitNom1,String produitNom2) {
		Log.info("On recherche le produit par ATC" + produitNom1 );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champs_type_de_recherche));
		click(champs_type_de_recherche);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recherche_par_ATC));
		click(recherche_par_ATC);
		wait(3000);
		js.executeScript("arguments[0].click()", driver.findElement(champs_recherche_produit));
	    wait(3000);
		input(produitNom1,champs_recherche_produit);
		//input(produitNom,champs_recherche_produit_DC);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",produitNom2))));
		Log.info("On clique le produit par ATC" + produitNom2);
		click(By.xpath(String.format("//td[text()='%s']",produitNom2)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
		Log.info("Les resultats de la recherche sont bien affichés");
	}
   

	public void convertirEnDC(String medicament,int row) {
		Log.info("on converti le medicament en DC");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]//preceding::button[contains(@onclick,'openDCWindowForRow(%d)')]",medicament,row))));
		Log.info("on click button convertir en DC");
		click(By.xpath(String.format("//a[contains(text(),'%s')]//preceding::button[contains(@onclick,'openDCWindowForRow(%d)')]",medicament,row)));
	}
	
	
	public void choisirProduitTrouveEnDC(String produitNomDC) {
		
		By produit_trouve_en_DC = By.xpath(String.format("//td[contains(text(),'%s')]",produitNomDC));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(produit_trouve_en_DC));
		click(produit_trouve_en_DC);
	}
	
public void choisirProduitTrouveEnBiosimilaireListe(String produit_biosimilaire) {
		
		By produit_trouve_en_Biosimilaire = By.xpath(String.format("//td[contains(text(),'%s')]",produit_biosimilaire));
		wait.until(ExpectedConditions.visibilityOfElementLocated(produit_trouve_en_Biosimilaire));
		click(produit_trouve_en_Biosimilaire);
	}
	
	public void ouvrirChoixDeLaPosologie(String descriptionProduit) {
		Log.info("On ouvre la page choix de la posologie du produit :" + descriptionProduit);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@title,'%s')]",descriptionProduit))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[@title='%s']",descriptionProduit))));
		for (int i = 0; i < 10; i++) {
			try {
			  WebElement element = driver.findElement(By.xpath(String.format("//span[contains(@title,'%s')]",descriptionProduit)));
			  click(element);
			  break;
			}catch (StaleElementReferenceException e) {
			}
		}		
		// org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document correction ci dessus
		//click(By.xpath(String.format("(//span[@title='%s']/../following-sibling::td)[5]//a[contains(@id, 'buttonEditGoPosology')]",descriptionProduit)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(formulaire_posologie));
		Log.info("Le choix de la posologie du produit" + descriptionProduit + "est bien ouverte");
	}
	public void ouvrirChoixDeLaPosologieEtValiderAlertSAM(String descriptionProduit) {
		Log.info("On ouvre la page choix de la posologie du produit :" + descriptionProduit);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@title,'%s')]",descriptionProduit))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[@title='%s']",descriptionProduit))));
		for (int i = 0; i < 10; i++) {
			try {
			  WebElement element = driver.findElement(By.xpath(String.format("//span[contains(@title,'%s')]",descriptionProduit)));
			  click(element);
			  break;
			}catch (StaleElementReferenceException e) {
			}
		}		
		// org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document correction ci dessus
		//click(By.xpath(String.format("(//span[@title='%s']/../following-sibling::td)[5]//a[contains(@id, 'buttonEditGoPosology')]",descriptionProduit)));
		  wait(3000);
		valider_alerte_SAM();
//		List<WebElement> alerte_sam = driver.findElements(By.xpath("//div[@id='productSearchForm:samAlertDialog']"));
//        wait(3000);
//		if(alerte_sam.size()>0) {
//			
//			Log.info("on valide Alerte SAM");
//			
//			click(By.xpath("//button[contains(@id,'btn_go_edit_prescription')]"));
//		}else {
//			Log.info("y a pas Alerte SAM");
//		}
	
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(formulaire_posologie));
		Log.info("Le choix de la posologie du produit" + descriptionProduit + "est bien ouverte");
	}
  
	
	public void RechercheNouveauMedicament() {
	Log.info("on clique le bouton retour pour retourner a la recherche du medicament");
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Retour']")));
	//click(By.xpath("//span[text()='Retour']"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(champ_recherche_produit));
	clearInput(champ_recherche_produit);
}
	
	
	public void ajouterProduitAvecPosologieUsuelle() {
		Log.info("on ajoute produit avec posologie usuelle");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Ajouter produit avec cette posologie']")));
		click(By.xpath("//i[@title='Ajouter produit avec cette posologie']"));
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ajouterProduitAvecPosologieUsuell() {
		Log.info("on ajoute produit avec posologie usuelle");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Ajouter produit avec posologie usuelle']")));
		click(By.xpath("//i[@title='Ajouter produit avec posologie usuelle']"));
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editerPosologie(String descriptionPosologie) {
		Log.info("On va editer la posologie" + descriptionPosologie );
		wait.until(ExpectedConditions.visibilityOfElementLocated(formulaire_posologie));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",descriptionPosologie))));
		click(By.xpath(String.format("//span[contains(text(),'%s')]",descriptionPosologie)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.visibilityOfElementLocated(formulaire_edition_posologie));
		Log.info("Le formulaire edition posologie est bien affiché");
		
	}
	
	
	
	public void ajouterNombreDeDoseUnitaire(String nombreDose) {
		Log.info("On ajouter le nombre de dose" + nombreDose );
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_nombre_dose_unitaire));
		WebElement input_dose_unitaire = driver.findElement(champ_nombre_dose_unitaire) ;
		input_dose_unitaire.sendKeys("\u0008");
		wait(3000);
		input_dose_unitaire.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),nombreDose);
		wait(3000);
		//js.executeScript(("arguments[0].click();"), driver.findElement(label_dose_unitaire));
		click(label_dose_unitaire);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.attributeContains(champ_nombre_dose_unitaire,"value",nombreDose));
		Log.info("Le nombre de dose" + nombreDose +"a été ajoutée avec success" );	
	}
	
	public void choisir_dose_unitaire_unite(String unite) {
		Log.info("on choisit la dose unitaire unite");
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_dose_unitaire_poids));
		click(champ_dose_unitaire_poids);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[@id='posologyEditForm:productUnitId_panel']//li[contains(text(),'%s')]",unite))));
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath(String.format("//div[@id='posologyEditForm:productUnitId_panel']//li[contains(text(),'%s')]",unite))));
		//click(By.xpath(String.format("//div[@id='posologyEditForm:productUnitId_panel']//li[text()='%s']",unite)));
	}

	public void choisirMomentDeLaPrise(String moment ) {
		Log.info("On choisit le moment de la prise :" + moment);
		//js.executeScript("arguments[0].click()", driver.findElement(By.xpath(String.format("//span[text() = '%s']",moment))));
		click(By.xpath(String.format("//span[text() = '%s']",moment)));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath(String.format("//span[text() = '%s']/../input[contains(@id,'posologyEditForm')]",moment))));
		Log.info("Le moment de la prise : " + moment + " a bien été choisie");
	}
	
	public void deSelectionnerMomentDeLaPrise(String moment ) {
		Log.info("On deselectionne le moment de la prise :" + moment);
		click(By.xpath(String.format("//span[text() = '%s']",moment)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.xpath(String.format("//span[text() = '%s']/../input[contains(@id,'posologyEditForm')]",moment))),false));
		Log.info("Le moment de la prise : " + moment + " a bien été deselectionné");
	}
	
	public void cliquerBoutonET() {
		Log.info("On clique sur le bouton ET pour compléter la posologie");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_et));
		click(bouton_et);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Log.info("Nous pouvons compléter la posologie");
	}
	
	public void cliquerBoutonPuis() {
		Log.info("On clique sur le bouton puis pour compléter la posologie");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_puis));
		click(bouton_puis);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Log.info("Nous pouvons compléter la posologie");
	}
	
	
	public void ajouterDureeDeLaPrise(String duree) {
		Log.info("On ajoute la durée de la prise");
		WebElement element = driver.findElement(label_pendant);
		js.executeScript("arguments[0].click();",element);
		//click(label_pendant);
		WebElement input_champ_pendant = driver.findElement(champ_pendant) ;
		js.executeScript("arguments[0].click();",input_champ_pendant);
		input_champ_pendant.sendKeys("\u0008");
		wait(3000);
		input_champ_pendant.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),duree);
		wait(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		js.executeScript("arguments[0].click();",element);
		//click(label_pendant);
		wait.until(ExpectedConditions.attributeContains(champ_pendant,"value",duree));
		Log.info("La durée " + duree + "a été ajoutée avec success" );
	}
	public void ajouterDureeDeLaPrise2(String duree) {
		Log.info("On ajoute la durée de la prise 2");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='posologyEditForm:during2']")));
		clearInput(By.xpath("//input[@id='posologyEditForm:during2']"));
		wait(3000);
		input(duree,By.xpath("//input[@id='posologyEditForm:during2']"));
		wait(3000);
		}
		
	
	public void selectUniteDeTempsDeLaDuree(String uniteTemps) {
		Log.info("On Selectionne soit ,minute,heure,demi-journée,jour,semaine,......." + uniteTemps);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		click(select_unite_de_temps);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='posologyEditForm:durationRange_items']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//li[@data-label='%s'])[2]",uniteTemps))));
		Log.info("Le select s'est ouvert");
		try {
			Thread.sleep(3000);
			//solution temporaire à virer apres si solution trouvée
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript(("arguments[0].click();"), driver.findElement(By.xpath(String.format("(//li[@data-label='%s'])[2]",uniteTemps))));
		//click(By.xpath(String.format("(//li[@data-label='%s'])[2]",uniteTemps)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[id='posologyEditForm:durationRange_items']")));
		Log.info("J'ai cliqué sur :" + uniteTemps);
		click(label_pendant);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	//	wait.until(ExpectedConditions.attributeToBe(By.cssSelector("[id='posologyEditForm:durationRange_label']"),"innerText",uniteTemps));
		Log.info("L'unité de durée " + uniteTemps + " a été bien selectionnée");
	}
	
	public void verifierQueLaPosologieBienAffiché(String posologie) {
		Log.info("On vérfie que la posologie a été bien prise en compte et qu'elle est bien affichée en haut du module");
		wait.until(ExpectedConditions.visibilityOfElementLocated(label_confirmation_posologie));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@title,'2 gélules, matin et soir pendant 50 minutes')]"))));		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(@title,'%s')]",posologie))));
		
		Log.info("La posologie a été bien prise en compte et elle est bien affiché en haut du module");
	}
	 
	
	public void fermerSideBar() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ui-sidebar-close ui-corner-all']")));
		click(By.xpath("//a[@class='ui-sidebar-close ui-corner-all']"));
	}
	
	public void prescrire() {
		Log.info("On Clique sur le bouton Prescrire pour prescrire le produit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_prescrire));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		click(bouton_prescrire);

	}
	
	public void prescrireEtImprimer() {
		 Log.info("On clique precrire et imprimer");
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Prescrire et imprimer']")));
			click(By.xpath("//span[text()='Prescrire et imprimer']"));
	}
	
	public void prescrire_et_fermer() {
		Log.info("On Clique sur le bouton Prescrire et fermerpour prescrire le produit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_prescrire_et_fermer));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		click(bouton_prescrire_et_fermer);

	}
	
	
	public void prescrire2() {
		Log.info("On Clique sur le bouton Prescrire pour prescrire le produit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_prescrire));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		
		click(bouton_prescrire);
	if(driver.findElements(By.xpath("//span[text()='Oui']")).size()>0) {
		WebElement qui=driver.findElement(By.xpath("//span[text()='Oui']"));
		//qui.click();
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", qui);
	}
	else {
	Log.info("no qui button exist");
	}

	}
	
	public void confirmerPrescription() {
		Log.info("On Clique sur oui pour confirmer la prescription");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_confirmation_prescription));
		click(bouton_confirmation_prescription);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_confirmation_prescription));
		Log.info("La prescription a été faite");
	}
	
	public void confirmerAjoutProduitUneNouvelleFois() {
		Log.info("On Clique sur oui pour confirmer l'ajout du produit une nouvelle fois");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_confirmation_ajout_produit_oui));
		click(bouton_confirmation_ajout_produit_oui);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_confirmation_ajout_produit_oui));
		Log.info("Le produit a été ajouté un nouvelle fois");
	}
	
	public void deselectionnerOptionDePrisePendant (String pendant) {
		Log.info("On deselectionne l'option pendant :" + pendant);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//input[contains(@id,'posologyEditForm:during1Button')]/following::span[text() = '%s']",pendant))));
		click(By.xpath(String.format("//input[contains(@id,'posologyEditForm:during1Button')]/following::span[text() = '%s']",pendant)));
		wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.xpath(String.format("//input[contains(@id,'posologyEditForm:during1Button')]/following::span[text() = '%s']/preceding-sibling::input",pendant))),false));
		Log.info("L'option pendant :" + pendant + " a été désélectionnée");	
	}
	public void confirmerAjoutProduitUneNouvelleFois2() {
		Log.info("On Clique sur oui pour confirmer l'ajout du produit une nouvelle fois");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_confirmation_ajout_produit_nouvelle_fois2));
		click(bouton_confirmation_ajout_produit_nouvelle_fois2);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_confirmation_ajout_produit_nouvelle_fois2));
		Log.info("Le produit a été ajouté un nouvelle fois");
	}
	
	public void deselectionneMaximumDePrise() {
		Log.info("On Clique sur le radio bouton maximum pour le desactiver");
		wait.until(ExpectedConditions.attributeContains(bouton_maximum,"class","checked"));
		click(bouton_maximum);
		wait.until(ExpectedConditions.elementSelectionStateToBe(bouton_maximum,false));
		Log.info("Le radio bouton maximum est bien desactivé");
	}
	
	public void desactiverNotes(String note) {
		Log.info("On desactive la note");
		wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(String.format("//input[@id='posologyEditForm:commentBCB:%s']",note)),true));
		WebElement element = driver.findElement(By.xpath(String.format("//input[@id='posologyEditForm:commentBCB:%s']",note)));
		js.executeScript("arguments[0].click();",element);
		wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(String.format("//input[@id='posologyEditForm:commentBCB:%s']",note)),false));
		Log.info("La note a été désactivée");
	}
	
	
	public void ajouterPosologieUsuelle(String productName) {
		Log.info("On ajoute la posologie usuelle");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//span[@title='%s']/../following-sibling::td)[5]//a[contains(@id, 'buttonAddProductWithDefaultPosology')]",productName))));
		click(By.xpath(String.format("(//span[@title='%s']/../following-sibling::td)[5]//a[contains(@id, 'buttonAddProductWithDefaultPosology')]",productName)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		Log.info("la posologie usuelle du produit : " + productName + " a été bien ajoutée");
	}
	
	public void fermerModuleAjoutProduit() {
		Log.info("On ferme le module ajout produit");
		switchToDefault();
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_fermer_module_ajout_produit));
		click(bouton_fermer_module_ajout_produit);
		//js.executeScript("arguments[0].click();",driver.findElement(bouton_fermer_module_ajout_produit));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(module_ajout_produit));
		Log.info("Le module ajout produit est bien fermé ");
	}
	
	public void activerOptionNePasDelivrer() {
		Log.info("On clique sur le bouton ne pas delivrer pour activer l'option ne pas delivrer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ne_pas_delivrer));
		click(bouton_ne_pas_delivrer);
		wait.until(ExpectedConditions.attributeContains(bouton_ne_pas_delivrer, "class", "ui-state-active"));
		Log.info("On active l'option ne pas delivrer");
	}
	
	public void selectionnerOptionSubstituable(String information) {
		Log.info("On clique sur la liste substituable pour choisir : " + information);
		click(select_list_non_substituable);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//li[contains(text(),'%s')]",information))));
		click(By.xpath(String.format("//li[contains(text(),'%s')]",information)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//label[contains(text(),'%s')]",information))));
		Log.info(" l'option" + information + "a été bien choisie");
	}
	
	public void activerOptionALD() {
		Log.info("On clique sur l'option ALD pour activer ALD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ALD));		
		click(bouton_ALD);
		wait.until(ExpectedConditions.attributeContains(bouton_ALD, "class", "ui-state-active"));
		Log.info("L'option ALD a été activée");
	}
	public void activerOptionNepasdelivrer() {
		Log.info("On clique sur l'option Ne pas delivrer  pour activer ALD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_Nepasdelivrer));		
		click(bouton_Nepasdelivrer);
		wait.until(ExpectedConditions.attributeContains(bouton_Nepasdelivrer, "class", "ui-state-active"));
		Log.info("L'option Ne pas delivrer a été activée");
	}
	
	public void activerOptionSubstituable() {
		Log.info("On clique sur l'option bouton_Substituable pour activer ALD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_Substituable));		
		click(bouton_Substituable);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Substituable']")));
		click(By.xpath("//li[text()='Substituable']"));
		wait.until(ExpectedConditions.attributeContains(bouton_Substituable, "class", "ui-state-active"));
		Log.info("L'option bouton_Substituablea été activée");
	}
	
	public void deactiverOptionALD() {
		Log.info("On clique sur l'option ALD pour deactiver ALD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_ALD));		
		if(wait.until(ExpectedConditions.attributeContains(bouton_ALD, "class", "ui-state-default"))) {
			Log.info("le button ALD est pas active");
		}else
			click(bouton_ALD);
		    wait.until(ExpectedConditions.attributeContains(bouton_ALD, "class", "ui-state-default"));
	}
	public void deactiverOptionNepasdelivrer() {
		Log.info("On clique sur l'option ALD pour deactiver ALD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_Nepasdelivrer));		
		if(wait.until(ExpectedConditions.attributeContains(bouton_Nepasdelivrer, "class", "ui-state-default"))) {
			Log.info("le button ALD est pas active");
		}else
			click(bouton_Nepasdelivrer);
		    wait.until(ExpectedConditions.attributeContains(bouton_Nepasdelivrer, "class", "ui-state-default"));
	}
	
	public void deactiverOptionSubstituable() {
		Log.info("On clique sur l'option bouton_Substituable pour deactiver NS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_Substituable));		
		click(bouton_Substituable);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Non substituable (MTE)']")));
		click(By.xpath("//li[text()='Non substituable (MTE)']"));
		//wait.until(ExpectedConditions.attributeContains(bouton_Substituable, "class", "ui-state-active"));
		Log.info("L'option bouton_Substituablea été deactivée");
	}
	public void modifierEtFermer() {
		Log.info("on clique sur modifier et prescrire");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("posologyEditForm:buttonPrescribeAndClose")));
		click(By.id("posologyEditForm:buttonPrescribeAndClose"));
		switchToDefault();
	}
	
	
	public void ajouterNombreDeRenouvellement(String nbreRenouvellement) {
		Log.info("On ajoute le nombre de Renouvellement : " + nbreRenouvellement);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_renouvellement));	
		js.executeScript("arguments[0].click();",driver.findElement(champ_renouvellement));
		driver.findElement(champ_renouvellement).sendKeys("\u0008");
		driver.findElement(champ_renouvellement).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),nbreRenouvellement);
		click(label_renouvellement);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.attributeContains(champ_renouvellement,"value",nbreRenouvellement));	
		Log.info("On a ajouté : " + nbreRenouvellement + "fois");
		
	}
	
	public void ajouterNombreDeBoite(String nombreDeBoite) {
		Log.info("On ajoute le nombre de boite : " + nombreDeBoite);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_nombre_boite));	
		js.executeScript("arguments[0].click();",driver.findElement(champ_nombre_boite));
		driver.findElement(champ_nombre_boite).sendKeys("\u0008");
		driver.findElement(champ_nombre_boite).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),nombreDeBoite);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",driver.findElement(label_renouvellement));
		//click(label_renouvellement);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.attributeContains(champ_nombre_boite,"value",nombreDeBoite));	
		Log.info("On a ajouté : " + nombreDeBoite + "fois");
	}
	
	public void choixFrequenceNombre(String frequence) {
		Log.info("On choisit la frequence : " + frequence);
		wait.until(ExpectedConditions.visibilityOfElementLocated(champ_frequence_de_prise));
		js.executeScript("arguments[0].click();",driver.findElement(champ_frequence_de_prise));
		wait(5000);
//		driver.findElement(champ_frequence_de_prise).sendKeys("\u0008");
//		driver.findElement(champ_frequence_de_prise).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),frequence);
		driver.findElement(champ_frequence_de_prise).sendKeys(Keys.CONTROL + "a");
		driver.findElement(champ_frequence_de_prise).sendKeys(Keys.DELETE);
		driver.findElement(champ_frequence_de_prise).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),frequence);
		WebElement element = driver.findElement(label_pendant);
		js.executeScript("arguments[0].click();",element);
		//click(label_pendant);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.attributeContains(champ_frequence_de_prise,"value",frequence));	
		Log.info("On a ajouté : " + frequence + "fois");
	}
	
	
	public void choixFrequenceNombre2(String frequence) {
		Log.info("on change le nombre de frequence 2");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,':frequency2Text')]")));
		clearInput(By.xpath("//input[contains(@id,':frequency2Text')]"));
		input(frequence,By.xpath("//input[contains(@id,':frequency2Text')]"));
		
		}
	
	
	public void choixPendant(String frequence,String duree) throws InterruptedException {
		Log.info("on ajoute duree pendant");
         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-pres-popup"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='posologyEditForm:during1']")));
		clearInput(By.xpath("//input[@id='posologyEditForm:during1']"));
		input(frequence,By.xpath("//input[@id='posologyEditForm:during1']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']",duree))));
		click(By.xpath(String.format("//span[text()='%s']",duree)));
		Thread.sleep(5000);
	
		}
	
	public void choixUniteMesureDeLaDose(String uniteMesureDose,String unitDose) {
		Log.info("On choisit l'unité de mesure de la dose : " + uniteMesureDose);
		click(select_unite_mesure_dose);
		Log.info("Le select de l'unité de mesure de la dose est ouverte");
		try {
			Thread.sleep(3000);
			//solution temporaire à virer apres si solution trouvée
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//js.executeScript(("arguments[0].click();"), driver.findElement(By.xpath(String.format("(//li[@data-label='%s'])[1]",uniteMesureDose))));
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("(//li[@data-label='%s'])[1]",uniteMesureDose))));
		click(By.xpath(String.format("//li[@id='posologyEditForm:productUnitId_%s']",uniteMesureDose)));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[id='posologyEditForm:productUnitId_items']")));
		Log.info("J'ai cliqué sur l'unité de mesure :" + uniteMesureDose);
		click(label_dose_unitaire);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		wait.until(ExpectedConditions.attributeToBe(By.cssSelector("[id='posologyEditForm:productUnitId_label']"),"innerText",unitDose));
		Log.info("L'unité de mesure de la dose " + uniteMesureDose + " a été bien selectionnée");
	}
	
	
	
	public void selectionnerUniteDeTempsFrequence(String frequenceNumber,String frequency) {
		Log.info("On choisit la frequence : " + frequenceNumber);
		click(select_frequence);
		Log.info("Le select s'est ouvert");
	
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("posologyEditForm:frequenceRange_items")));
		js.executeScript(("arguments[0].click();"), driver.findElement(By.xpath(String.format("//li[@id='posologyEditForm:frequenceRange_%s']",frequenceNumber))));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[id='posologyEditForm:frequenceRange_items']")));
		Log.info("J'ai cliqué sur :" + frequenceNumber);
		click(label_pendant);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	//	wait.until(ExpectedConditions.attributeToBe(By.cssSelector("[id='posologyEditForm:frequenceRange_label']"),"innerText",frequency));
		Log.info("La fréquence " + frequenceNumber + " a été bien selectionnée");
	}
	
	public void selectionnerUniteDeTempsFrequence2(String frequenceNumber,String frequency) {
		Log.info("On choisit la frequence : " + frequenceNumber);
		click(select_frequence);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("posologyEditForm:frequenceRange_items")));
		WebElement semaine=driver.findElement(By.xpath("//span[text()='semaine']"));
		semaine.click();
	
		Log.info("J'ai cliqué sur :" + frequenceNumber);
		click(label_pendant);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		Log.info("La fréquence " + frequenceNumber + " a été bien selectionnée");
	}
	
	private void wait(int temps) {
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void verifier_non_traduction_dc_pour_medicament() {
		Log.info("ce produit n'a pas de correspondance dc");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='alertMessage']")));
		
	}
	
	public void verifier_existence_reserve(String reserve) {
		Log.info(String.format("on verifie existence reserve %s",reserve));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='win_DC']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]",reserve))));
	}
	
	public void verifierExistenceAlertAllergie(String medicament) {
		Log.info(String.format("on verifie l'existence alert allergie liee au %s",medicament));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(button_alert_allergies));
		wait(3000);
		js.executeScript("arguments[0].click()",driver.findElement(button_alert_allergies));
		//click(button_alert);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[text()='ALLERGIE']/following::b[contains(text(),'%s')]",medicament))));
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//b[text()='ALLERGIE']/following::b[contains(text(),'%s')]",medicament))).isDisplayed());
	}
	
	public void verifierExistenceAlertContreIndication(String type,String medicament) {
		Log.info(String.format("on verifie l'existence alert allergie liee au %s",medicament));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(button_alert));
		wait(3000);
		click(button_alert);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament))));
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament))).isDisplayed());
	}
	
	
	public void verifierExistenceAlertContreIndication2(String type,String medicament) {
		Log.info(String.format("on verifie l'existence alert contradiction liee au %s",medicament));
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(button_alert));
		//js.executeScript("arguments[0].click()", driver.findElement(button_alert));
		wait(3000);
		click(button_alert);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::td[contains(text(),'%s')]",type,medicament))));
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//b[contains(text(),'%s')]/following::td[contains(text(),'%s')]",type,medicament))).isDisplayed());
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
	
	
	
	public void verifierExistenceNonAlertContreIndication(String type,String medicament) {
		Log.info(String.format("on verifie l'inexistence alert contradiction liee au %s",medicament));
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_alert));
		js.executeScript("arguments[0].click()", driver.findElement(button_alert));
		//click(button_alert);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::td[contains(text(),'%s')]",type,medicament))));
		List  alerts = driver.findElements(By.xpath(String.format("//b[contains(text(),'%s')]/following::td[contains(text(),'%s')]",type,medicament)));
		int alert_size = alerts.size();
		
		Assert.assertTrue(alert_size==0);


	
	}
	
	
	
	public void verifierExistenceAlertContreIndicationCinqFois(String type,String medicament) {
		Log.info(String.format("on verifie l'existence alert contre indications  liee au %s 5 fois",medicament));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(button_alert));
		wait(3000);
		click(button_alert);
	     List<WebElement> contre_indications = driver.findElements(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament)));
		//int size = contre_indications.size();
		//System.out.println("the size is:" + size);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament))));
	    Assert.assertTrue(contre_indications.size()==6);
	}
	
	
	public void verifierExistenceAlertPRECAUTIOND_EMPLOI(String type,String medicament) {
		Log.info(String.format("on verifie l'existence alert allergie liee au %s",medicament));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(button_alert));
		//click(button_alert);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament))));
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament))).isDisplayed());
	
	}
	
	
	public void verifierExistenceRedondanceThérapeutiqueAntiVitamineK(String type,String medicament1,String medicament2) {
	
		Log.info("on verifie l'existence alert Redondance Therapeutique");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	
		//wait.until(ExpectedConditions.visibilityOfElementLocated(button_redondance_alert));
	
		try {
	
			click(button_redondance_alert);
	
		}catch(StaleElementReferenceException e) {
		e.printStackTrace();
	 }
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament1,medicament2))));
	
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament1,medicament2))).isDisplayed());
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'tabOverdose')]//following::table/tbody/tr[4]/td")));
	
		String AntiVitamineK = driver.findElement(By.xpath("//div[contains(@id,'tabOverdose')]//following::table/tbody/tr[4]/td")).getText();
	 
		Log.info("On virifie l'existence de la Antivitamine K");
	
		Assert.assertTrue(AntiVitamineK.contains("Antivitamines K"));
	
	
	}
	
	public void verifierExistenceRedondanceThérapeutique(String type,String medicament1,String medicament2) {
		
		Log.info("on verifie l'existence alert Redondance Therapeutique");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_redondance_alert));
		
		Log.info("on clique button redondance alert");
		
		click(button_redondance_alert);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament1,medicament2))));
		
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//b[contains(text(),'%s')]/following::b[contains(text(),'%s')]/following::b[contains(text(),'%s')]",type,medicament1,medicament2))).isDisplayed());
		}
	
	public String recupereProdutComposants() {
		
		Log.info("on verifie l'existence alert Redondance Therapeutique");
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_redondance_alert));
	
		try {
	
			click(button_redondance_alert);
	
		}catch(StaleElementReferenceException e) {
		e.printStackTrace();
	 }
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'tabOverdose')]//following::table/tbody/tr[3]/td")));
	
		String produit  = driver.findElement(By.xpath("//div[contains(@id,'tabOverdose')]//following::table/tbody/tr[3]/td")).getText();
	    
		
		Log.info(produit);
		//Log.info(String.format("On virifie que le composant est present : %s , %s" ,medicament1,medicament2));
	    
		return produit;
		//Assert.assertTrue(produit.contains(medicament1));
		//Assert.assertTrue(produit.contains(medicament2));
	
	
	}
public String recupereProdutComposants2() {
		
		Log.info("on verifie l'existence alert Redondance Therapeutique");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'tabOverdose')]//following::table/tbody/tr[3]/td")));
	
		String produit  = driver.findElement(By.xpath("//div[contains(@id,'tabOverdose')]//following::table/tbody/tr[3]/td")).getText();
		
		Log.info(produit);
		//Log.info(String.format("On virifie que le composant est present : %s , %s" ,medicament1,medicament2));
	    
		return produit;
		
	
	
	}
	
	
	public void verifierInexistenceAlertInteractionMedicamenteuse() {
		Log.info("on verifie l'inexistence des interactions medicamenteuse");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'noAlert') and @id='alertPrescriptionForm:interactions_button']")));
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(@class,'noAlert') and @id='alertPrescriptionForm:interactions_button']")).isDisplayed());
	}
	
	public void verifierInexistenceAlertProcreer() {
		Log.info("on verifie l'inexistence alert procreer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'noAlert') and @id='alertPrescriptionForm:interactions_button']")));
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(@class,'noAlert') and @id='alertPrescriptionForm:contraindications_button']")).isDisplayed());
	}
	
	
	public void verifierExistenceAlertInteractionMedicamenteuse() {
		Log.info("on verifie l'existence alert des interactions medicamenteuse");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'highAlert') and @id='alertPrescriptionForm:interactions_button']")));
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(@class,'highAlert') and @id='alertPrescriptionForm:interactions_button']")).isDisplayed());
	}
	
	
	
	public void verifierExistenceIconDansLeresultatDeRecherche(String color) {
		Log.info("on verifie l'existence Icon Voiture ou autre dans le resultat de la recherche");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//i[@class='fa fa-car ui-icon %s' and contains(@title,'la conduite')]",color))));
		
		Assert.assertTrue(driver.findElement(By.xpath(String.format("//i[@class='fa fa-car ui-icon %s' and contains(@title,'la conduite')]",color))).isDisplayed());
	}
	
	
	
	public void verifierPosologieContientLettresEtChiffres(String lettre_chiffre) {
		Log.info("on verifie l'existence de la posolgie en lettre et chiffre");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//h2[contains(text(),'%s')]",lettre_chiffre))));
		
	}
	
	public void verifierExistenceIconDopantDansLeresultatDeRecherche() {
		Log.info("on verifie l'existence Icon Voiture ou autre dans le resultat de la recherche");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Dopant']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@title='Dopant']")).isDisplayed());
	}
	
	public void verifierInExistenceIconDopantDansLeresultatDeRecherche() {
		Log.info("on verifie l'inexistence Icon Voiture ou autre dans le resultat de la recherche");
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Dopant']")));
		List<WebElement> Dopants =driver.findElements(By.xpath("//img[@title='Dopant']"));
		if(Dopants.size()>0) {
			Log.info("element exists");
		}else {
			Log.info("elemet doesn't exist");
		}

	}
	
	public void verifierExistenceIconRestrictionProduitDansLeresultatDeRecherche() {
		Log.info("on verifie l'existence Icon restriction de produit dans le resultat de la recherche");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'general-lock-closed') and contains(@title,'Restrictions du produit')]")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//i[contains(@class,'general-lock-closed') and contains(@title,'Restrictions du produit')]")).isDisplayed());
	}
	
	
	public void verifierExistenceIconTPDansLeresultatDeRecherche() {
		Log.info("on verifie l'existence Icon TP ou autre dans le resultat de la recherche");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Produit éligible à une prise en charge RC']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@title='Produit éligible à une prise en charge RC']")).isDisplayed());
	}
	
	
	public void verifierExistenceIconLogoHDansLeresultatDeRecherche() {
		Log.info("on verifie l'existence Icon Logo H ou autre dans le resultat de la recherche");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Hospitalier']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//i[@title='Hospitalier']")).isDisplayed());
}
	
	public String recupereLtextAlertContrôlePosologieDuréeMaximale() {
		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-confirm-dialog-message']//table/tbody/tr[3]/td")));

		WebElement Alert = driver.findElement(By.xpath("//span[@class='ui-confirm-dialog-message']//table/tbody/tr[3]/td"));
	
		String AlertContrôlePosologieDuréeMaximale  = Alert.getText();
	
	
		return AlertContrôlePosologieDuréeMaximale;
	}
	
	
	public String recuperAlertQantiteMaximale() {
		Log.info(String.format("on verifie l'existence alert quantite maximale recommendee"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_info));
		js.executeScript("arguments[0].click()",driver.findElement(button_info));
		//click(button_info);
		
        //wait.until(ExpectedConditions.visibilityOfElementLocated(button_controle_posolgie));
		wait(3000);
		click(button_controle_posolgie);
		
		WebElement Alert = driver.findElement(By.xpath("//div[@id='sideBarPanel:sideBarAlerts:tabControlPosoly']//table/tbody/tr[3]/td"));
		
		String AlertQauntiteMaximale  = Alert.getText();
		
		return AlertQauntiteMaximale;
}
	
	public String recuperAlertQantiteMaximaleJournaliereJaune() {
		Log.info(String.format("on verifie l'existence alert quantite maximale recommendee"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_jaune));
		
		click(button_jaune);
		
       wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(button_controle_posolgie));
	
		click(button_controle_posolgie);
		
		
		WebElement Alert = driver.findElement(By.xpath("//div[@id='sideBarPanel:sideBarAlerts:tabControlPosoly']//table/tbody/tr[3]/td"));
		
		String AlertQauntiteMaximaleJournaliere  = Alert.getText();
		
		return AlertQauntiteMaximaleJournaliere;
}
	
	
	public String recuperAlertQantiteMaximaleJournaliereRouge() {
		Log.info(String.format("on verifie l'existence alert quantite maximale recommendee"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(button_rouge));
		
		click(button_rouge);
		
       wait.until(ExpectedConditions.visibilityOfElementLocated(button_controle_posolgie));
		
		click(button_controle_posolgie);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement Alert = driver.findElement(By.xpath("//div[@id='sideBarPanel:sideBarAlerts:tabControlPosoly']//table/tbody/tr[3]/td"));
		String AlertQauntiteMaximaleJournaliere  = Alert.getText();
		
		return AlertQauntiteMaximaleJournaliere;
}
	
	
	
	public void verifierAlertQantiteMaximaleJournaliere(String alert) {
		Log.info(String.format("on verifie l'existence alert quantite maximale recommendee"));

		
		Log.info(String.format("on verifie l'existence alert quantite maximale recommendee"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(button_jaune));
		wait(3000);
		//click(button_jaune);
		js.executeScript("arguments[0].click()",driver.findElement(button_jaune));
			wait(3000);
		    click(button_controle_posolgie);
		    js.executeScript("arguments[0].click()",driver.findElement(button_jaune));
		
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@id,'tabControlPosoly')]//td[contains(text(),'%s')]", alert))));

	}

	
	
	
	
	public void verifierExistenceAlertIPC() {
		Log.info("on verifie L'existence Alert Incompatibilite physco-chimique");
		wait.until(ExpectedConditions.visibilityOfElementLocated(alert_IPC));
		Assert.assertTrue(elementIsPresentOnDOM(alert_IPC));
	}
	
	public void verifierExistenceAlertRedondance() {
		Log.info("on verifie L'existence Alert redondances therapeutiques");
		wait.until(ExpectedConditions.visibilityOfElementLocated(alert_redondance));
		Assert.assertTrue(elementIsPresentOnDOM(alert_redondance));
	}
	
	public void verifierResultatApresConvertirEnDC(String medicament,String medicament1) {
		Log.info("on verifie resultat apres convertir medicament en DC");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'%s')]", medicament))));
		Log.info("on selectionne le medicament En DC");
		click(By.xpath(String.format("//div[contains(text(),'%s')]", medicament)));
		click(By.xpath("//button[text()='Sélectionner DC']"));
		Log.info("on verifie que Le risque de redondance avec la DC doit être maintenu (icône toujours affichée)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),'%s')]//preceding::button[@title='Annuler DC']",medicament1))));		
	}
	
//	public void verifierExistenceProduitATC(produitNom) {
//		Log.info(String.format("on verifie existence %s",produitNom));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",produitNom))));
//		click(By.xpath(String.format("//td[text()='%s']",produitNom)));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(tableau_liste_produit));
//		Log.info("Les resultats de la recherche sont bien affichés");
//	}
	public void verifierExistenceProduit(String produitNom) {
		Log.info(String.format("on verifie existence %s",produitNom));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",produitNom))));
		Log.info(String.format("le %s existe dans la liste",produitNom));
	}
	
	public void verifierExistenceProduit2(String produit) {
		Log.info(String.format("on verifie existence %s",produit));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[contains(text(),'%s')]",produit))));
		Log.info(String.format("le %s existe dans la liste",produit));
	}
	
	
	public void verifierExistenceProduitATC(String produitNom) {
		Log.info(String.format("on verifie existence %s",produitNom));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",produitNom))));
		Log.info(String.format("le %s existe dans la liste",produitNom));
	}
	
	
	public void verifierExistenceProduitDC(String produitNom) {
		Log.info(String.format("on verifie existence %s",produitNom));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",produitNom))));
		Log.info(String.format("le %s existe dans la liste",produitNom));
	}
	public void verifierExistenceProduitPrincActif(String produitNom) {
		Log.info(String.format("on verifie existence %s",produitNom));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",produitNom))));
		Log.info(String.format("le %s existe dans la liste",produitNom));
	}
	
	public void verifierExistenceProduitIndications(String produitNom) {
		Log.info(String.format("on verifie existence %s",produitNom));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",produitNom))));
		Log.info(String.format("le %s existe dans la liste",produitNom));
	}
	
	
	public void verifierAssociationContreIndication(String association,String medicament1,String medicament2) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='alertPrescriptionForm:interactions_button']")));
		Log.info("on clique button indications");
		wait(3000);
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@id='alertPrescriptionForm:interactions_button']")));
		//click(By.xpath("//button[@id='alertPrescriptionForm:interactions_button']"));
		Log.info(String.format("on verifie l'association entre %s , %s ",medicament1,medicament2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//b[contains(text(),'%s')]//following::b[contains(text(),'%s')]//following::b[contains(text(),'%s')]", association,medicament1,medicament2))));
	
	}
	public void verifier_apparition_recommendations_patient_prescripteur() {
		Log.info("on clique button info");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='alertPrescriptionForm:info_button']")));
		js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//button[@id='alertPrescriptionForm:info_button']")));
	 //  click(By.xpath("//button[@id='alertPrescriptionForm:info_button']"));
	   Log.info("on clique button recommendations" );
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Recommandations']")));
	   click(By.xpath("//a[text()='Recommandations']"));
	   Log.info("on verifie existence recommendation patient et prescripteur");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'tabRecommendations')]/iframe")));
	   driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@id,'tabRecommendations')]/iframe")));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//font[text()='SURVEILLANCE du traitement :']")));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'ARRETER LE TRAITEMENT ET CONSULTER IMMEDIATEMENT LE MEDECIN ')]")));
	
	}
	
	public void verifier_posologie_en_premier_dans_la_list(String psologie) {
		WebElement list_posologie = driver.findElement(By.xpath("//div[@id='posologySelectForm:listPosology']/div[2]//table/tbody/tr[1]/td[1]"));
		String text_posologie = list_posologie.getText();
		wait.until(ExpectedConditions.visibilityOf(list_posologie));
		Assert.assertTrue(text_posologie.contains(psologie));
	}
public void verifier_absence_posologie_enfant(String info_enfant,String maladie_enfant) {
		
		Log.info(String.format("on verifier l'absence de posologie enfant %s %s",info_enfant,maladie_enfant));
		List<WebElement> posologie_enfant = driver.findElements(By.xpath(String.format("//td[contains(text(),'%s')]//following::span[contains(text(),'%s')]",info_enfant,maladie_enfant )));
		int posologie_non_existant = posologie_enfant.size();
		Assert.assertTrue(posologie_non_existant==0);

	}
	
public void verifier_absence_posologie_enfant(String info_enfant) {
		
		Log.info(String.format("on verifier l'absence de posologie enfant %s",info_enfant));
		List<WebElement> posologie_enfant = driver.findElements(By.xpath(String.format("//td[contains(text(),'%s')]",info_enfant)));
		int posologie_non_existant = posologie_enfant.size();
		Assert.assertTrue(posologie_non_existant==0);

	}
	
	public void ouvrir_list_documents_medicament(String medicament) {
		Log.info("on ouvre la liste de documents officiels ");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]/following::a[contains(@id,'listProducts:0:showDocumentListButton')]",medicament))));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-pres-popup"));
		click(By.xpath(String.format("//span[contains(text(),'%s')]/following::a[@title='Documents officiels']",medicament)));
	}
	
	public void ouvrir_list_documents_medicament2(String medicament) {
		Log.info("on ouvre la liste de documents officiels ");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]/following::a[contains(@id,'listProducts:0:showDocumentListButton')]",medicament))));
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframe-pres-popup"));
		click(By.xpath(String.format("//span[contains(text(),'%s')]/following::a[@title='Documents officiels']",medicament)));
	}
	
	public void ouvrir_liste_de_documents_et_verifier_existence_documents(String document1,String document2) {
		
		Log.info("verifier l' existence des  documents ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document1))));
		Log.info(String.format("document %s existe",document1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document2))));
		Log.info(String.format("document %s existe",document2));
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document3))));
		//Log.info(String.format("document %s existe",document3));
	}
	
	public void ouvrir_liste_de_documents_et_verifier_existence_documents(String document1,String document2,String document3,String document4) {
		Log.info("verifier l' existence des  documents ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document1))));
		Log.info(String.format("document %s existe",document1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document2))));
		Log.info(String.format("document %s existe",document2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document3))));
		Log.info(String.format("document %s existe",document3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//td[text()='%s']",document4))));
		Log.info(String.format("document %s existe",document4));
	}
	public void ouvrir_document_pdf_et_verifier_les_informations(String document,String url) {
		Log.info(String.format("on clique l'icone PDF pour ouvrir  le document %s existe",document));
		click(By.xpath(String.format("//td[text()='%s']/preceding::i[contains(@class,'pdf')]",document)));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		String window_pdf = windows.get(2);
		
		driver.switchTo().window(window_pdf);
		String url_current = driver.getCurrentUrl();
		Assert.assertTrue(url_current.contains(url));
		////td[text()='ANSM']/preceding::td[contains(text(),'vague de chaleur')]/preceding::i[contains(@class,'pdf')]
		
	}
	
	public void ouvrir_document_pdf_et_verifier_les_informations2(String document,String url) {
		Log.info(String.format("on clique l'icone PDF pour ouvrir  le document %s existe",document));
		click(By.xpath(String.format("//td[text()='%s']/preceding::i[contains(@class,'pdf')]",document)));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		String window_pdf = windows.get(1);
		
		driver.switchTo().window(window_pdf);
		String url_current = driver.getCurrentUrl();
		Assert.assertTrue(url_current.contains(url));
		////td[text()='ANSM']/preceding::td[contains(text(),'vague de chaleur')]/preceding::i[contains(@class,'pdf')]
		
	}
	
	public void valider_alerte_SAM() {
		//List<WebElement> alerte_sam = driver.findElements(By.xpath("//div[@id='productSearchForm:samAlertDialog']"));
		List<WebElement> alerte_sam = driver.findElements(By.xpath("//button[contains(@id,'btn_go_edit_prescription')]"));
		if(alerte_sam.size()>0) {
			Log.info("on valide Alerte SAM");
			click(By.xpath("//button[contains(@id,'btn_go_edit_prescription')]"));
		}else {
			Log.info("y a pas Alerte SAM");
		}
	}

	}
	

