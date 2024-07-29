package listener;

import java.sql.Timestamp;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount = -1;
    private static long timePassed = 0;
    private static long start = 0;
    
    public boolean retry(ITestResult result) {
        int maxRetryCount = 10;
        while (retryCount++ < maxRetryCount) {
        	print();
        	if (start == 0) {
        		start = new Timestamp(System.currentTimeMillis()).getTime();
        	}
        	return true;
        }
        print();
        return false;
    }
    
    private void print() {
    	if (retryCount > 0) {
    		long nowPlus1 = new Timestamp(System.currentTimeMillis()).getTime();
        	timePassed = (nowPlus1 - start) / retryCount;
        	System.out.println("Temps moyen après l'exécution n°" + retryCount + " : " + timePassed/1000 + "secondes");
    	}
    }
}