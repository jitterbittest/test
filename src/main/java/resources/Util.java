package resources;

import javax.swing.text.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Util {
	AndroidDriver<AndroidElement>  driver;

	public Util(AndroidDriver<AndroidElement> driver)
	{
		this.driver=driver;
	}

	public void scrolltotext(String text) {
		
		System.out.println("Now Scrolling down to " +text);
		driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector())."
				+ "scrollIntoView(text(\""+text+"\"));");
	}

}
