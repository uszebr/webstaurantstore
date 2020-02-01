package com.webstaurantstore.basetest;


import com.webstaurantstore.base.Constants;
import com.webstaurantstore.basetest.utility.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

abstract public class BaseTest {


    /////////////


    public Logger log;
    public WebDriver driver;


    public WebDriver getDriver() {
        return driver;
    }


    @BeforeSuite
    public void setUpSuite(ITestContext iTestContext){
        String testName = iTestContext.getName();
        System.setProperty("log4j.configurationFile","resources/log4j2.xml");// xml best way to configure log4j2.. properties was working good only in 1st version

        this.log = LogManager.getLogger(testName);


    }


    @Parameters({ "browser", "environment" })
    @BeforeMethod(alwaysRun = true)
    public void setUpMethod(@Optional("chrome") String browser, @Optional("local") String environment,  Method method, ITestContext iTestContext) {
        log.info("==BeforeMethod==");


        BrowserFactory browserFactory = new BrowserFactory(browser,log);

        if (environment.equals("grid")) {
            driver = browserFactory.createDriverGrid();
        } else {
            driver = browserFactory.setDriver();
        }
        //Setting Up Browser
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);




    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit(); // commented to  debug
        }
        log.info("==After Method==");

    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.info("Can not thread.sleep, number of seconds is:" + seconds);
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return this.driver.getCurrentUrl();
    }

    public String takeScreenshot(ITestResult... iTestResults) {//take screenshot - returns fileName with path;
        String methodName = "DEFmethod";
        String className = "DEFclass";
        if (iTestResults != null) {//if have itestresult parameter - get method name
            methodName = iTestResults[0].getMethod().getMethodName().toString();//test method name
            className = iTestResults[0].getTestClass().getRealClass().getSimpleName();//this gives short(no package) class name
        }
        //taking screenShoot
        String fileNameDest = "";
        try {
            //Call getScreenshotAs method to create image file
            File SrcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
            fileNameDest = "screenshoots/" + className + "_" + methodName + "-" + TimeStamp.getTimeStamp() + ".png";
            File DestFile = new File(fileNameDest);

            //Copy file at destination


            FileUtils.copyFile(SrcFile, DestFile);

        } catch (Exception e) {
            log.error("Can not Write Srceeshot");
        }
        return fileNameDest;
    }


}
