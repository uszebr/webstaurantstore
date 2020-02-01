package com.webstaurantstore.pages;

import com.webstaurantstore.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    protected static By addToCartButton = By.xpath("//input[@id='buyButton']");
    protected static By productHeader = By.xpath("//h1[@itemprop='Name']");

    protected ProductPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void addToCartButtonClick(){
        click(addToCartButton);
    }
    public void waitProductPageToLoad(){
        waitToVisibilityOfElement(addToCartButton);
        waitToVisibilityOfElement(productHeader);
    }
}
