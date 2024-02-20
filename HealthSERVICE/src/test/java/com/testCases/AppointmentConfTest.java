package com.testCases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.actions.Actions;
import com.base.Base;
import com.testCases.AppointmentConfTest;
import com.codoid.products.exception.FilloException;
import com.pageObjects.AppointmentConfPage;
import com.pageObjects.BookAppointmentPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.utils.Utils;

public class AppointmentConfTest extends Base{
	
public Actions actions;
	
	private Logger log = LogManager.getLogger(AppointmentConfTest.class.getName());
	private HashMap<String, String> data;

	@BeforeTest(description="Initialise the drivers")
	public void initialize() throws IOException, FilloException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");
		data = new Utils().getTestData("TC2");
		actions = new Actions(driver);
			
	}

	@Test(description="Verify the Appointment page")
	public void verifyAppointmentPage() {
		
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		BookAppointmentPage ba = new BookAppointmentPage(driver);
		AppointmentConfPage ac = new AppointmentConfPage(driver);
		
		actions.navigateTo(prop.getProperty("url"));
		actions.click(hp.getMenuBtn());
		actions.click(hp.getLogin());
		actions.enterText(lp.getUsername(), data.get("Username"));
		actions.enterText(lp.getPassword(), data.get("Password"));
		actions.click(lp.getLoginBtn());
		//add a wait function
		actions.selectFromDropdown(ba.getFacilityDD(), data.get("Facility"));
		actions.click(ba.getReadmission());
		actions.click(ba.getMedicaid());
		actions.enterText(ba.getVisitDate(), data.get("Visit Date"));
		actions.enterText(ba.getComment(), data.get("Comment"));
		actions.click(ba.getBookBtn());
		
		Assert.assertTrue(ac.getTitle().isDisplayed());

	}


	@AfterTest(description="Method to close the Driver")
	public void teardown() {
		driver.close();
		log.info("Driver is closed");
	}

}
