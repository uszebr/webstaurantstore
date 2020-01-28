package com.webstaurantstore.pages;

import com.webstaurantstore.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.webstaurantstore.base.Constants.*;

public class HomePage extends BasePage {
    protected static String homeURL = BASE_URL;

    protected static By searchTextBox = By.xpath("//input[@id='searchval']");
    protected static By searchButton = By.className("banner-search-btn");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(){
        System.out.println("Going to Home Page..");
        this.driver.get(homeURL);

    }
    public void fillUpSearchTextBox(String searchText){
        System.out.println("Filling Up Search Box..");
        type(searchText,searchTextBox);
    }
    public SearchResultPage clickSearchButton(){
        System.out.println("Clicking Search Button..");
        click(searchButton);
        return new SearchResultPage(this.driver);
    }

}
