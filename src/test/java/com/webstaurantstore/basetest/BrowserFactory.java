package com.webstaurantstore.basetest;



import com.webstaurantstore.base.Constants;///ALL CONTSTANTS are stored here...
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

abstract public class BrowserFactory {


    /////






        public static WebDriver setDriver(String browser) {
            WebDriver driver = null;
            switch (browser) {

                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

                    driver = new ChromeDriver();
                    break;

                default:

                    System.setProperty("webdriver.driver.chromedriver", "resources/chromedriver.exe");

                    driver = new ChromeDriver();
                    break;
            }
            //Browser Setup
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            return driver;
        }

}
