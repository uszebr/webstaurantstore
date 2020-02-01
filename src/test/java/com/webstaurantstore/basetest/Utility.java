package com.webstaurantstore.basetest;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.DataProvider;

public class Utility extends BaseTest {//Utilities from Shishin FrameWork - https://github.com/dimashyshkin/advanced-selenium-webdriver/blob/master/PART%206/Lecture%2043/advanced-selenium-webdriver/src/main/java/com/herokuapp/theinternet/base/TestUtilities.java









        @DataProvider(name="files")
        protected static Object[][] files() {
            return new Object[][] {
                    {1,"index.html"},
                    {2,"logo.png"},
                    {3,"text.txt"}
            };
        }

        /** Take screenshot */
       /* protected void takeScreenshot(String fileName) {// Screenshots need to be implemented in BaseTest Class!!
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir")
                    + File.separator + "test-output"
                    + File.separator + "screenshots"
                    + File.separator + getTodaysDate()
                    + File.separator + testSuiteName
                    + File.separator + testName
                    + File.separator + testMethodName
                    + File.separator + getSystemTime()
                    + " " + fileName + ".png";
            try {
                FileUtils.copyFile(scrFile, new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        /** Todays date in yyyyMMdd format */
        private static String getTodaysDate() {
            return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
        }

        /** Current time in HHmmssSSS *///My TimeStampSolution is better
        private String getSystemTime() {
            return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
        }

        /** Get logs from browser console */
        protected List<LogEntry> getBrowserLogs() {// From Shiskin FrameWork
            LogEntries log = driver.manage().logs().get("browser");
            List<LogEntry> logList = log.getAll();
            return logList;
        }



}
