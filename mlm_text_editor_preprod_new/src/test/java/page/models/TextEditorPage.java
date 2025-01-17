package page.models;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import clm.interop.Log;

public class TextEditorPage extends BasePage{

	LocalDateTime date_du_jour = LocalDateTime.now();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String date = formatter.format(date_du_jour);
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:00");
	String date2 = formatter.format(date_du_jour);
	
	JavascriptExecutor scroll = (JavascriptExecutor) driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
	
	public void creer_un_modele(String categorie,String type_document,String modele_num) {

		
//		byte[] array = new byte[3]; // length is bounded by 3
//		Random random = new Random();
//		random.nextBytes(array);
//	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    String modele =  "Modèle2" + date2 + modele_num;
	   
		Log.info("on choisi un titre pour le modele");
		 wait_avec_sleep(7000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Choisir un titre pour le modèle']")));
	    input(modele,By.xpath("//input[@title='Choisir un titre pour le modèle']"));
	    Log.info("on choisi categorie");
	    click(By.xpath("//div[text()='Sélectionner une catégorie']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",categorie))));
	    click(By.xpath(String.format("//div[text()='%s']",categorie)));
	    click(By.xpath("//div[text()='Sélectionner un type de document']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",type_document))));
	    click(By.xpath(String.format("//div[text()='%s']",type_document)));

	 
	}
	
	public void creer_un_modele_avec_entete(String categorie,String type_document,String entete_num) {
		 
		 Log.info("on switch au deuxieme window pour creer le modele");
		 String entete =  "Entête1" + date + entete_num;
		 wait_avec_sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Choisir un titre pour le modèle']")));
		 wait_avec_sleep(7000);
		input(entete,By.xpath("//input[@title='Choisir un titre pour le modèle']"));
	    Log.info("on choisi categorie");
	    click(By.xpath("//div[text()='Sélectionner une catégorie']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",categorie))));
	    click(By.xpath(String.format("//div[text()='%s']",categorie)));
	    click(By.xpath("//div[text()='Sélectionner un type de document']"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",type_document))));
	    click(By.xpath(String.format("//div[text()='%s']",type_document)));
	    //Log.info("on clique sur button signets");
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Signets')]")));
//	    click(By.xpath("//div[contains(text(),'Signets')]"));
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-reportField")));
//	    input("Prénom",By.id("search-reportField"));
//	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Données du patient')]//following::td[text()='Prénom']")));
//	  click(By.xpath("//div[contains(text(),'Données du patient')]//following::td[text()='Prénom']"));
//	   Log.info("on ferme inserer informations fenetre");
//	   click(By.xpath("//button[@aria-label='Close']"));
	 
	}
	
	
	
	public void ajout_signets(String info_signet) {
		 
		 Log.info("on switch au deuxieme window pour creer le modele");
		Log.info("on choisi un signet pour le modele");
		 wait_avec_sleep(5000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Signets')]")));
	    click(By.xpath("//div[contains(text(),'Signets')]"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-reportField")));
	    clearInput(By.id("search-reportField"));
	    input(info_signet,By.id("search-reportField"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[contains(text(),'Données du patient')]//following::td[text()='%s']",info_signet))));
		click(By.xpath(String.format("//div[contains(text(),'Données du patient')]//following::td[text()='%s']",info_signet)));
		 Log.info("on ferme inserer informations fenetre");
		 click(By.xpath("//button[@aria-label='Close']"));
		 wait_avec_sleep(5000);
	}
	
	public void inserer_image_dans_textediteur(String chemin_image) throws AWTException {
		 //driver.switchTo().frame("myTextControlContainer_txframe");
		Log.info("on insere des images dans pdf");
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myTextControlContainer_txframe"));
		 wait_avec_sleep(5000);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Insérer']")));
		 Log.info("on clique sur inserer");
		 click(By.xpath("//div[text()='Insérer']"));
		wait_avec_sleep(3000);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ribbonTabInsert_drpDnBtnInsertImage")));
		 Log.info("on clique sur image");
		click(By.id("ribbonTabInsert_drpDnBtnInsertImage"));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ribbonTabInsert_mnuItemInsertImage")));
		wait_avec_sleep(3000);
		Log.info("on clique sur images");
		click(By.id("ribbonTabInsert_mnuItemInsertImage"));
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ribbonTabInsert_mnuInsertImage_Source_Local")));
		wait_avec_sleep(3000);
		Log.info("on clique sur Telecharger");
		click(By.id("ribbonTabInsert_mnuInsertImage_Source_Local"));
		
		
		StringSelection sel = new StringSelection(chemin_image);
		   // Copy to clipboard
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
		 System.out.println("selection" +sel);
		
		 // Create object of Robot class
		 Robot robot = new Robot();
	      wait_avec_sleep(1000);   
		  // Press Enter
		 robot.keyPress(KeyEvent.VK_ENTER);

		// Release Enter
		 robot.keyRelease(KeyEvent.VK_ENTER);
		  // Press CTRL+V
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);

		// Release CTRL+V
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 wait_avec_sleep(1000);
		        
		   // Press Enter 
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 
		 wait_avec_sleep(10000);
		 switchToDefault();
	
	}
	
	public void saisie_des_informations_dans_le_textediteur(String text) {
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myTextControlContainer_txframe"));
		wait_avec_sleep(5000);
		js.executeScript("arguments[0].click()",driver.findElement(By.id("txHiddenTextField_textView")));
		wait_avec_sleep(5000);
	
		driver.findElement(By.id("txHiddenTextField_textView")).sendKeys(Keys.chord(Keys.END));

		Actions actions=new Actions(driver);
		actions.sendKeys(text).perform();
		wait_avec_sleep(5000);

		
//		JavascriptExecutor js =  (JavascriptExecutor)driver;
//	    String t=js.executeScript("return document.getElementById('txHiddenTextField_textView').value").toString();
//		Log.info(t);
	    switchToDefault();

		
		
		
		
		
	//	input(text,By.id("txHiddenTextField_textView"));
		
	}
	
	public void verifierContenuDansLeTextEditor(String contenu) {
		Log.info("on verifie l'existence de text renseignes dans le text editor");
		String textEditorContenu = recupereTextDeTextEditor();
		Assert.assertTrue(textEditorContenu.contains(contenu));
		Log.info("le contenu renseigne existe dans le text editor");
		switchToDefault();
	}
	public String recupereTextDeTextEditor() {
		Log.info("on recupere les textes du text editor");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myTextControlContainer_txframe"));
		wait_avec_sleep(5000);
	    WebElement element = driver.findElement(By.id("txHiddenTextField_textView"));
	    String text = ((JavascriptExecutor)driver).executeScript("return arguments[0].textContent;", element).toString();
		wait_avec_sleep(5000);
	    Log.info(text);
		return text;
		//.lastChild.
	}
	
	public void verifierContenuDansLeTextEditor2(String contenu) {
		Log.info("on verifie l'existence de text renseignes dans le text editor");
		String textEditorContenu = recupereTextDeTextEditor2();
		Assert.assertTrue(textEditorContenu.contains(contenu));
		Log.info("le contenu renseigne existe dans le text editor");
	}
	public String recupereTextDeTextEditor2() {
		Log.info("on recupere les textes du text editor");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myTextControlContainer_txframe"));
		wait_avec_sleep(5000);
		WebElement element = driver.findElement(By.id("txHiddenTextField_textView"));
	    wait_avec_sleep(5000);
	    String text = ((JavascriptExecutor)driver).executeScript("return arguments[0].textContent;", element).toString();
	    Log.info(text);
		return text;
	}
	
public void readEditorContent(String url) throws IOException {
//	//Creating a HttpClient object
//    CloseableHttpClient httpclient = HttpClients.createDefault();
//    //Creating a HttpGet object
//    HttpGet httpget = new HttpGet(url);
//    //Executing the Get request
//    CloseableHttpResponse httpresponse = httpclient.execute(httpget);
//    Scanner sc = new Scanner(httpresponse.getEntity().getContent());
//    //Instantiating the StringBuffer class to hold the result
//    StringBuffer sb = new StringBuffer();
//    while(sc.hasNext()) {
//       sb.append(sc.next());
//       //System.out.println(sc.next());
//    }
//    //Retrieving the String from the String Buffer object
//    String result = sb.toString();
//    System.out.println(result);
//    //Removing the HTML tags
//    result = result.replaceAll("<[^>]*>", "");
//    System.out.println("Contents of the web page: "+result);
// }
		
		URL pdfUrl = new URL(url);
	InputStream in = pdfUrl.openStream();
		 Scanner sc = new Scanner(in);
	      StringBuffer sb = new StringBuffer();
while(sc.hasNext()) {
    sb.append(sc.next());
    //System.out.println(sc.next());
 }
      //Retrieving the String from the String Buffer object
	      String result = sb.toString();
	      System.out.println(result);
	      //Removing the HTML tags
	      result = result.replaceAll("<[^>]*>", "");
	      Log.info("Contents of the web page: "+ result);
		//BufferedInputStream bf = new BufferedInputStream(in);
		 
		//PDDocument doc = PDDocument.load(bf,MemoryUsageSetting.setupTempFileOnly());
		//String content = new PDFTextStripper().getText(doc);
		//doc.close();

	//return result;
	}
	
	
	
	public void scroll() {
		wait_avec_sleep(5000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myTextControlContainer_txframe"));
		  Actions act = new Actions(driver);
          act.sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
          wait_avec_sleep(3000);
        
//		 WebElement element = driver.findElement(By.xpath("//form[@id='txNovalidateForm']/following::div[2]"));
//		 js.executeScript("arguments[0].click()",driver.findElement(By.id("txHiddenTextField_textView")));
//		   Log.info("on scroll");
//		 scroll(element);
//		    wait_avec_sleep(5000);
	}
	
	public void valider_la_creation_du_model_avec_oui() {
		Log.info("on valide la creation du model avec oui");
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Valider']")));
		   click(By.xpath("//div[text()='Valider']"));
		  Log.info("on choisi de rester sur l'editeur de texte en tapant sur oui");
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Oui']")));
		   click(By.xpath("//span[text()='Oui']"));
		   wait_avec_sleep(3000);
		
	}
	
	public void valider_la_creation_du_model_avec_Non() {
		Log.info("on valide la creation du model avec non");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Valider']")));
		click(By.xpath("//div[text()='Valider']"));
		Log.info("on choisi de aller sur l'Onglet MLM en tapant sur non");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Non']")));
		click(By.xpath("//span[text()='Non']"));	
		
	}
	
	public void verifier_que_les_champs_signets_sont_rempli_automatiquement(String text) {
		Log.info("on verifie que les champs de resignets sont remplis automatiquement");
		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		wait_avec_sleep(3000);
		try {
			String pdfContent = readPdfContent(driver.getCurrentUrl());
			Assert.assertTrue(pdfContent.contains(text));
			Log.info("les textes ajoutes existent dans le pdf" + pdfContent);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();		}
		
		Log.info("les textes ajoutes existent dans le pdf" + text);
	}

	public void verifier_contenu_de_l_ordonnace(String text) {
		Log.info("on verifie contenu de l'ordonnance");
		wait_avec_sleep(5000);
		try {
			
			String pdfContent = readPdfContent(driver.getCurrentUrl());
			Assert.assertTrue(pdfContent.contains(text));
		  Log.info("les textes ajoutes existent dans le pdf" + pdfContent);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.info("les textes ajoutes existent dans le pdf");
	}

	public void renseigner_type_de_document(String type) {
		Log.info("on renseigne le type de document");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'moreInfoViewButton')]")));
		click(By.xpath("//button[contains(@class,'moreInfoViewButton')]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Type Document*']/following::div[1]")));
		click(By.xpath("//div[text()='Type Document*']/following::div[1]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']",type))));
		click(By.xpath(String.format("//div[text()='%s']",type)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'moreInfoViewButton')]")));
		click(By.xpath("//button[contains(@class,'moreInfoViewButton')]"));
		//click(By.xpath("//button[@aria-label='Close']"));
	}
	
	
	public void valider_et_retour_le_document() {
		Log.info("on valide le document apres verifier existence des textes");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Valider et retour']")));
		click(By.xpath("//div[text()='Valider et retour']"));
		//List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		//driver.switchTo().window(windows.get(0));
		wait_avec_sleep(5000);
	}
	

	public void valider_et_ouvrir_le_document() {
//		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(windows.get(1));
	
		Log.info("on valide le document apres verifier existence des textes");
		//wait_avec_sleep(15000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Valider et ouvrir']")));
	  //  js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[text()='Valider et ouvrir']")));
			click(By.xpath("//div[text()='Valider et ouvrir']"));
			wait_avec_sleep(5000);
	}
	
	public void imprimer_le_document_en_pdf() {
		Log.info("on imprime le document apres verifier existence des textes");
		wait_avec_sleep(5000);
		click(By.xpath("//div[text()='Imprimer']"));
		wait_avec_sleep(8000);
	}
	
public static  String readPdfContent(String URL) throws IOException {
		
		URL pdfUrl = new URL(URL);
	
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
		
	return content;
}
	
	
	
	public void saisie_des_infos_supplementaires(String url,String text) {
		Log.info("on saisie des infos supplementaire dans l'ancient editor");
//		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(windows.get(1));
		Log.info("on clique sur button signets");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Signets')]")));
	    click(By.xpath("//div[contains(text(),'Signets')]"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-reportField")));
	    input("Prénom",By.id("search-reportField"));
	    click(By.xpath("//div[contains(text(),'Données du patient')]//following::td[text()='Prénom']"));
		Log.info("on ferme inserer informations fenetre");
		click(By.xpath("//button[@aria-label='Close']"));
//		Log.info("on verifie existence des des info ajoutes");
//		try {
//			String pdfContent = readPdfContent(url);
//			Assert.assertTrue(pdfContent.contains(text));
//		
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		Log.info("les textes ajoutes existent dans le pdf");
//	    driver.close();
//		driver.switchTo().window(windows.get(0));
	}
	

	
	
	
	
	
	 public void switchTowindow(int window) {
			List <String> windows = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windows.get(window));
			wait_avec_sleep(10000);
		}
	
	
	
	public void fermer_window() {
		driver.close();
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
