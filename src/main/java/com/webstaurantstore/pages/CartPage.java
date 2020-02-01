package com.webstaurantstore.pages;

import com.webstaurantstore.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.webstaurantstore.base.Constants.*;

public class CartPage extends BasePage {
    protected static String cartPageURL = BASE_URL + "/viewcart.cfm";

    protected static By emptyCartButton = By.className("emptyCartButton");
    protected static By emptyCartConfirmButton = By.xpath("//button[contains(text(),'Empty Cart')]");



    public CartPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    public void openCartPage(){
        log.info("Open Cart Page..");
        driver.get(cartPageURL);
    }

    public void emptyCartButtonClick(){
        log.info("Clicking EMPTY Cart Button..");
        this.pause(1);
        click(emptyCartButton);
    }

    public void emtyCartConfirmButtonClick(){//confirm empty cart button in Modal popup dialog

        log.info("Clicking EMPTY Cart CONFIRM Button(modal dialog)..");
        pause(1);
        click(emptyCartConfirmButton);
    }
    public boolean checkCartIsEmpty(){
        log.info("Checking is Cart empty?..");
        pause(1);
        try {//if element not present - exception
            if ( this.driver.findElement(emptyCartButton).isDisplayed()) {
                log.info("Cart is NOT empty..");
               return false;
            } else {
                log.info("Cart is EMPTY..");
                return true;
            }
        } catch (Exception e) {
            log.info("Cart is NOT empty..");
            return false;
        }
    }


}
