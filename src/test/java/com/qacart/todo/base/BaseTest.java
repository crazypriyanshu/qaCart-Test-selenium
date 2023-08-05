package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Base class for all test cases
 * Creates Webdriver object and assigning a thread to it
 * Initializes the WebDriver -> setup() -> In BEFORE block, so runs before every test case who extends this class
 * tearDown(ITestResult) -> In after block, so runs AFTER every test case who extends this class -> gets test case name
 *   -- and creates destination file using destFile and stores the screenshot after every test run
 *   Add attached screenshot to Allure by copying screenshot from dest file path -> takeScreenshot(File)
 * */
public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @BeforeMethod
    public void setup() {
        WebDriver driver = new DriverFactory().initializeDriver();
        this.driver.set(driver);

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testCaseName = result.getMethod().getMethodName();
        File destFile = new File("target" + File.separator + "screenshots"+File.separator+testCaseName+".png");
        takeScreenshot(destFile);
        driver.get().quit();

    }

    public void takeScreenshot(File destFile) {
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destFile);
            InputStream is = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", is);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }
}
