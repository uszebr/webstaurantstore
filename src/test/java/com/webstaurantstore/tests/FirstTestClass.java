package com.webstaurantstore.tests;

import com.webstaurantstore.base.Constants;
import com.webstaurantstore.basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTestClass extends BaseTest {//this class to try DataProviders and Reports

    @Test(groups = {"debug", "NoExecute"})
    public void positiveTest() {
        driver.get(Constants.BASE_URL);
        log.info("==Just 100% positive test==");
        Assert.assertTrue(true,"Error Message From positiveTest method");
    }

    @Test(groups = {"debug", "NoExecute"})
    public void negativeTest() {
        log.info("==Just 100% negative test==");
        driver.get(Constants.BASE_URL);

        Assert.assertTrue(false,"Error Message From negativeTest 100% negative test==");
    }
}
