package com.webstaurantstore.pages;

import com.webstaurantstore.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.webstaurantstore.base.Constants.*;

public class HomePage extends BasePage {
    protected static String homeURL = BASE_URL;

    protected static By searchTextBox = By.xpath("//input[@id='searchval']");
    protected static By searchButton = By.className("banner-search-btn");


    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openHomePage(){
        log.info("Going to Home Page..");
        this.driver.get(homeURL);

    }
    public void fillUpSearchTextBox(String searchText){
        log.info("Filling Up Search Box..");
        type(searchText,searchTextBox);
    }
    public SearchResultPage clickSearchButton(){
        log.info("Clicking Search Button..");
        click(searchButton);
        return new SearchResultPage(this.driver, log);
    }

}
