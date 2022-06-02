package SetupClass;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import gherkin.formatter.MonochromeFormats;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "." }, // tags= {"@2CO_checkout"},
		plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber/cucumber.json",
				"usage:target/usage.jsonx", "junit:target/cucumber.xml" }// ,
//monochrome = true, //display the console output in a proper readable format
//strict = true, //it will check if any step is not defined in step definition file
//dryRun = false //to check the mapping is proper between feature file and step def file
// @paypal_checkout, @2CO_checkout, @Sign_Up_Correct_Data
)

public class TestRun {

	@BeforeClass
	public static void beforeClass() throws Exception {
		SetClass.before_Class();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		SetClass.after_Class();
	}

}
