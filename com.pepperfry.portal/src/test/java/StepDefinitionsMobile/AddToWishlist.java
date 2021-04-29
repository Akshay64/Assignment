package StepDefinitionsMobile;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import screens.LoginScreen;
import screens.LoginScreenMobile;
import screens.Operations;
import screens.ProductScreensMobile;

public class AddToWishlist {

	public static AndroidDriver driver;
	public static Operations opt;
	public static DesiredCapabilities caps;
	public static LoginScreenMobile login;
	public static ProductScreensMobile productScreens;
	static String propertyfilePath = System.getProperty("user.dir") + "/Properties/credentials.properties";

	@Before
	public void setupBrowser() {
		caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "2da5e06e");
		caps.setCapability("platformName", "Android");
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		//caps.setCapability("autoAcceptAlerts", true);
		caps.setCapability("autoDismissAlerts", true);
		
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.setExperimentalOption("excludeSwitches",
		 * Collections.singletonList("enable-automation"));
		 * options.setExperimentalOption("useAutomationExtension", false);
		 * options.addArguments("--disable-extensions");
		 * options.addArguments("test-type"); Map<String, Object> prefs = new
		 * HashMap<String, Object>(); prefs.put("credentials_enable_service", false);
		 * prefs.put("profile.password_manager_enabled", false);
		 * options.setExperimentalOption("prefs", prefs);
		 * 
		 * caps.setCapability(ChromeOptions.CAPABILITY, options);
		 */
		
		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			login = new LoginScreenMobile(driver);
			productScreens = new ProductScreensMobile(driver);
			opt = new Operations(driver);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Given("^user browse to pepperfry live website and click on profile$")
	public void navigateToProfile() {
		String url = opt.getProperty(propertyfilePath, "url");

		driver.get(url);
		try {
			Thread.sleep(5000);
			//code to dismiss add to home screen notification
			
			Set<String> contexts = driver.getContextHandles();
			Iterator<String> itr = contexts.iterator();
			try {
			while(itr.hasNext()) {
				String tmp = itr.next();
				if(tmp.equalsIgnoreCase("NATIVE_APP")){
					driver.context("NATIVE_APP");
					driver.findElement(By.id("infobar_close_button")).click();
				}
				//NATIVE_APP //	CHROMIUM
			}
			}
			catch(NoSuchElementException e) {
				System.out.println("No add to home screen notification is displayed !");
			}
			driver.context("CHROMIUM");
			login.navigateToProfile_ClickOnLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("^user login to site using email and password$")
	public void login() {
		String username = opt.getProperty(propertyfilePath, "username");
		String password = opt.getProperty(propertyfilePath, "password");

		try {
			login.login(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@And("^hover on Furniture and click on Sofa Sets$")
	public void goToSofas() {
		
		try {
			productScreens.goToSofaSets();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^on redirected product listing page, click on first product$")
	public void selectFirstProduct() {
		try {
			productScreens.selectFirtProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^it will open product in new tab/page, click on Add to wishlist and store the product name$")
	public void addFirstProduct() {
		try {
			productScreens.addFirstProductToWishlist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^Go back to previous tab/page, look for second product in the list & click on Add to wishlist & store the product name$")
	public void addSecondProduct() {
		try {
			productScreens.addSecondProduceToWishlist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^click on Wishlist on the top section$")
	public void goToWishlist() {
		try {
			productScreens.navigateToWishlist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@And("^Validate the product names in the Whislist$")
	public void validateWishlistProducts() {
		Assert.assertTrue(productScreens.validateProductNames());
	}
	
	@And("^Validate the count of the Wishlist and the number of products listed in the side panel$")
	public void validateWishlistCounts() {
		Assert.assertTrue(productScreens.validateWishlistCounts());
	}
}
