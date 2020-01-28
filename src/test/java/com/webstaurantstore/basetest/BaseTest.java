package com.webstaurantstore.basetest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;

public class BaseTest {


    /////////////




        public WebDriver driver;


        public WebDriver getDriver() {
            return driver;
        }




        @Parameters({"browser"})
        @BeforeMethod(alwaysRun = true)
        public void setUpMethod(String browser) {
            System.out.println("==Before==");


            driver = BrowserFactory.setDriver(browser);


        }

        @AfterMethod(alwaysRun = true)
        public void tearDown() {

            if (driver != null) {
              //  driver.quit(); // commented to test
            }
            System.out.println("==After==");

        }
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Can not thread.sleep, number of seconds is:" + seconds);
            e.printStackTrace();
        }
    }
    public String getUrl(){
        return this.driver.getCurrentUrl();
    }






}
