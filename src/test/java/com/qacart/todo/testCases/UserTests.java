package com.qacart.todo.testCases;

import com.github.javafaker.Faker;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.ToDoPage;
import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class UserTests extends BaseTest {
    @Test(description = "Should be able to signup")
    public void shouldBeAbleToRegisterToApplication() throws IOException {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().register(driver.get(), user);
        boolean isWelcomeDisplayed = ToDoPage.getInstance().isWelcomeMessageDisplayed(driver.get());
        Assert.assertTrue(isWelcomeDisplayed);
    }
}
