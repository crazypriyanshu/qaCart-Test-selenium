package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.*;

/**
 * API related actions using Rest assured for User API
 * Singleton class for creating only 1 instance of UserAPI
 * Registers a user using API -> register(User) -> Hitting /api/v1/users/register
 * */
public class UserAPI {

    private static UserAPI userAPI;

    public static UserAPI getInstance() {
        if(userAPI == null) {
            userAPI = new UserAPI();
        }
        return userAPI;
    }

    private UserAPI() {}

    public Response register(User user) throws IOException {
        return given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post("/api/v1/users/register")
        .then()
                .extract().response();
    }
}
