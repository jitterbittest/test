package net.javaguides.maven_web_project.welcometointernet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
@Test

public class first2 {

	public void screenshot() throws Exception {
		 System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			
			driver.get("http://the-internet.herokuapp.com/");
			System.out.println(driver.findElement(By.xpath("//*[@id=\'content\']/h1")).getText());
			
		    
		     (new WebDriverWait(driver, 10)).until(
		     
		            ExpectedConditions.presenceOfElementLocated(
                 By.linkText("A/B Testing"))).click();
		    
		    driver.navigate().back();
		    
		    (new WebDriverWait(driver, 10)).until(
		            ExpectedConditions.presenceOfElementLocated(
                 By.cssSelector("a[href='/add_remove_elements/']"))).click();
		    
		    
			
	}
}


	
