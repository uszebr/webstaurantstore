package com.webstaurantstore.tests;

import com.webstaurantstore.basetest.BaseTest;
import com.webstaurantstore.pages.CartPage;
import com.webstaurantstore.pages.HomePage;
import com.webstaurantstore.pages.ProductPage;
import com.webstaurantstore.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchAddEmptyCardTest extends BaseTest {

    @Test(groups = {"endToEnd", "execute"})
    public void firstTest() {
        // I am big fan of independent tests, and believe that tests need to be maximum independent
        // ,so I implemented all assignment in one End to End test -with Soft Assertion
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.fillUpSearchTextBox("stainless work table");
        SearchResultPage searchResultPage = homePage.clickSearchButton();


        //may be add wait for visibility of H1 class="page-header search--title"
        // log.info( searchResultPage.checkProductTitlesOnCurrentSearchPage("stainless work table","table"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true,searchResultPage.checkProductTitlesOnALLSearchPages("table"));

        ProductPage productPage = searchResultPage.goLastProductOnCurrentPage();
        productPage.waitProductPageToLoad();

        productPage.addToCartButtonClick();


        CartPage cartPage = new CartPage(this.driver, log);
        cartPage.openCartPage();

        softAssert.assertFalse(cartPage.checkCartIsEmpty());//one product should be in the cart
        cartPage.emptyCartButtonClick();

        cartPage.emtyCartConfirmButtonClick();

        softAssert.assertEquals(true,cartPage.checkCartIsEmpty());// cart should be empty


        softAssert.assertAll();
    }
    @Test(groups = {"debug", "NoExecute"})
    public void positiveTest() {
        log.info("==Just 100% positive test==");


        Assert.assertTrue(true);
    }


    @Test(groups = {"debug", "NoExecute"})
    public void debugTest() {
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.fillUpSearchTextBox("stainless work table");
        SearchResultPage searchResultPage = homePage.clickSearchButton();


        //may be add wait for visibility of H1 class="page-header search--title"
        // log.info( searchResultPage.checkProductTitlesOnCurrentSearchPage("stainless work table","table"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true,searchResultPage.checkProductTitlesOnALLSearchPages("table"));

        ProductPage productPage = searchResultPage.goLastProductOnCurrentPage();
        productPage.addToCartButtonClick();


        CartPage cartPage = new CartPage(this.driver, this.log);
        cartPage.openCartPage();

        softAssert.assertFalse(cartPage.checkCartIsEmpty());//one product should be in the cart
        cartPage.emptyCartButtonClick();

        cartPage.emtyCartConfirmButtonClick();

        softAssert.assertEquals(true,cartPage.checkCartIsEmpty());// cart should be empty


        softAssert.assertAll();
    }

}
