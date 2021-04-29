package screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection;

public class Operations {

	private static WebDriver driver;

	public Operations(WebDriver driver) {
		this.driver = driver;
	}

	public static void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"background:yellow; border:2px solid red;");
		try {
			Thread.sleep(250);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void moveToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		highlightElement(driver, element);

	}

	public static void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public static void scrollToBottom() {
		((JavascriptExecutor)

		driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static String getTextFromWebElement(WebElement element) {
		moveToElement(element);
		String text = element.getText();
		return text;
	}

	public static void clickOnElement(WebElement element) {
		moveToElement(element);
		element.click();
	}

	public static void sendTextElement(WebElement element, String text) {
		moveToElement(element);
		element.sendKeys(text);
	}

	public static ArrayList<String> getTextFromListOfWebElements(List<WebElement> elements) {

		ArrayList<String> listOfText = new ArrayList<String>();
		Iterator iterator = elements.iterator();
		while (iterator.hasNext()) {
			WebElement ele = (WebElement) iterator.next();
			moveToElement(ele);
			String text = ele.getText();
			listOfText.add(text);
		}
		return listOfText;
	}

	public static String getProperty(String filePath, String key) {
		String value = "";

		try {
			FileInputStream fin = new FileInputStream(filePath);

			Properties pro = new Properties();
			pro.load(fin);
			value = pro.getProperty(key);

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}
	
	public static void captureScreenshot(String fileName, String path) {
		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmm");  
		   LocalDateTime now = LocalDateTime.now();  
		 //  System.out.println(dtf.format(now)); 
		
		TakesScreenshot ss = ((TakesScreenshot) Operations.driver);
		File sourceFile = ss.getScreenshotAs(OutputType.FILE);
		
		String filePath = path+fileName+dtf.format(now)+".png";
		
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
