package com.webstaurantstore.pages;

import com.webstaurantstore.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.webstaurantstore.base.Constants.*;

public class CartPage extends BasePage {
    protected static String cartPageURL = BASE_URL + "/viewcart.cfm";

    protected static By emptyCartButton = By.className("emptyCartButton");
    protected static By emptyCartConfirmButton = By.xpath("//button[contains(text(),'Empty Cart')]");



    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void openCartPage(){
        System.out.println("Open Cart Page..");
        driver.get(cartPageURL);
    }

    public void emptyCartButtonClick(){
        System.out.println("Clicking EMPTY Cart Button..");
        BasePage.pause(1);
        click(emptyCartButton);
    }

    public void emtyCartConfirmButtonClick(){//confirm empty cart button in Modal popup dialog

        System.out.println("Clicking EMPTY Cart CONFIRM Button(modal dialog)..");
        pause(1);
        click(emptyCartConfirmButton);
    }
    public boolean checkCartIsEmpty(){
        System.out.println("Checking is Cart empty?..");
        pause(1);
        try {//if element not present - exception
            if ( this.driver.findElement(emptyCartButton).isDisplayed()) {
                System.out.println("Cart is NOT empty..");
               return false;
            } else {
                System.out.println("Cart is EMPTY..");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Cart is NOT empty..");
            return false;
        }
    }


}
