package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getName() + "-------> SUCCESS");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(iTestResult.getName() + "-------> FAILED");
        WebDriver driver = ((BaseTest) iTestResult.getInstance()).getDriver();
        String fileName = "SkyScreaperCenterTest";
        try {
            synchronized (driver) {
                fileName += "_" + new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/" + fileName + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(iTestResult.getName() + "-------> SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }
}
