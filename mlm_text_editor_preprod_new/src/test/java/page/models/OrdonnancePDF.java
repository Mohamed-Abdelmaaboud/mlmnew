package page.models;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.google.common.io.Files;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import clm.interop.Log;

public class OrdonnancePDF extends BasePage{
	
	public void verifier_contenu_de_l_ordonnace(String text) {
		Log.info("on verifie contenu de l'ordonnance");
		try {
			String pdfContent = readPdfContent();
			Assert.assertTrue(pdfContent.contains(text));
			Log.info("les textes ajoutes existent dans le pdf" + pdfContent);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.info("les textes ajoutes existent dans le pdf");
		
		//driver.close();
	}

	public static  String readPdfContent() throws IOException {
	
	  //  String 	url = FileDownloaded_Ext("C:\\\\Users\\\\mabdelmaaboud\\\\Downloads","pdf");
		
		URL pdfUrl = new URL(recupereLeDernierFichier());
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		String content = new PDFTextStripper().getText(doc);
		doc.close();

	return content;
	}
	
	public void verifier_existence_texte_dans_le_document_pdf() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'TXTCTRL')]")));
		String txt = driver.findElement(By.xpath("//span[contains(text(),'TXTCTRL')]")).getText();
		Log.info(txt);
	}
	public void switchTowindow(int window) {
		List <String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(window));
	}

	public void fermer_window() {
		driver.close();
	}
	
	
	public void downLoadPDF() throws InterruptedException {
		Log.info("on telecharge le PDF");
		Thread.sleep(10000);
		WebElement d = driver.findElement(By.tagName("pdf-viewer"));
			System.out.println(d);	
//		Thread.sleep(10000);
//
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		WebElement downloadPDF =(WebElement) js.executeScript("return document.getElementById('viewer').shadowRoot.getElementById('toolbar').shadowRoot.getElementById('downloads').shadowRoot.getElementById('download')");
//		Thread.sleep(10000);
//		downloadPDF.click();
	}
	
	
	public static String recupereLeDernierFichier() {
	   // File dir = new File(sdir);
	    //if (dir.isDirectory()) {
	     //   File[] dirFiles = dir.listFiles((FileFilter)FileFilterUtils.fileFileFilter());
	      //  if (dirFiles != null && dirFiles.length > 0) {
	         //   Arrays.sort(dirFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		Log.info("on recupere le dernier telecharge fichier");
	//	File dir = new File("C:\\Users\\mabdelmaaboud\\Downloads");
		File dir = new File("C:\\Users\\QA_CLM\\Downloads");
		File[] files = dir.listFiles();
         Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
         Log.info(files[0].getPath());
	      return files[0].getPath();
	   
	}
	
	public void readpdfItext(String text) {
		try {
   
			PdfReader pdfReader = new PdfReader(recupereLeDernierFichier());	
			//Get the number of pages in pdf.
			int pages = pdfReader.getNumberOfPages(); 
			//Iterate the pdf through pages.
			for(int i=1; i<=pages; i++) { 
			  //Extract the page content using PdfTextExtractor.
			  String pageContent = PdfTextExtractor.getTextFromPage(pdfReader, i);
			  //Print the page content on console.
			  System.out.println("Content on Page "    + i + ": " + pageContent);
			  Assert.assertTrue(pageContent.contains(text));
		      }
		 
		      //Close the PdfReader.
		      pdfReader.close();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  }
	
	
	
	public void readpdfItext2(String text) {
		try {
   
			PdfReader pdfReader = new PdfReader(recupereLeDernierFichier());	
			
			  String pageContent = PdfTextExtractor.getTextFromPage(pdfReader,1);
			  //Print the page content on console.
			  System.out.println("Content on Page "  + ": " + pageContent);
			  Assert.assertTrue(pageContent.contains(text));
		    //  }
		 
		      //Close the PdfReader.
		      pdfReader.close();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  }
	
	
	
	public void getFontSizeAndFamily() throws IOException {
		 PdfReader reader = new PdfReader(recupereLeDernierFichier());
			
				 int nbmax = reader.getNumberOfPages();
				 System.out.println("nb pages " + nbmax);

				 for (int i = 1; i <= nbmax; i++) {
				    System.out.println("----------------------------------------");
				    System.out.println("Page " + i);
				    PdfDictionary dico = reader.getPageN(i);
				    PdfDictionary ressource = dico.getAsDict(PdfName.RESOURCES);
				    PdfDictionary font = ressource.getAsDict(PdfName.FONT);
				    // we got the page fonts
				// Set<PdfName> keys = (font.getKeys());
				 
				 List<PdfName> keys = new ArrayList<>(font.getKeys());
			       //java.util.Iterator<PdfName>  it =  keys.iterator();
				 //   while (((java.util.Iterator<PdfName>) keys).hasNext()) {
				 for(PdfName key:keys) {
				       //PdfName name = (PdfName) keys.get(i);
				       PdfDictionary fontdict = font.getAsDict(key);
				       PdfObject typeFont = fontdict.getDirectObject(PdfName.SUBTYPE);
				       PdfObject baseFont = fontdict.getDirectObject(PdfName.BASEFONT);  
				       //PdfObject fontSize = fontdict.getDirectObject(PdfName.);  
				       
				      // Assert.assertTrue(fontSize.toString().contains("8"));
				       System.out.println(baseFont.toString());      
				       System.out.println(typeFont.toString());   
				       //System.out.println(fontSize); 
				       Assert.assertTrue(baseFont.toString().contains("Tahoma"));
				    }
				 }
	}
	
	
	public List<RenderedImage> getImagesFromPDF(PDDocument document) throws IOException {
		List<RenderedImage> images = new ArrayList<>();
		for (PDPage page : document.getPages()) {
			images.addAll(getImagesFromResources(page.getResources()));
		}

		return images;
	}

	private List<RenderedImage> getImagesFromResources(PDResources resources) throws IOException {
		List<RenderedImage> images = new ArrayList<>();

		for (COSName xObjectName : resources.getXObjectNames()) {
			PDXObject xObject = resources.getXObject(xObjectName);
			
			if (xObject instanceof PDFormXObject) {
				images.addAll(getImagesFromResources(((PDFormXObject) xObject).getResources()));
			} else if (xObject instanceof PDImageXObject) {
				images.add(((PDImageXObject) xObject).getImage());
			}
		}

		return images;
	}

	
	public void verifierPoliceFont() throws IOException {
		Log.info("on verifie la police du Font");
		File newFile= new File(recupereLeDernierFichier());
	    PDDocument pdfDocument = PDDocument.load(newFile);
	    for (int i = 0; i < pdfDocument.getNumberOfPages(); ++i) {
	    PDPage page= pdfDocument.getPage(i);
	    PDResources resources =page.getResources();
	    for (COSName fontName : resources.getFontNames())
	    {
	      PDFont font = resources.getFont(fontName);
	     
	      System.out.println(font.getName());
	     
	      String font_name = font.getName();
	      Log.info("le nom de la font dans le pdf est :" + font_name);
	      Assert.assertEquals(font_name, font.getName());
	}
	}
	    
	}
	
	
	public void verifierPresenceImageDansLePDF() throws IOException {
    Log.info("je verifie l'existence d'un image insere dans le PDF et je verifie qu'y 'un seul");
	File newFile= new File(recupereLeDernierFichier());
    PDDocument pdfDocument = PDDocument.load(newFile);
   Assert.assertTrue(getImagesFromPDF(pdfDocument).size()==1,"y a pas des images sur le PDF");
   System.out.println( "number of images is : " + getImagesFromPDF(pdfDocument).size());
   Log.info("l'image existe dans le PDF");
  
   pdfDocument.close();
		
	}
	
	
	
	
	
	
	
	public void renommeDownloadPDF() {
		Log.info("on renomme le pdf qui telecharge pour pouvoir l'utiliser");
		  // Create an object of the File class
        // Replace the file path with path of the directory
        File file = new File("/home/mayur/Folder/GFG.java");
  
        // Create an object of the File class
        // Replace the file path with path of the directory
        File rename = new File("/home/mayur/Folder/HelloWorld.java");
  
        // store the return value of renameTo() method in
        // flag
        boolean flag = file.renameTo(rename);
  
        // if renameTo() return true then if block is
        // executed
        if (flag == true) {
            System.out.println("File Successfully Rename");
        }
        // if renameTo() return false then else block is
        // executed
        else {
            System.out.println("Operation Failed");
        }
    }
	
	
	
	
	
	private static String FileDownloaded_Ext(String dirPath,String ext){
		boolean flag=false;
		String fileName = dirPath ;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    List<File> list = Arrays.asList(files);
	    String fName = list.get(0).getName();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	
	    	}
	    }
	 
	    return fileName;
	}
	
	
	public String waitUntilDonwloadCompleted() throws InterruptedException {
	      // Store the current window handle
	      String mainWindow = driver.getWindowHandle();
	      
	      // open a new tab
	      JavascriptExecutor js = (JavascriptExecutor)driver;
	      js.executeScript("window.open()");
	     // switch to new tab
	    // Switch to new window opened
	      for(String winHandle : driver.getWindowHandles()){
	          driver.switchTo().window(winHandle);
	      }
	     // navigate to chrome downloads
	      driver.get("chrome://downloads");
	      
	      JavascriptExecutor js1 = (JavascriptExecutor)driver;
	      // wait until the file is downloaded
	      Long percentage = (long) 0;
	      while ( percentage!= 100) {
	          try {
	              percentage = (Long) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value");
	              //System.out.println(percentage);
	          }catch (Exception e) {
          // Nothing to do just wait
	        }
	          Thread.sleep(1000);
	      }
	     // get the latest downloaded file name
	      Thread.sleep(3000);
	      Log.info("on affiche le dossier");
		  
		   //js1.executeScript("arguments[0].click()",driver.findElement(By.xpath("//a[text()='Afficher le dossier']")));
		   //js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #show').click()");
		  //  js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.getElementById('title-area').click()");
	    String fileName = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");
	     // get the latest downloaded file url
	      String sourceURL = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').href");
	      // file downloaded location
	      String donwloadedAt = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div.is-active.focus-row-active #file-icon-wrapper img').src");
	      System.out.println("Download deatils");
	      System.out.println("File Name :-" + fileName);
	      System.out.println("Donwloaded path :- " + donwloadedAt);
	      System.out.println("Downloaded from url :- " + sourceURL);
	     // print the details
	      System.out.println(fileName);
	      System.out.println(sourceURL);
	     // close the downloads tab2
	      driver.close();
	     // switch back to main window
	      driver.switchTo().window(mainWindow);
	     // return fileName;
	      return donwloadedAt ;
	  }
	
	
	
	}

	

	
	
	
