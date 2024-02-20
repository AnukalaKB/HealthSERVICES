package com.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{
		prop =loadConfig();
		String BrowserName = prop.getProperty("browser");
		
		if(BrowserName.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if (BrowserName.equals("firefox")) {
			System.setProperty("webDriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
			driver = new FirefoxDriver();
		} else  if(BrowserName.equals("ie")) {
			System.setProperty("webDriver.ie.driver",  System.getProperty("user.dir") + "/src/main/resources/IEDriverServer");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println(BrowserName + "is not a valid browser");
		} driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  
		  return driver;
	}

	
	public Properties loadConfig() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
		prop.load(fis);
		return prop;
	}
}
