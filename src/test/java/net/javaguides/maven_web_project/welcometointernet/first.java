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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
@Test

public class first {

	public void screenshot() throws Exception {
		 System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			
			driver.get("http://the-internet.herokuapp.com/");
			System.out.println(driver.findElement(By.xpath("//*[@id=\'content\']/h1")).getText());
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(source, new File("./Screenshots/Screen.png"));
			FileUtils.copyFile(source, new File("C:/test/test.png"));
			System.out.println("the Screenshot is taken");
			
			//Find Link
			List<WebElement> links = driver.findElements(By.tagName("a"));
			
			
			 
			 System.out.println("The number of links on the web page is " +links.size());
			 FileWriter w = new FileWriter("C:\\test\\temp.txt");
		     BufferedWriter out = new BufferedWriter(w);
		     
			 for (int i = 0; i<links.size(); i=i+1)
			 
			 {
			 
			 System.out.println(links.get(i).getText());
			 out.write(links.get(i).getText());
		     out.newLine();
			 
			 }
			 out.flush();
			 
			 //broken link
			 for(int i=0;i<links.size();i++)
				{
					
					WebElement ele= links.get(i);
					
					String url=ele.getAttribute("href");
					
					verifyLinkActive();
					
				}
			 System.out.println("There no broken link");
			 }
    // checkbox- java.util.List&lt;WebElement&gt; checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); System.out.println(checkboxes.size());
	
	//menu - java.util.List;WebElement&gt; dropdown = driver.findElements(By.tagName("select"));  
	//System.out.println(dropdown.size());	
	
	//textboxes - java.util.List;WebElement&gt; textboxes = driver.findElements(By.xpath("//input[@type='text'[@class='inputtext']")); System.out.println(textboxes.size());
	
	
	public static void verifyLinkActive()
	{
        try 
        {
        	String linkUrl="http://the-internet.herokuapp.com/";
           URL url = new URL(linkUrl);
           
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
           
           httpURLConnect.setConnectTimeout(3000);
           
           httpURLConnect.connect();
           
           if(httpURLConnect.getResponseCode()==200)
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {
           
        }
     
	}
     

}


	
