package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class LoginScreenMobile {

	private static AndroidDriver driver;
	private static Operations opt;

	@FindBy(how = How.XPATH, using = "(.//*[@id='footerFixed']//div//a)[3]")
	WebElement profileIcon;

	@FindBy(how = How.ID, using = "registerPopupLink")
	WebElement register_login_btn;

	@FindBy(how = How.ID, using = "login-btn")
	WebElement loginBtn;

	@FindBy(how = How.ID, using = "hdrLoginEmailid")
	WebElement email_id_field;

	@FindBy(how = How.ID, using = "hdrLoginPassword")
	WebElement password_field;

	@FindBy(how = How.NAME, using = "usernameLogin")
	WebElement usernameLogin;

	@FindBy(how = How.ID, using = "search")
	WebElement searchField;

	@FindBy(how = How.ID, using = "searchButton")
	WebElement searchIcon;

	@FindBy(how = How.XPATH, using = "(.//*[@id='productView']//div[@unbxdattr='product']//a[1])[1]")
	WebElement firstProduct;

	@FindBy(how = How.ID, using = "vipBuyNowButton")
	WebElement buyNowBtn;

	@FindBy(how = How.ID, using = "webklipper-publisher-widget-container-notification-close-div")
	WebElement popupCloseBtn;

	@FindBy(how = How.XPATH, using = ".//*[@class='LoginRegister__mobile-wrap']//a[2]")
	WebElement login_using_password;

	@FindBy(how = How.XPATH, using = ".//*[@id='fixedBar']//div[text()='Profile']")
	WebElement profileHeader;

	public LoginScreenMobile(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		opt = new Operations(driver);
	}

	static String path = System.getProperty("user.dir") + "/Screenshots/Mobile/";

	public void navigateToProfile_ClickOnLogin() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"))));
			WebElement frame = driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));

			// closing ad

			driver.switchTo().frame(frame);
			opt.clickOnElement(popupCloseBtn);
			driver.switchTo().defaultContent();
			String currentContext = driver.getContext();
			System.out.println(currentContext);

			// navigating to profile
			opt.clickOnElement(profileIcon);

			opt.captureScreenshot("navigateToProfile", path);

			// closing ad
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"))));
			frame = driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
			driver.switchTo().frame(frame);
			opt.clickOnElement(popupCloseBtn);
			driver.switchTo().defaultContent();

			opt.clickOnElement(login_using_password);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			opt.captureScreenshot("clickOnLogin", path);

			System.out.println("Clicked on login button");
		} catch (Exception e) {
			throw new Exception("Failed to navigate to Login screen");
		}

	}

	public void login(String username, String password) throws Exception {
		try {
			// entering email & password
			
			opt.sendTextElement(email_id_field, username);
			opt.sendTextElement(password_field, password);
			opt.clickOnElement(loginBtn);
			if (!profileHeader.isDisplayed()) {

				throw new Exception("Log in Failed !!");
			} else {
				System.out.println("Logged in successfuly");
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			opt.captureScreenshot("afterLogin", path);

		} catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("Failed to login into the portal");
		}
	}
}
