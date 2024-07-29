package page.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import clm.interop.Log;
import clm.interop.enumerator.Browser;
import clm.interop.webdrivermanager.Driver;
import utils.properties.PropertiesReader;

public class PageConnexion extends BasePage{
	
	By boutonAccepterCookies = By.cssSelector("#cookies-container:not([style *= 'display']) #accept-1");
	By champ_identifiant = By.name("username");
	By champ_mot_de_passe = By.name("password");
	By bouton_entrer = By.id("submitBtn");
	By bouton_carteCPS = By.id("csp_input");
	By logoAccueil = By.cssSelector("[alt='MLM logo']");
	static String login = PropertiesReader.openFile("test_execution").getProperty("login");
	static String motDePasse = (PropertiesReader.openFile("test_execution").getProperty("motdepasse"));
	static String login2 ="MEDECIN_100 ";
	static String motDePasse2="Azerty1*";
	static String url_mlm = (System.getProperty("execution.url").length() == 0) ? PropertiesReader.openFile("test_execution").getProperty("url") : System.getProperty("execution.url");
	
	
	public void seConnecterParLoginEtMotDePasse() {
		Log.info("Je me connecte en entrant l'identifiant ,le mot de passe et en cliquant sur entrer Entrer");
		if (elementIsPresentOnDOM(boutonAccepterCookies)) {
			click(boutonAccepterCookies);
			Log.info("Les cookies apparaissent");
			input(login, champ_identifiant);
			input(motDePasse, champ_mot_de_passe);
			click(bouton_entrer);
			reconnexion_si_echouerParLogin(2);
		} else {
			Log.info("Les cookies n'apparaissent pas");
			input(login, champ_identifiant);
			input(motDePasse, champ_mot_de_passe);
			click(bouton_entrer);
			reconnexion_si_echouerParLogin(2);
		}
	}
	
	public void seConnecterParLoginEtMotDePasse2() {
		Log.info("Je me connecte en entrant l'identifiant ,le mot de passe et en cliquant sur entrer Entrer");
		if (elementIsPresentOnDOM(boutonAccepterCookies)) {
			click(boutonAccepterCookies);
			Log.info("Les cookies apparaissent");
			input(login2, champ_identifiant);
			input(motDePasse2, champ_mot_de_passe);
			click(bouton_entrer);
			reconnexion_si_echouerParLogin(2);
		} else {
			Log.info("Les cookies n'apparaissent pas");
			input(login2, champ_identifiant);
			input(motDePasse2, champ_mot_de_passe);
			click(bouton_entrer);
			reconnexion_si_echouerParLogin(2);
		}
	}
	private void reconnexion_si_echouerParLogin(int essai) {
		int i = 0;
		while (elementIsPresentOnDOM(champ_identifiant) && i < essai) {
			Log.info(String.format("Essai %o : Je me reconnecte", (++i)));
			input(login, champ_identifiant);
			input(motDePasse, champ_mot_de_passe);
			click(bouton_entrer);
		}
	}

	
	public void seConnecterModeCarteCPS() {
		Log.info("=======POPUP accepter cookies ====== : " + driver.findElements(boutonAccepterCookies).size());
		Log.info("J'accepte les cookies si elles apparaissent");
		verificationPageConnexionBienAfficheSinonActualisation();
		if(elementIsPresentOnDOM(boutonAccepterCookies)) {
			click(boutonAccepterCookies);
			Log.info("Je me connecte avec la carte CPS");
			click(bouton_carteCPS);
			reconnexion_si_echouer(2);
			
		}else {
	    Log.info("Les cookies n'apparaissent pas");	
		Log.info("Je me connecte avec la carte CPS");
		click(bouton_carteCPS);
		reconnexion_si_echouer(2);
		 }
		}
	
	
	
	private void reconnexion_si_echouer(int essai) {
		int i = 0;
		while (elementIsPresentOnDOM(bouton_carteCPS) && i < essai) {
			Log.info(String.format("Essai %o : Je me reconnecte", (++i)));
			click(bouton_carteCPS);
		}
	}
	

	private void verificationPageConnexionBienAfficheSinonActualisation() {
		if(elementIsVisibleOnScreen(logoAccueil)) {
			Log.info("La page connexion a été bien chargée");		
			}else {
				Log.info("La page connexion n'a pas été bien donc on rafraichis le navigateur");
				driver.navigate().refresh();
			}
	}
	

	 public void serconnecte_au_mlm() {
		 WebDriver driver = Driver.getInstance(Browser.valueOf(browser));
		 driver.get(url_mlm);
	 }

}
