package StepDefinitionsDesktop;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import screens.LoginScreen;
import screens.Operations;
import screens.ProductScreens;

public class VerifyPayValues {

	public static WebDriver driver;
	public static Operations opt;
	public static LoginScreen login;
	public static ProductScreens productScreens;
	static String propertyfilePath = System.getProperty("user.dir") + "/Properties/credentials.properties";
	static String youPay_cart = "";
	static String youPay_address = "";

	@Before
	public void setupBrowser() {
		String driverPath = System.getProperty("user.dir") + "/Drivers";
		System.setProperty("webdriver.chrome.driver", driverPath + "/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		login = new LoginScreen(driver);
		opt = new Operations(driver);
	}

	@Given("^user browse to pepperfry live website and click on profile$")
	public void navigateToProfile() {
		String url = opt.getProperty(propertyfilePath, "url");

		driver.get(url);
		login.navigateToProfile_ClickOnLogin();

	}

	@When("^user login to site using email and password$")
	public void userLogin() {
		String username = opt.getProperty(propertyfilePath, "username");
		String password = opt.getProperty(propertyfilePath, "password");

		try {
			login.login(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^user search sofa which redirects to product list$")
	public void searchSofa() {
		try {
			productScreens = new ProductScreens(driver);
			productScreens.searchProduct("sofa");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@And("^user clicks very first product from the list and product detail page gets open in new tab$")
	public void navigateToProductScreen() {
		try {
			productScreens.clickOnProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^user selects 2 in the quantity dropdown and clicks on Buy Now button$")
	public void selectQuanityAndBuyNow() {
		try {
			productScreens.buyNowFlow("2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^on cart page store the You Pay values$")
	public void storeYouPayValue_cart() {
		try {
			productScreens = new ProductScreens(driver);
			youPay_cart = productScreens.getYouPayValueOnCart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^user click on Place Order button then it will redirect to address page$")
	public void navigateToAddressPage() {
		try {
			productScreens.clickOnPlaceOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^store the You Pay value on address$")
	public void storeYouPayValue_address() {
		try {
			productScreens = new ProductScreens(driver);
			youPay_address = productScreens.getValueFromAddressPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^compare those with earlier stored You Pay value of cart$")
	public void compareYouPayValues() {
		Assert.assertEquals(youPay_cart, youPay_address);
	}
	
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}