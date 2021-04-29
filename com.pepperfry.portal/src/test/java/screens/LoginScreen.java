package screens;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScreen {

	private static WebDriver driver;
	private static Operations opt;

	@FindBy(how = How.XPATH, using = ".//*[@class='header-nav-item profile']")
	WebElement profileIcon;

	@FindBy(how = How.ID, using = "registerPopupLink")
	WebElement register_login_btn;

	@FindBy(how = How.XPATH, using = ".//*[contains(text(),'Existing User? Log In')]")
	WebElement loginBtn;

	@FindBy(how = How.NAME, using = "user[new]")
	WebElement email_id_field;

	@FindBy(how = How.NAME, using = "password")
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
	
	static String path = System.getProperty("user.dir")+"/Screenshots/Desktop/";

	
	public LoginScreen(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		opt = new Operations(driver);
	}

	public void navigateToProfile_ClickOnLogin() {

		Actions action = new Actions(driver);
		action.moveToElement(profileIcon).click(register_login_btn).build().perform();

		opt.clickOnElement(loginBtn);
		opt.captureScreenshot("clickLogin", path);

		System.out.println("Clicked on login button");

	}

	public void login(String username, String password) throws Exception {
		opt.sendTextElement(email_id_field, username);
		opt.sendTextElement(password_field, password);
		opt.clickOnElement(usernameLogin);
		Thread.sleep(4000);
		opt.captureScreenshot("afterLogin", path);

		if (!searchField.isDisplayed()) {

			throw new Exception("Log in Failed !!");
		} else {
			
			System.out.println("Logged in successfuly");
		}

	}

		
	

}
