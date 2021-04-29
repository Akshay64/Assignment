package screens;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductScreens {
	private static WebDriver driver;
	private static Operations opt;

	@FindBy(how = How.ID, using = "search")
	WebElement searchField;

	@FindBy(how = How.ID, using = "searchButton")
	WebElement searchIcon;

	@FindBy(how = How.XPATH, using = "(.//*[@id='productView']//div[@unbxdattr='product']//a)[4]")
	WebElement firstProduct;

	@FindBy(how = How.ID, using = "select2-quantity-container")
	WebElement quantityContainer;

	@FindBy(how = How.ID, using = "webklipper-publisher-widget-container-notification-close-div")
	WebElement closeAd;

	@FindBy(how = How.ID, using = "vipBuyNowButton")
	WebElement buyNowBtn;

	@FindBy(how = How.CSS, using = "#total_pay_coupon")
	WebElement youPayValue_Cart;

	@FindBy(how = How.LINK_TEXT, using = "PLACE ORDER")
	WebElement placeOrderBtn;

	@FindBy(how = How.ID, using = "total_pay_coupon")
	WebElement youPayValue_address;
	
	@FindBy(how = How.ID, using="webklipper-publisher-widget-container-notification-frame")
	WebElement notificationFrame;
	
	static String path = System.getProperty("user.dir")+"/Screenshots/Desktop/";

	
	public ProductScreens(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		opt = new Operations(driver);
	}

	public void searchProduct(String productName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(searchIcon));

		opt.sendTextElement(searchField, productName);
		Thread.sleep(2000);
		opt.captureScreenshot("productSearch", path);

		opt.clickOnElement(searchIcon);
		Thread.sleep(2000);
		opt.captureScreenshot("searchResults", path);

		if (!firstProduct.isDisplayed()) {

			throw new Exception("Search functionality is not working !!");
		} else {
			System.out.println("Searched product in successfuly");
		}
	}

	public void clickOnProduct() throws Exception {
		opt.clickOnElement(firstProduct);
		
		Thread.sleep(3000);
		opt.captureScreenshot("productInfo", path);

		String parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		Iterator<String> itr = windows.iterator();
		while (itr.hasNext()) {
			String tmp = itr.next();
			if (!tmp.equals(parentWindow)) {
				driver.switchTo().window(tmp);
			}
		}

		if (!buyNowBtn.isDisplayed()) {

			throw new Exception("Failed to switch tab!!");
		} else {
			System.out.println("Navigated to product details screen successfuly");
		}
	}

	public void buyNowFlow(String quantity) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(quantityContainer));

		try {
			Thread.sleep(5000);
			if(notificationFrame.isDisplayed()) {
				driver.switchTo().frame(notificationFrame);
				opt.clickOnElement(closeAd);
				driver.switchTo().defaultContent();
			}
			
		} catch (NoSuchElementException e) {
			System.out.println("No Ad pop-up displayed");
		}
		try {
			opt.clickOnElement(quantityContainer);

			WebElement quantityValue = driver
					.findElement(By.xpath(".//*[@id='select2-quantity-results']//li[" + quantity + "]"));
			opt.clickOnElement(quantityValue);
			Thread.sleep(2000);
			opt.captureScreenshot("setQuantity", path);
			
			System.out.println("Selected the quantity successfuly");
			opt.clickOnElement(buyNowBtn);
			
			Thread.sleep(3000);
			opt.captureScreenshot("afterBuyNow", path);
			
			System.out.println("Clicked on Buy Now Button");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception occured in buy now flow");
		}
	}

	public String getYouPayValueOnCart() throws Exception {
		String price = "";
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(youPayValue_Cart));
		try {
			price = opt.getTextFromWebElement(youPayValue_Cart).trim();
			Thread.sleep(2000);
			opt.captureScreenshot("cartValue", path);
			System.out.println("You Pay Value from Cart = " + price);
		} catch (Exception e) {
			throw new Exception("Exception occured in while getting You pay value in Cart");
		}

		return price;
	}

	public void clickOnPlaceOrder() throws Exception {
		try {
			opt.clickOnElement(placeOrderBtn);
			Thread.sleep(3000);
			opt.captureScreenshot("clickPlaceOrder", path);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(youPayValue_address));
			System.out.println("Navigated to Address screen successfuly");
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Exception occured while navigating to the Address screen");
		}

	}

	public String getValueFromAddressPage() throws Exception {
		String price = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(youPayValue_address));
			Thread.sleep(2000);
			opt.captureScreenshot("valueOnAddress", path);
			price = opt.getTextFromWebElement(youPayValue_address).trim();
			System.out.println("You Pay Value from Address = " + price);

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Exception occured in while getting You pay value on Address screen");
		}

		return price;
	}

}
