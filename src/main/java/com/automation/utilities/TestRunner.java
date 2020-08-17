package com.automation.utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;;

///@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/FeatureFile", glue = { "com.automation.stepdef" },
tags="@Tahmarai")

public class TestRunner extends AbstractTestNGCucumberTests   {

	DriverCapabailities webDriver = new DriverCapabailities();
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	@Parameters("Common.browser")
	public void setUpClass(String name) throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		webDriver.getWebdriver(name);

	}

	@Test(dataProvider = "features")
	public void feature(PickleEventWrapper eventwrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
		testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
	}

	@DataProvider // (parallel=true)
	public Object[][] features() {
		return testNGCucumberRunner.provideScenarios();
	}

	@Override
	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		webDriver.QuitBrowser();
		testNGCucumberRunner.finish();
	}






}
