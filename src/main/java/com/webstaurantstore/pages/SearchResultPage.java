package com.webstaurantstore.pages;

import com.webstaurantstore.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {
    private int pageNumber;// what page on Search Results we are currently on;



    protected static By productTitles = By.xpath("//div[@class='ag-item gtm-product ']//a[@class='description']");//Xpath for links on each product from Search Results
    protected static By nextPageButton = By.xpath("//a[@rel='next']");//Xpath button to the next page of search results, if does not exists - no next page;
    protected static By addToCartButton = By.xpath("//input[@name='addToCartButton']");//ADD to Cart Button element;
    protected static By productTitlesLink = By.xpath("//a[@class='description']");
    protected static By addToCartButtonModalDialog = By.xpath("//button[@name='addToCartButton']");




    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.pageNumber = 1;// when page constructed page number is 1::: not 0 - it should match visible page number
    }

    public boolean checkProductTitlesOnCurrentSearchPage(String matchingText) {// true if all items on current page contains matching text;
        boolean resultCurrentPage = true;//if search criteria contains no items, - list empty - result =true;
        matchingText = matchingText.trim().toLowerCase();//this is text we are looking in each product title
        // removing spaces and converting to lower case
        List<WebElement> listOfProductElements = getProductsListOnCurrentSearchPage();
        List<String> listOfProductTitles = new ArrayList<>();// list of titles(link texts) for each product on Search Page
        System.out.println("Getting Titles from Elements..");
        for (WebElement element : listOfProductElements// converting elements list to titles list, if check every product page is not mandatory
            // can refactor to get list of titles straight without getting list of elements;
        ) {
            listOfProductTitles.add(element.getText());
        }
        System.out.println("Checking if Titles contain text..");
        for (String text : listOfProductTitles
        ) {
            if (!text.toLowerCase().contains(matchingText)) {
                System.out.println(text + " :This product title does not contains text: " + matchingText);
                resultCurrentPage = false;
            }
        }
        return resultCurrentPage;
    }

    public List<WebElement> getProductsListOnCurrentSearchPage() {// I need it in case we want to click every element and check each product page
        System.out.println("Getting Elements from Search Page..");
        return driver.findElements(productTitles);
    }

    public boolean nextPageSearchResults() {// false if no nextPageButton on the search results page;
        System.out.println("Trying to load next page..");
        List<WebElement> list = this.driver.findElements(nextPageButton);// it is list because there are two links "next page"
        if (list.size() > 0) {
            list.get(0).click();
            this.pageNumber++;// adding page number
            System.out.println("Clicked to the next page! Current Page is: " + this.pageNumber);
            BasePage.pause(1);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkProductTitlesOnALLSearchPages(String matchingText) {// true if all items on All pages contain matching text;
        boolean resultAllPages = true;

        do {
            System.out.println("Checking Search Results, Page: " + pageNumber);
            //check results on current page;
            if (!checkProductTitlesOnCurrentSearchPage(matchingText)) {
                resultAllPages = false;
                System.out.println("!!!Found Not matching products on page number: " + pageNumber);
            }
            

        } while (nextPageSearchResults());//loop to the next page until False to load next page;
        return resultAllPages;
    }
    public void addToCartLastProductOnCurrentPage(){// refactored to click on add to cart on ProductPage
        System.out.println("Clicking AddToCart button - last element on current page..");
        List<WebElement> productsAddToCardList = this.driver.findElements(addToCartButton);// getting all "Add to Cart" buttons to the list
        if (productsAddToCardList.size()>0) {// if list not empty - clicking
            productsAddToCardList.get(productsAddToCardList.size()-1).click();// clicking last element in the list

            BasePage.pause(1);
            addToCartButtonModalDialogClick();// confirm adding to the cart on modal dialog if it is present


        }
    }
    public ProductPage goLastProductOnCurrentPage(){// clicking to last product on current page link
        System.out.println("Clicking link title on last element on current page..");
        List<WebElement> productsTitlesList = this.driver.findElements(productTitlesLink);//getting all products titles links to the list
        if (productsTitlesList.size()>0) {// if list not empty - clicking
            productsTitlesList.get(productsTitlesList.size()-1).click();// clicking last element in the list and go to product page
        }
        return new ProductPage(this.driver);
    }

    protected void addToCartButtonModalDialogClick(){
        if(getElement(addToCartButtonModalDialog).isEnabled()){
            getElement(addToCartButtonModalDialog).click();
        }
    }

    public int getPageNumber(){
        return this.pageNumber;
    }

}
