package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObject.BaseClass;

public class TestResultListener extends BaseClass implements ITestListener {
    UtilityFunctions utilityFunctions=new UtilityFunctions(driver);
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    utilityFunctions.takeScreenShot((WebDriver) iTestResult.getTestContext().getAttribute("driver"),iTestResult.getMethod().getMethodName()+"_Passed_");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
