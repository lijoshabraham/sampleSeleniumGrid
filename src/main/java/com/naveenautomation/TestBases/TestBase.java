package com.naveenautomation.TestBases;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;

import com.naveenautomation.Listeners.WebDriverEvents;
import com.naveenautomation.browsers.Browsers;
import com.naveenautomation.env.Environment;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

    // ThreadLocal variable to store WebDriver instance
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static Browsers DEFAULT_BROWSER = Browsers.EDGE;
    private static Environment DEFAULT_ENV = Environment.PROD;
    public static Logger logger;
    private WebDriverEvents events;
    private EventFiringWebDriver eDriver;
    private static final boolean IS_GRID = true;

    @BeforeClass
    public void setUpLogger() {
        logger = Logger.getLogger(TestBase.class);
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
        logger.setLevel(Level.ALL);
    }

    // Test initialization
    public void initialization() throws MalformedURLException {
        if (IS_GRID) {
            setupGrid();
        } else {
            setBrowserForTesting();
        }
        
        driverManagement();
        logger.info("Loading Page in Browser");
        driverThreadLocal.get().get(DEFAULT_ENV.getEnvUrl());
    }

    // Managing WebDriver
    private void driverManagement() {
        driverThreadLocal.get().manage().window().maximize();
    }
    
    // Setting up Selenium Grid
    private void setupGrid() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);
        ChromeOptions options = new ChromeOptions();
        options.merge(cap);
        String hubURL = "http://192.168.2.29:4444/wd/hub"; 
        driverThreadLocal.set(new RemoteWebDriver(new URL(hubURL), cap));
    }

    // Setting up browser for local testing
    private void setBrowserForTesting() {
        // Retrieve value of "browser"
        String browserName = System.getProperty("browser");

        if (browserName == null) {
            DEFAULT_BROWSER = Browsers.EDGE;
        } else {
            DEFAULT_BROWSER = Browsers.valueOf(browserName.toUpperCase());
        }

        switch (DEFAULT_BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                logger.info("Launching Chrome Browser");
                driverThreadLocal.set(new ChromeDriver());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                logger.info("Launching Firefox Browser");
                driverThreadLocal.set(new FirefoxDriver());
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                logger.info("Launching Edge Browser");
                driverThreadLocal.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        eDriver = new EventFiringWebDriver(driverThreadLocal.get());
        events = new WebDriverEvents();
        eDriver.register(events);
        driverThreadLocal.set(eDriver);
    }


    @AfterMethod
    public void tearDown() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    // Method to retrieve WebDriver instance
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
