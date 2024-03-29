package com.testCases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actions.Actions;
import com.base.Base;
import com.testCases.LoginPageTest;
import com.codoid.products.exception.FilloException;
import com.pageObjects.BookAppointmentPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.utils.Utils;

public class LoginPageTest extends Base {
	
public Actions actions;
	
	private Logger log = LogManager.getLogger(LoginPageTest.class.getName());
	private HashMap<String, String> data;

	@BeforeTest(description="Initialise the drivers")
	public void initialize() throws IOException, FilloException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");
		data = new Utils().getTestData("TC1");
		actions = new Actions(driver);
		
	}

	@Test(description="Verify the Login Functionality")
	public void performLogin() {
		
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		BookAppointmentPage ba = new BookAppointmentPage(driver);
		
		actions.navigateTo(prop.getProperty("url"));
		actions.click(hp.getMenuBtn());
		actions.click(hp.getLogin());
		actions.enterText(lp.getUsername(), data.get("Username"));
		actions.enterText(lp.getPassword(), data.get("Password"));
		actions.click(lp.getLoginBtn());
		Assert.assertTrue(ba.getTitle().isDisplayed());
		log.info("Successfully Logged In");

	}

	@DataProvider
	public Object[][] getData() {
		// Each row is set of test data for a test case
		// Each column is a value in a set of test data
		Object[][] data = new Object[2][2];

		// First set of test data
		data[0][0] = "John Doe";
		data[0][1] = "ThisIsNotAPassword";

		// Second set of test data
		data[1][0] = "testuser2";
		data[1][1] = "testpwd2";

		return data;
	}

	@AfterTest(description="Method to close the Driver")
	public void teardown() {
		driver.close();
		log.info("Driver is closed");
	}

}
