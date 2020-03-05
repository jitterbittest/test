package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/*
import admin_home.AdminHomeTC;
import admin_login.AdminLoginTC;
import error_report.ErrorReportTC;
import login.LoginTC;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import resetpassword.ResetPasswordTC;
import sendgift.SendGiftTC;
import user_change_password.UserChangePasswordTC;
import userhistory.UserTransactionHistoryTC;
*/
public class TestBase {

	public static WebDriver driver;
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static boolean changePreCondtion;
	public static int count = 0;

	@BeforeSuite
	public void setExtent() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Swych.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Anjali");
	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}
	

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	/**
	 * 
	 * @param context
	 * @throws InterruptedException
	 * @throws Exception
	 */
/*
	@BeforeClass
	public void moduleSetup(ITestContext context) throws Exception {
		Map<String, String> parameterMap = context.getCurrentXmlTest().getAllParameters();
		initiateWebdriver(context);
		driver.manage().window().maximize();
		driver.get(parameterMap.get("url"));

		if (context.getCurrentXmlTest().getClasses().get(0).getName().toString().equalsIgnoreCase("login.LoginTC")
				&& count != 0) {
			LoginTC obj = new LoginTC();
			String[] data1 = obj.getData();
			obj.loginPreCondition(data1[0]);
		}

		else if (context.getCurrentXmlTest().getClasses().get(1).getName().toString()
				.equalsIgnoreCase("resetpassword.ResetPasswordTC") && count != 0) {
			ResetPasswordTC obj = new ResetPasswordTC();
			String[] data1 = obj.getData();
			obj.ResetPasswordPreCondition(data1[0]);
		}

		else if (context.getCurrentXmlTest().getClasses().get(2).getName().toString()
				.equalsIgnoreCase("user_change_password.UserChangePasswordTC") && count != 0) {

			UserChangePasswordTC obj = new UserChangePasswordTC();
			String[] data1 = obj.getData();
			obj.changePasswordPreCondition(data1[0], data1[1], data1[2], data1[3], data1[4]);
		}

		else if (context.getCurrentXmlTest().getClasses().get(3).getName().toString()
				.equalsIgnoreCase("sendgift.SendGiftTC") && count != 0) {
			SendGiftTC obj = new SendGiftTC();
			String[] data1 = obj.getData();
			obj.sendGiftPreCondition(data1[0], data1[1], data1[2], data1[3], data1[4]);
		}

		else if (context.getCurrentXmlTest().getClasses().get(4).getName().toString()
				.equalsIgnoreCase("userhistory.UserTransactionHistoryTC") && count != 0) {

			UserTransactionHistoryTC obj = new UserTransactionHistoryTC();
			String[] data1 = obj.getData();
			obj.userHistoryPreCondition(data1[0], data1[1], data1[2], data1[3], data1[4]);
		}

		else if (context.getCurrentXmlTest().getClasses().get(5).getName().toString()
				.equalsIgnoreCase("error_report.ErrorReportTC") && count != 0) {
			ErrorReportTC obj = new ErrorReportTC();
			String[] data1 = obj.getData();
			obj.errorReportPreCondition(data1[0], data1[1], data1[2], data1[3], data1[4]);
		}

		else if (context.getCurrentXmlTest().getClasses().get(6).getName().toString()
				.equalsIgnoreCase("admin_login.AdminLoginTC") && count != 0) {
			AdminLoginTC obj = new AdminLoginTC();
			String[] data1 = obj.getData();
			obj.loginPreCondition(data1[0]);

		}

		else if (context.getCurrentXmlTest().getClasses().get(7).getName().toString()
				.equalsIgnoreCase("admin_home.AdminHomeTC") && count != 0) {
			AdminHomeTC obj = new AdminHomeTC();
			String[] data1 = obj.getData();
			obj.adminHomePreCondition(data1[0], data1[1], data1[2]);
		}

	}

	@AfterClass
	public void TearDown() {
		if (driver != null)
			driver.quit();
	}
	*/

	/**
	 * 
	 * @param context
	 */
	/*
	public void initiateWebdriver(ITestContext context) {
		String os = System.getProperty("os.name").toUpperCase();
		String browser;
		Map<String, String> parameterMap = context.getCurrentXmlTest().getAllParameters();
		if (os.contains("WINDOWS") || os.contains("LINUX") || os.contains("MAC")) {
			if (parameterMap.containsKey("browser")) {
				browser = parameterMap.get("browser");
				if (browser.equalsIgnoreCase("chrome")) {
					if (os.contains("WINDOWS")) {
						System.setProperty("webdriver.chrome.driver", "lib/Windows/chromedriver.exe");
					} else if (os.contains("MAC")) {
						System.setProperty("webdriver.chrome.driver", "lib/Mac/chromedriver");
					} else if (os.contains("LINUX")) {
						System.setProperty("webdriver.chrome.driver", "lib/Linux/chromedriver");
					}
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("firefox")) {
					if (os.contains("WINDOWS")) {
						System.setProperty("webdriver.gecko.driver", "lib/Windows/geckodriver.exe");
					} else if (os.contains("MAC")) {
						System.setProperty("webdriver.gecko.driver", "lib/Mac/geckodriver");
					} else if (os.contains("LINUX")) {
						System.setProperty("webdriver.gecko.driver", "lib/Linux/geckodriver");
					}
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("IE")) {
					if (os.contains("WINDOWS")) {
						System.setProperty("webdriver.ie.driver", "lib/Windows/IEDriverServer.exe");
					} else if (os.contains("LINUX")) {
						System.setProperty("webdriver.ie.driver", "lib/Linux/IEDriverServer");
					}
					driver = new InternetExplorerDriver();
				} else if (browser.equalsIgnoreCase("safari")) {
					driver = new SafariDriver();
				}
			}
		}
	}

	public void TcFailedOrChangedStatus(ITestContext context) throws Exception {
		driver.quit();
		moduleSetup(context);
	}

	public void changeState() {
		changePreCondtion = true;

	}
*/
	/**
	 * This method is used for move to this element and click on the element
	 * 
	 * @param driver
	 */

	public void clickOnPageObject(String xpath) {
		Actions builder = new Actions(driver);
		builder.moveToElement(getElement(xpath)).click(getElement(xpath));
		builder.perform();
	}

	/*
	 * This method is used for the verify the current URL
	 * 
	 * @param url
	 */

	public void verifyCurrentPageURL(String expectedURL) throws InterruptedException {

		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL);

	}

	/**
	 * This method is used for the get the element by xpath
	 * 
	 * @param driver
	 */

	public WebElement getElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	/**
	 * This method is used for the select the value by name from the drop-down
	 */

	public void selectByVisibleTextDropDown(String dropDown, String visibleText) {
		Select dropdown = new Select(getElement(dropDown));
		dropdown.selectByVisibleText(visibleText);
	}

	/**
	 * This method is used for the select the value from drop-down by index
	 */

	public void selectByIndexDrop_down(String dropDown, int index) {
		Select dropdown = new Select(getElement(dropDown));
		dropdown.selectByIndex(index);
	}

	/**
	 * This method is used for the get the elements by xpath
	 * 
	 * @param xpath
	 */

	public ArrayList<WebElement> getElements(String xpath) {
		ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElements(By.xpath(xpath));
		return (elements);
	}

	/**
	 * This method is used for the reordering the element list
	 * 
	 * @param list
	 */
	public ArrayList<WebElement> getRandomElementList(ArrayList<WebElement> list) {
		Random rand = new Random();

		for (int i = 0; i < list.size(); i++) {
			int randomIndex = rand.nextInt(list.size());
			list.add(list.get(randomIndex));
		}
		return list;
	}

	/**
	 * This method is used for the generate the random element
	 */
	public static String randomEmail() {
		Random rand = new Random();
		int randomIndex = rand.nextInt();
		String email = "Test" + randomIndex + "@yahoo.co";
		return email;
	}

	/**
	 * This method is used for the clear field
	 * 
	 * @param xpath
	 */

	public void clearField(String xpath) {
		driver.findElement(By.xpath(xpath)).clear();
	}

	/*
	 * This method is used for the send the input
	 * 
	 * @param object
	 */
	public void inputField(String xpath, String input) {
		WebElement webelement = driver.findElement(By.xpath(xpath));
		webelement.sendKeys(input);
	}

	public boolean isElementPresent(By by) {
		boolean flag = true;
		// WebElement element = driver.findElement(by);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;

		// try {
		// Object obj = jse.executeScript("return typeof(arguments[0]) !=
		// 'undefined' && arguments[0] != null;",
		// element);
		// if (obj.toString().contains("true")) {
		// System.out.println("isElementPresentCheckUsingJavaScriptExecutor:
		// SUCCESS");
		// return true;
		// } else {
		// System.out.println("isElementPresentCheckUsingJavaScriptExecutor:
		// FAIL");
		// }
		//
		// } catch (NoSuchElementException e) {
		// System.out.println("isElementPresentCheckUsingJavaScriptExecutor:
		// FAIL");
		// }
		//
		// finally{
		// System.out.println("isElementPresentCheckUsingJavaScriptExecutor:
		// FAIL");
		// }
		//
		// return false;

		try {
			if (null == driver.findElement(by)) {
				flag = false;
			}
		} catch (NoSuchElementException e) {
			System.out.println("No elemet ");
		}
		return flag;
	}

	public String getCurrenURL() {
		return driver.getCurrentUrl();
	}

	public void openNewtab() throws AWTException {
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_T);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_T);
	}

	public void closeCurrentTab() throws AWTException {
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_W);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_W);
	}

	public void switchToWindowByIndex(String index) throws InterruptedException {
		int i = Integer.parseInt(index);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(i));
	}
	/*public static class email {
	public static void sendemail() throws EmailException {
		System.out.println ("Starting to send email");
		Email email = new SimpleEmail();
		email.setHostName("imap.gmail.com");
		email.setSmtpPort(993);
		email.setAuthenticator(new DefaultAuthenticator("partha@goswych.com", "Sep@2019"));
		email.setSSLOnConnect(true);
		email.setFrom("saapindia@gmail.com");
		email.setSubject("Test Report");
		email.setMsg("This is Swych Automation ... :-)");
		email.addTo("partha@goswych.com");
		email.send();
		System.out.println ("Sent email");
	}
	}
	*/
	/*
	 * This method is used for the page contain this text or not
	 * 
	 * @param text
	 */
	/*
	public boolean pagecontaintext(String validationMessage)
			throws IOException, TesseractException, InterruptedException {
		boolean flag = false;
		for (int itr = 0; itr <= 10; itr++) {
			Thread.sleep(1000);
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			ITesseract instance = new Tesseract();
			instance.setDatapath(System.getProperty("user.dir") + "\\src\\main\\resources\\config");
			String result = instance.doOCR(SrcFile);
			if (result.contains(validationMessage)) {
				flag = true;
				Thread.sleep(1000);
				break;
			}
		}
		return flag;
	}
*/
}
