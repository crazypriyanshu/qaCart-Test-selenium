package com.qacart.todo.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * If browser selected or provided then run tests on the specified browser, or else run on chrome by default
 * This DriverFactory should create driver based on the input provided in CLI
 * Eg.
 * mvn clean test -Dbrowser=EDGE
 * mvn clean test -Dbrowser=FIREFOX
 * adding comment
 *
 */
public class DriverFactory {
    public WebDriver initializeDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser", "CHROME");
        switch (browser) {
            case "CHROME" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
            case "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "SAFARI" -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
            case "EDGE" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> {
                throw new RuntimeException("Browser is not supported");
            }


        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
