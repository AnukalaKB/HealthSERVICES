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
import com.codoid.products.exception.FilloException;
import com.testCases.HomePageTest;
import com.utils.Utils;
import com.pageObjects.HomePage;

public class HomePageTest extends Base {
	
public Actions actions;
	
	private Logger log = LogManager.getLogger(HomePageTest.class.getName());
	private HashMap<String, String> data;

	@BeforeTest
	public void initialize() throws IOException, FilloException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");
		data = new Utils().getTestData("TC3");
		actions = new Actions(driver);
		actions.navigateTo(prop.getProperty("url"));
	}
	
	@Test
	public void checkFooter() throws IOException {

		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.getFooter().isDisplayed());
		log.info("Footer is displayed");

	}
	
	@Test
	public void checkTitle() throws IOException {


		HomePage hp = new HomePage(driver);

		String actualTitle = hp.getTitle().getText();
		String expectedTitle = data.get("Title");

		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Successfully validated Title");

	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Driver is closed");
	}

}
