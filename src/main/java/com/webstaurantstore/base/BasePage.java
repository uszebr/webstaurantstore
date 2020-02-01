package com.webstaurantstore.base;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.Logger;

import static com.webstaurantstore.base.Constants.*;///ALL CONTSTANTS are stored here...

abstract public class BasePage {
    // public static final By LOGO = By.xpath("");


    protected WebDriver driver;
    protected Logger log;


    protected BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;


    }


    protected void click(By element) {
        find(element).click();
    }

    protected void type(String keys, By element) {
        find(element).sendKeys(keys);
    }

    protected WebElement find(By element) {
        return driver.findElement(element);
    }

    protected void waitToVisibilityOfElement(By element, Integer... seconds) {
        int attempts = 3;// to attempts to check visibility of the element;
        while (attempts > 0) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(element), (seconds.length > 0 ? seconds[0] : DEFAULT_TIMEOUT));
                break;
            } catch (StaleElementReferenceException e) {
                // e.printStackTrace();
            }
            attempts--;
        }

    }


    protected void waitToUnvisibilityOfElement(By element) {
        int attempts = 3;// to attempts to check visibility of the element;
        while (attempts > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(this.driver, DEFAULT_TIMEOUT);

                wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

                // waitFor((ExpectedConditions.invisibilityOfElementLocated(element)), (seconds.length > 0 ? seconds[0] : DEFAULT_TIMEOUT));
                break;
            } catch (StaleElementReferenceException e) {
                // e.printStackTrace();
            }
            attempts--;
        }
    }

    protected void waitFor(ExpectedCondition<WebElement> condition, Integer... timeout) { // wait for certain condition withIn timeout time
        // if no parameter, using default timeout from Constans
        WebDriverWait wait = new WebDriverWait(this.driver, (timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT));
        wait.until(condition);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getText(By element) {
        return find(element).getText();
    }

    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

    public  void pause(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            log.info("Exception. Can not wait for:" + timeInSeconds);
        }
    }


}