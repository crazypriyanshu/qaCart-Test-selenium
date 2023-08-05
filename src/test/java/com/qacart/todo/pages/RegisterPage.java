package com.qacart.todo.pages;

import com.qacart.todo.apis.UserAPI;
import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Create Page Object for Register page for the tool
 * Singleton class for creating only 1 instance of Register page
 * Adding Register using UI -> register(WebDriver, User)
 * Loading the signup page using UI -> load(WebDriver driver)
 * Registering new user using API -> registerUsingAPI(WebDriver, User)
 * */
public class RegisterPage {
    private static RegisterPage registerPage;

    // Elements
    private final By firstName = By.cssSelector("[data-testid=\"first-name\"]");
    private final By lastName = By.cssSelector("[data-testid=\"last-name\"]");

    private final By email = By.cssSelector("[data-testid=\"email\"]");

    private final By password = By.cssSelector("[data-testid=\"password\"]");
    private final By confirmPassword = By.cssSelector("[data-testid=\"confirm-password\"]");
    private final By submitButton = By.cssSelector("[data-testid=\"submit\"]");




    // Constructor
    private RegisterPage() {}

    // Singleton
    public static RegisterPage getInstance() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }

    // Methods

    @Step("Register using UI")
    public void register(WebDriver driver, User user) {
        driver.findElement(firstName).sendKeys(user.getFirstName());
        driver.findElement(lastName).sendKeys(user.getLastName());
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getPassword());
        driver.findElement(confirmPassword).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }

    @Step("Visit a signup page")
    public void load(WebDriver driver) throws IOException {
        driver.get(ConfigUtils.getInstance().getBaseUrl()+"/signup");
    }

    @Step("Register using API")
    public void registerUsingAPI(WebDriver driver, User user) throws IOException {
        Response response = UserAPI.getInstance().register(user);
        String accessToken = response.path("access_token");
        String userId = response.path("userID");
        String firstName = response.path("firstName");

        user.setAccessToken(accessToken);
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        Cookie userIdCookie = new Cookie("userID", userId);
        Cookie firstNameCookie = new Cookie("firstName", firstName);


        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIdCookie);
        driver.manage().addCookie(firstNameCookie);
        RegisterPage.getInstance().load(driver);

    }
}
