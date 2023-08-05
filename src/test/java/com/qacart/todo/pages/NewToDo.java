package com.qacart.todo.pages;

import com.qacart.todo.apis.ToDoAPI;
import com.qacart.todo.models.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
/**
 * Create Page Object for adding a new ToDo feature in the tool
 * Singleton class for creating only 1 instance of New Todo page
 * Adding Todo using UI -> addToDo(Webdriver, Text to add)
 * Adding Todo using API -> addToDoUsingAPI(User, Text to add)
 * */

public class NewToDo {
    private static NewToDo newToDo;
    private final By newTodoInput = By.cssSelector("[data-testid=\"new-todo\"]");

    private final By newToDoSubmit = By.cssSelector("[data-testid=\"submit-newTask\"]");

    // Constructors
    private NewToDo() {}

    public static NewToDo getInstance() {
        if (newToDo == null) {
            newToDo = new NewToDo();
        }
        return newToDo;
    }

    // Methods, steps
    @Step("add to do using UI")
    public void addToDo(WebDriver driver, String item) {
        driver.findElement(newTodoInput).sendKeys(item);
        driver.findElement(newToDoSubmit).click();
    }

    @Step("add to do using API")
    public void addToDoUsingAPI(User user, String item) throws IOException {
        ToDoAPI.getInstance().addToDo(user, item);

    }
}
