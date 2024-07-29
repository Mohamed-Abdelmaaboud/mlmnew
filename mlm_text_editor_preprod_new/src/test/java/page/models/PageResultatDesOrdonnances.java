package page.models;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import clm.interop.Log;

public class PageResultatDesOrdonnances extends BasePage{
	
	String urlPageResultatDesOrdonnances = "https://mlmpreprod.monlogicielmedical.com/jenomi/formularioResultsLab.htm";
	By page_resultat_des_ordonnances = By.id("cuerpo");
	By bouton_plus_prelevement= By.xpath("//img[@id='searchAddExamId']");
	By bouton_supprimer = By.cssSelector("[title='Supprimer']");
	By logoMLM = By.id("img_logo");
	By bouton_oui_suppression = By.id("dialogButtonId_1");
	By search_exam_champs =By.xpath("//input[@id='textSearchExams']");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public PageResultatDesOrdonnances() {
	Log.info("On affiche la PageResultatDesOrdonnances");
	wait.until(ExpectedConditions.urlContains(urlPageResultatDesOrdonnances));
	wait.until(ExpectedConditions.visibilityOfElementLocated(page_resultat_des_ordonnances));
	Log.info("la PageResultatDesOrdonnances est affiché");
   }
	
	public void supprimerToutesLesOrdonnances() {
	Log.info("On va supprimer toutes les ordonnances");
	wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_plus_prelevement));
	click(bouton_plus_prelevement);
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	List<WebElement> supprimer = driver.findElements(bouton_supprimer);
	if (supprimer.size()>0) {
		for (int i = 0; i < supprimer.size(); i++) {
				
				supprimer.get(i).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_oui_suppression));
				click(bouton_oui_suppression);
			}
			wait.until(ExpectedConditions.invisibilityOfElementLocated(bouton_supprimer));
			Log.info("Toutes les ordonnances ont été supprimées");
	} 
	else {
		Log.info("Nous avons pas d'ordonnance à supprimer,on retourne à l'accueil");
		
	}
	wait.until(ExpectedConditions.visibilityOfElementLocated(logoMLM));
	click(logoMLM);
	}
	
	public void retourAccueil() {
		Log.info("Nous retournons à l'accueil apres avoir avoir intégré les resultats.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(logoMLM));
		click(logoMLM);
		Log.info("Nous avons bien quitté la page intégration des resultats");
	}
	
	
	
	public void creer_nouveau_prelevement() {
		Log.info("on cree un nouveau prelevement");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouton_plus_prelevement));
		click(bouton_plus_prelevement);
		Log.info("on click ok pour choisir la default date de jour");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		click(By.xpath("//button[text()='OK']"));
		Log.info("un nouveau prelevement a ete cree");
		
	}
	
	public void recherche_un_examen(String examen) {
		Log.info(String.format("on recherche un examen %s",examen));
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_exam_champs));
		clearInput(search_exam_champs);
		input(examen,search_exam_champs);
		Log.info("on clique sur boutton rechercher");
		click(By.xpath("//table[@id='buttonSearch']//button"));
		
		
	}
	public void choisir_examen(String examen) {
		Log.info("on choisit examen");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",examen))));
	    click(By.xpath(String.format("//span[contains(text(),'%s')]",examen)));
		
	}
	public void choisir_examen2(String examen) {
		Log.info("on choisit examen");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[contains(text(),'%s')]",examen))));
	    click(By.xpath(String.format("//span[text()='%s']",examen)));
		
	}
	
	public void recherche_un_examen2(String examen) {
		Log.info(String.format("on recherche un examen %s",examen));
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_exam_champs));
		input(examen,search_exam_champs);
		Log.info("on clique sur boutton rechercher");
		click(By.xpath("//table[@id='buttonSearch']//button"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@class,'elbow-plus')]")));
		//click(By.xpath("//img[contains(@class,'elbow-plus')]"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']",examen))));
	    //click(By.xpath(String.format("//span[text()='%s']",examen)));
		
	}
	
	
	 public void saisie_valeur_en_UI(String valeur,String valeur_min,String valeur_max,String examen) {
		 Log.info("on clique le button UU");
		 click(By.xpath("//table[@id='buttonUnitsUsual']//button"));
		 click(By.xpath("//table[@id='buttonUnitsInter']//button"));
		 Log.info("on clique le champs valeur pour saisir la valeur en UI");
	    //input(valeur,By.xpath(String.format("//input[@id='editorUU']")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))));
		wait(3000);
		//click(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen)));
		js.executeScript("arguments[0].click()",driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))));
		//click(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen)));
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//input[@id='ext-comp-1028']"))));
		wait(3000);
		 input(valeur,By.xpath("//input[@id='ext-comp-1028']"));
		 click(By.xpath(String.format("//div[contains(text(),'%s')]",examen)));
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'rankLower')]"))));
         clearInput(By.xpath("//input[contains(@id,'rankLower')]"));
         input(valeur_min, By.xpath("//input[contains(@id,'rankLower')]"));
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'rankTop')]"))));
	     clearInput(By.xpath("//input[contains(@id,'rankTop')]"));
	     input(valeur_max, By.xpath("//input[contains(@id,'rankTop')]"));

	 }
	 
	 public void saisie_valeur_en_UI(String valeur,String examen) {
		 Log.info("on clique le button UU");
		 //click(By.xpath("//table[@id='buttonUnitsUsual']//button"));
		 click(By.xpath("//table[@id='buttonUnitsInter']//button"));
		 Log.info("on clique le champs valeur pour saisir la valeur en UI");
	    //input(valeur,By.xpath(String.format("//input[@id='editorUU']")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))));
		wait(3000);
		//click(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen)));
		js.executeScript("arguments[0].click()",driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))));
		//click(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen)));
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//input[@id='ext-comp-1028']"))));
		wait(3000);
		 input(valeur,By.xpath("//input[@id='ext-comp-1028']"));
