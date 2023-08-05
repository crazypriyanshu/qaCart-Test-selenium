package com.qacart.todo.testCases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.NewToDo;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.ToDoPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class ToDoTest extends BaseTest {
    @Test(description = "Should be able to add a To Do")
    public void shouldBeAbleToAddAToDo() throws IOException {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsingAPI(driver.get(), user);
        ToDoPage.getInstance().clickOnPlusButton(driver.get());
        NewToDo.getInstance().addToDo(driver.get(), "Learn Selenium");
        String generatedText = ToDoPage.getInstance().getToDoText(driver.get());
        Assert.assertEquals(generatedText, "Learn Selenium");
    }

    @Test(description = "should be able to delete an added ToDo")
    public void shouldBeAbleToDeleteAToDo() throws IOException {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsingAPI(driver.get(), user);
        NewToDo.getInstance().addToDoUsingAPI(user, "Learn Selenium");
        ToDoPage.getInstance().load(driver.get());
        ToDoPage.getInstance().deleteTodo(driver.get());
        boolean isDeleted = ToDoPage.getInstance().isNoToDoDisplayed(driver.get());
        Assert.assertTrue(isDeleted);
    }
}
