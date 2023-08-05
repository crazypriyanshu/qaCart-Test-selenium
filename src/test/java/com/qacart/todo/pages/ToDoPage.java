package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Create Page Object for ToDo page for the tool
 * Singleton class for creating only 1 instance of ToDoPage
 * Adding isWelcomeMessageDisplayed using UI -> isWelcomeMessageDisplayed(WebDriver) -> returns boolean
 * Loading the ToDo page page using UI -> load(WebDriver)
 * Click On Plus Button using UI -> clickOnPlusButton(WebDriver)
 * Gets the text message added in ToDo -> getToDoText(WebDriver) -> returns String
 * Clicks on delete button to delete ToDO -> deleteTodo(WebDriver)
 * Verifies if NoToDo is left after deleting->  isNoToDoDisplayed(WebDriver) -> returns boolean
 * */
public class ToDoPage {
    private static ToDoPage toDoPage;
    private final By welcomeMessage = By.cssSelector("[data-testid=\"welcome\"]");
    private final By addButton = By.cssSelector("[data-testid=\"add\"]");

    private final By toDoText = By.cssSelector("[data-testid=\"todo-text\"]");

    private final By deleteButton = By.cssSelector("[data-testid=\"delete\"]");

    private final By noToDo = By.cssSelector("[data-testid=\"no-todos\"]");

    // Constructor
    private ToDoPage() {}

    public static ToDoPage getInstance() {
        if(toDoPage ==null) {
            toDoPage = new ToDoPage();
        }
        return toDoPage;
    }

    // Methods

    @Step("Visiting todo page")
    public void load(WebDriver driver) throws IOException {
        driver.get(ConfigUtils.getInstance().getBaseUrl()+"/todo");
    }

    @Step("Verify if WelcomeMessage is Displayed")
    public boolean isWelcomeMessageDisplayed(WebDriver driver) {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    @Step("Click On Plus button")
    public void clickOnPlusButton(WebDriver driver) {
        driver.findElement(addButton).click();

    }

    @Step("Get the Todo text")

    public String getToDoText(WebDriver driver) {
        return driver.findElement(toDoText).getText();
    }

    @Step("Deleting Todo")
    public void deleteTodo(WebDriver driver) {
        driver.findElement(deleteButton).click();
    }
    @Step("Verify if no todo is Displayed after deleting todo")
    public boolean isNoToDoDisplayed(WebDriver driver) {
        return driver.findElement(noToDo).isDisplayed();
    }

}