//		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'rankLower')]"))));
//         clearInput(By.xpath("//input[contains(@id,'rankLower')]"));
//         input(valeur_min, By.xpath("//input[contains(@id,'rankLower')]"));
//		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'rankTop')]"))));
//	     clearInput(By.xpath("//input[contains(@id,'rankTop')]"));
//	     input(valeur_max, By.xpath("//input[contains(@id,'rankTop')]"));

	 }
	 
	 public void saisie_valeur_en_UU(String valeur,String valeur_min,String valeur_max,String examen) {
		 Log.info("on clique le button UU");
		 click(By.xpath("//table[@id='buttonUnitsUsual']//button"));
		 //click(By.xpath("//table[@id='buttonUnitsInter']//button"));
		 Log.info("on clique le champs valeur pour saisir la valeur en UU");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen))));
		js.executeScript("arguments[0].click()",driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen))));
		// click(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen)));
		   wait(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//input[@id='editorUU']"))));
		input(valeur,By.xpath(String.format("//input[@id='editorUU']")));
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'rankLower')]"))));
        clearInput(By.xpath("//input[contains(@id,'rankLower')]"));
         input(valeur_min, By.xpath("//input[contains(@id,'rankLower')]"));
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'rankTop')]"))));
	     clearInput(By.xpath("//input[contains(@id,'rankTop')]"));
	     input(valeur_max, By.xpath("//input[contains(@id,'rankTop')]"));
	     wait(3000);

	 }
	 public void verifier_valeurs_en_UU(String examen) {
		  Log.info("on clique le button UU");
		  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='buttonUnitsUsual']//button")));
		  click(By.xpath("//table[@id='buttonUnitsUsual']//button"));
		 //  wait(3000);
		 //click(By.xpath("//div[contains(@class,'row')]//div[contains(text(),'Hémoglobine (Hb)')]/following::div[1]"));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen))));
		//  String valeur_en_UU =driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen))).getText();
	     // Log.info("le valeur UU est : " + valeur_en_UU);
		 // Assert.assertEquals(valeur_en_UU,valeur_uu,"les valeurs ne sont pas identiques");
	 }
	 
	 public void verifier_valeurs_en_UU2(String valeur_uu,String examen) {
		  Log.info("on clique le button UU");
		  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='buttonUnitsUsual']//button")));
		  click(By.xpath("//table[@id='buttonUnitsUsual']//button"));
		 //  wait(3000);
		 //click(By.xpath("//div[contains(@class,'row')]//div[contains(text(),'Hémoglobine (Hb)')]/following::div[1]"));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen))));
		  String valeur_en_UU =driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[2]",examen))).getText();
	      Log.info("le valeur UU est : " + valeur_en_UU);
		  Assert.assertEquals(valeur_en_UU,valeur_uu,"les valeurs ne sont pas identiques");
	 }
	 
	 
	 
	 public void verifier_valeurs_en_UI(String valeur_ui,String examen) {
		  Log.info("on clique le button UU");
		  wait(3000);
		  click(By.xpath("//table[@id='buttonUnitsInter']//button"));
		 //wait(3000);
		 //click(By.xpath("//div[contains(@class,'row')]//div[contains(text(),'Hémoglobine (Hb)')]/following::div[1]"));
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))));
		//  String valeur_en_UI =driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))).getText();
	     // Log.info("le valeur UU est : " + valeur_en_UI);
		 // Assert.assertEquals(valeur_en_UI,valeur_ui,"les valeurs ne sont pas identiques");
	 }
	 
	 
	 
	 public void verifier_valeurs_en_UI2(String valeur_ui,String examen) {
		  Log.info("on clique le button UU");
		  wait(3000);
		  click(By.xpath("//table[@id='buttonUnitsInter']//button"));
		 //wait(3000);
		 //click(By.xpath("//div[contains(@class,'row')]//div[contains(text(),'Hémoglobine (Hb)')]/following::div[1]"));
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))));
		  String valeur_en_UI =driver.findElement(By.xpath(String.format("//div[contains(@class,'row')]//div[contains(text(),'%s')]/following::div[1]",examen))).getText();
	      Log.info("le valeur UU est : " + valeur_en_UI);
		  Assert.assertEquals(valeur_en_UI,valeur_ui,"les valeurs ne sont pas identiques");
	 }
	 
	 
	 
	 public void supprimer_recherche(String examen) {
		 Log.info("on supprime l'existante recherche et recherche un nouveau examens");
		 clearInput(search_exam_champs);
	 }
	 
	 
	 public void imprimer_les_examens_en_editeur_texte() {
		 Log.info("on imprime le resume en editeur de texte");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Imprimer']")));
		 click(By.xpath("//button[text()='Imprimer']"));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Imprimer le résumé']")));
		 click(By.xpath("//span[text()='Imprimer le résumé']"));
		 wait(15000);
		 
	 }
	 
		public void configurerOrdonnance() throws InterruptedException {
			Log.info("on clique sur configuration ordonnances pour ouvrir fenetres gestion des ordonnances");
			click(By.xpath("//span[text()='Résultats']/preceding::div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("win_configReports")));
		    Log.info("on clique sur configuration de l'ordonnance");
		    Thread.sleep(3000);
		    js.executeScript("arguments[0].click()",driver.findElement(By.xpath("//span[contains(text(),'Configuration')]/preceding::div[1]")));
		    Thread.sleep(3000);
		    //	click(By.xpath("//span[contains(text(),'Configuration')]/preceding::div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkPrintDuplicate")));
		   if(driver.findElement(By.id("checkPrintDuplicate")).isSelected()) {
			    Log.info("on deselectionne dupliquer");
				click(By.id("checkPrintDuplicate"));
		   }else {
			   Log.info("dupliquer est deselectionner");
		  }
		
		
		click(By.xpath("//button[text()='OK']"));
		}
		
	 
	 public void switchTowindow(int window) {
			List <String> windows = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windows.get(window));
		}
	 
	 private void wait(int seconds) {
	
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	
	
	
	
	

