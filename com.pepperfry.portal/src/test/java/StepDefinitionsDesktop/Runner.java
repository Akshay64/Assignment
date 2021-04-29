package StepDefinitionsDesktop;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/DesktopFeatures"
		,glue = {"StepDefinitionsDesktop"}
		,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		,monochrome = true
		,publish = true
		
		)
public class Runner extends AbstractTestNGCucumberTests {

}

