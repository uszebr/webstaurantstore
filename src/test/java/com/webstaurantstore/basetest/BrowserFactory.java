package com.webstaurantstore.basetest;


import com.webstaurantstore.base.Constants;///ALL CONTSTANTS are stored here...
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

 public class BrowserFactory {

    // private ThreadLocal<WebDriver> webDriverContair = new ThreadLocal<>();// Idea to do it really multi thread safe
    private ThreadLocal<WebDriver> driverContainer = new ThreadLocal<WebDriver>();
    Logger log;
    String browser;

    public BrowserFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();//for Human error
        this.log = log;

    }

    /////


    public  WebDriver setDriver() {

        switch (this.browser) {//Add more drivers here:)

            case "chrome":
                log.info("Setting Up Chrome Browser");
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driverContainer.set(new ChromeDriver());
                break;
            case "firefox":
                log.info("Setting Up Firefox Browser");
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                driverContainer.set(new FirefoxDriver());
                break;

            default:
                log.info("Webdriver not set properly, starting default driver. Unrecognized parameter: " + browser);
                System.setProperty("webdriver.driver.chromedriver", "resources/chromedriver.exe");

                driverContainer.set(new ChromeDriver());
                break;
        }



        return driverContainer.get();
    }
     public WebDriver createDriverGrid() {
         String hubUrl = "http://192.168.0.9:4444/wd/hub";//url from Selenium Grid Hub - where to register node
         DesiredCapabilities capabilities = new DesiredCapabilities();
         log.info("Starting " + browser + " on grid");

         // Creating driver
         switch (browser) {
             case "chrome":
                 capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
                 break;

             case "firefox":
                 capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
                 break;
         }

         try {
             driverContainer.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

         return driverContainer.get();
     }




}
