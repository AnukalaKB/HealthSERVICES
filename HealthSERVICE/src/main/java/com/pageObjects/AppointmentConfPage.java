package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.Base;

public class AppointmentConfPage extends Base{
	
	public AppointmentConfPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// All objects should be defined here
	private By title = By.cssSelector("h2");
	
	
	// All methods should be defined here
	public WebElement getTitle() {
		return driver.findElement(title);
	}

}
