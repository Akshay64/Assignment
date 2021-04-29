package screens;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class ProductScreensMobile {
	
	private static AndroidDriver driver;
	private static Operations opt;
	private static String secondProductName;
	private static String firstProductName;
	
	@FindBy(how=How.XPATH,using="(.//*[@id='footerFixed']//div//a)[1]")
	WebElement homeScreenIcon;
	
	@FindBy(how=How.XPATH,using=".//*[@class='menu-item']//*[text()='Furniture']")
	WebElement fernitureMenu;
	
	@FindBy(how=How.XPATH,using=".//*[(text()='Sofas')]/../../..")
	WebElement sofasOption;
	
	@FindBy(how=How.XPATH,using=".//a[(text()='Sofa Sets')]")
	WebElement sofaSetsOption;
	
	@FindBy(how=How.XPATH,using="(.//*[@id='ProductsContainer']/div[1]//div[@class='clip-pro-dtls-wrap']//div[2]/a)[1]")
	WebElement firstProduct;
	
	@FindBy(how=How.XPATH,using="(.//*[@id='ProductsContainer']/div[2]//div[@class='clip-pro-dtls-wrap']//div[2]/a)[1]")
	WebElement secondProduct;
	
	@FindBy(how=How.XPATH,using="(.//*[@id='ProductsContainer']/div[3]//div[@class='clip-pro-dtls-wrap']//div[2]/a)[1]")
	WebElement thirdProduct;
	
	@FindBy(how=How.LINK_TEXT,using="ADD TO WISHLIST")
	WebElement addToWishlist;
	
	@FindBy(how=How.XPATH,using="(.//*[@id='fixedBar']//a)[1]")
	WebElement backArrow;
	
	@FindBy(how=How.XPATH,using=".//*[@class='hd-menu-list hd-wishlist']/a")
	WebElement wishlistIcon;
	
	@FindBy(how=How.XPATH,using=".//*[@class='hd-menu-list hd-wishlist']/a//span")
	WebElement wishlist_badge_icon;
	
	@FindBy(how=How.XPATH,using=".//*[@id='wishlistHolderlist']/div")
	List<WebElement> wishlist_items;
	
	@FindBy(how=How.XPATH,using=".//*[@id='fixedBar']//*[text()='Wishlist']")
	WebElement wishlistHeader;
	
	static String path = System.getProperty("user.dir") + "/Screenshots/Mobile/";

	
	public ProductScreensMobile(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		opt = new Operations(driver);
	}
	
	public void goToSofaSets() throws Exception {
		try {
		//navigate to Homescreen
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(homeScreenIcon));
		
		opt.clickOnElement(homeScreenIcon);
		Thread.sleep(2000);
		opt.captureScreenshot("homeScreen", path);
		System.out.println("Navigated to Home screen");
		
		//selecting ferniture menu
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@class='menu-item']//*[text()='Furniture']"))));
		opt.clickOnElement(fernitureMenu);
		Thread.sleep(2000);
		opt.captureScreenshot("ferniture", path);
		System.out.println("Navigated to Ferniture screen");

		//select Sofas
		wait.until(ExpectedConditions.elementToBeClickable(sofasOption));
		opt.clickOnElement(sofasOption);
		Thread.sleep(2000);
		opt.captureScreenshot("sofaOptions", path);
		System.out.println("Selected Sofas");

		//Select SofaSets
		opt.clickOnElement(sofaSetsOption);
		Thread.sleep(2000);
		opt.captureScreenshot("sofaOptions", path);
		System.out.println("Selected Sofas Sets");

		}
		catch(Exception e) {
			throw new Exception("Failed to navigate to Sofa Sets");
		}
	}
	
	public void selectFirtProduct() throws Exception {
		try {
		//saving name for first product and then clicking on it	
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(.//*[@id='ProductsContainer']/div[1]//div[@class='clip-pro-dtls-wrap']//div[2])[1]"))));
		firstProductName = opt.getTextFromWebElement(firstProduct);
		System.out.println("First Product Name = "+ firstProductName);

		opt.clickOnElement(firstProduct);
		Thread.sleep(2000);  
		opt.captureScreenshot("firstProduct", path);
		}
		catch(Exception e) {
			throw new Exception("Failed to Select first product");
			
		}
	}
	
	public void addFirstProductToWishlist() throws Exception {
	//add first product to wishlist	
		try {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("ADD TO WISHLIST")))); 
		
		opt.clickOnElement(addToWishlist);	
		Thread.sleep(2000);
		opt.captureScreenshot("firstProductToWish", path);
		}
		catch(Exception e) {
			throw new Exception("Failed to add first product to wishlist");
		}
	}
	
	public void addSecondProduceToWishlist() throws Exception {
	try {
		//click on back button
		opt.clickOnElement(backArrow);
		Thread.sleep(2000);
		opt.captureScreenshot("afterBack", path);
		System.out.println("Clicked on Back arrow");
		//saving name for secon product and then clicking on it
		secondProductName = opt.getTextFromWebElement(secondProduct);
		System.out.println(secondProductName);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//moving to thrird product so that second product should be visible properly
		opt.moveToElement(thirdProduct);
		
		//selecting second product
		opt.clickOnElement(secondProduct);
		Thread.sleep(2000);
		opt.captureScreenshot("secondProduct", path);
		System.out.println("Clicked on second product");

		//add second product to wishlist
		opt.clickOnElement(addToWishlist);
		Thread.sleep(2000);
		opt.captureScreenshot("secondProductToWish", path);
		System.out.println("added second product to wishlist");
		
	}
	catch(Exception e) {
		throw new Exception("Failed to add second product to wishlist");
	}
	}
	
	public void navigateToWishlist() throws Exception {
		//navigting to wishlist
		try {
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current window = "+ currentWindow);
		opt.clickOnElement(wishlistIcon);
		Thread.sleep(2000);
		opt.captureScreenshot("clickWishlist", path);
		System.out.println("Clicked on Wishlist icon");
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		
		while(itr.hasNext()) {
		String tmp = itr.next();
		{
			if(!tmp.equalsIgnoreCase(currentWindow)) {
				driver.switchTo().window(tmp);
				System.out.println("Switched to other window");
				System.out.println("Navigated to Wishlist");
			}
		}
		}
		}catch(Exception e) {
			throw new Exception("Failed to navigate to Wishlist");
			
		}
	}
	
	public boolean validateProductNames() {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(wishlistHeader));
		
		boolean flag1 = driver.findElement(By.partialLinkText(firstProductName)).isDisplayed();
		boolean flag2 = driver.findElement(By.partialLinkText(secondProductName)).isDisplayed();
		
		if(flag1 && flag2) {
			System.out.println("Selected products successfuly added to wishlist");
			flag=true;
			
		}
		return flag;
	}
	
	public boolean validateWishlistCounts() {
		boolean flag = false;
		String badgeCount = opt.getTextFromWebElement(wishlist_badge_icon);
		System.out.println("Wishlist count = "+ badgeCount);
		
		int productListSize = wishlist_items.size();
		String productListSize_String = Integer.toString(productListSize);
		System.out.println("Number of produce listed in wishlist = " + productListSize_String);
		
		if (badgeCount.equals(productListSize_String)) {
			System.out.println("Wishlist counts matched successfuly ");
			flag = true;

		}
		return flag;
		
	}

}
