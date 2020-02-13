package com.webstaurantstore.basetest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {


    public ExtentHtmlReporter extentHtmlReporter;
    public ExtentReports extentReport;
    public ExtentTest extentTest;//this is logger


    @Override
    public void onStart(ITestContext iTestContext) {
        //refactored to extentTestManager and ExtentManager
        /*String timeStamp = TimeStamp.getTimeStamp();//get timestamp for report file name;
        String reportName = "Report_" + timeStamp + ".html";
        extentHtmlReporter = new ExtentHtmlReporter("test-reports/" + reportName);// where to store html reports;
        extentHtmlReporter.loadXMLConfig("configiguration/extent-config.xml");

        extentReport = new ExtentReports();// creating report obj
        extentReport.attachReporter(extentHtmlReporter);// adding reporter

        extentReport.setSystemInfo("Host Name", "BokaratTesting");//adding report properties))
        extentReport.setSystemInfo("Environment", "Automation Testing");
        extentReport.setSystemInfo("User Name", "Viktor Di");

        //next config possible to set in extent-config.xml
        extentHtmlReporter.config().setReportName("Test Automation Report"); // reports name
        extentHtmlReporter.config().setDocumentTitle("Bokarat Test Project"); // reports title  `
        extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the tests chart
        extentHtmlReporter.config().setTheme(Theme.DARK);


        System.out.println("==listener-onStart== " + iTestContext.getCurrentXmlTest().getName());*/
        System.out.println("-=- Test Suite " + iTestContext.getName() + " started -=-");
        // iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(("-=- Running test method " + iTestResult.getMethod().getMethodName() + "..."));
        ExtentReport.startTest(iTestResult.getMethod().getMethodName());
    }


    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(("-=- Test Suite " + iTestContext.getName() + " ending -=-"));
        ExtentReport.endTest();
        ExtentManager.getInstance().flush();

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        // just displaying message from listener
     /*   extentTest = extentReport.createTest(iTestResult.getName());
        extentTest.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.BLUE));*/// set test status and label color

        Object testClass = iTestResult.getInstance();
         ((BaseTest) testClass).log.info("-=- Executed " + iTestResult.getMethod().getMethodName() + " test Success!");//this is logging to log4j2

        ExtentReport.getTest().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.BLUE));

        ExtentReport.getTest().log(Status.INFO, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
      //  ExtentReport.getTest().fail(iTestResult.getThrowable().getMessage());//getting message from Assert errorMessage, this also working, but refactored to
        ExtentReport.getTest().log(Status.ERROR, iTestResult.getThrowable().getMessage());//getting message from Assert errorMessage
        Object testClass = iTestResult.getInstance();
        String fileNameDest = ((BaseTest) testClass).takeScreenshot(iTestResult);//Where Screenshot stored from BaseTest Class
        String rootPath = System.getProperty("user.dir");// project path
        try {

            ExtentReport.getTest().addScreenCaptureFromPath(rootPath + "/" + fileNameDest);// directory screnshots// <-formating in the BaseTest class//May be refactor to separete folder for each test, with TimeStamp
            ExtentReport.getTest().log(Status.ERROR, "Screenshot in attach.");
        } catch (Exception e) {

            ExtentReport.getTest().log(Status.ERROR, "Can not attach screenshot :" + rootPath + "/" + fileNameDest);
        }
        //next implemintation need to be refactored - doesnt work af after refactoring manager pattern extent report
        // ExtentTestManager.getTest().fail(iTestResult.getTestContext().getFailedTests().getAllMethods().toString());
/*
        System.out.println("-=- Test execution " + iTestResult.getMethod().getMethodName() + " Failed...");
        ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED));

        ExtentTestManager.getTest().fail(iTestResult.getThrowable());


        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();//get webdriver instance - allow to keep it not static


        String methodName = "";
        String className = "";
        if (iTestResult != null) {//if have itestresult parameter - get method name
            methodName = iTestResult.getMethod().getMethodName().toString();//test method name
            className = iTestResult.getTestClass().getRealClass().getSimpleName();//this gives short(no package) class name
        }
        //taking screenShoot
        String fileNameDest = "";
        try {

            File SrcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

            fileNameDest = "screenshoots/" + className + "_" + methodName + "-" + TimeStamp.getTimeStamp() + ".png";

            File DestFile = new File(fileNameDest);
            //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);

        } catch (Exception e) {
        }
        String rootPath = System.getProperty("user.dir");
        try {
            extentTest.fail("Screenshot in attach." + extentTest.addScreenCaptureFromPath(rootPath + "/" + fileNameDest));
        } catch (IOException e) {
            extentTest.fail("Can not attach screenshot");
        }

*/

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        /*extentTest = extentReport.createTest(iTestResult.getName());
        extentTest.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREY));
*/
        System.out.println("-=- Test " + iTestResult.getMethod().getMethodName() + " Skipped...");
        ExtentReport.getTest().log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREY));

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("-=- Test failed but within percentage % " + iTestResult.getMethod().getMethodName());
    }


}
