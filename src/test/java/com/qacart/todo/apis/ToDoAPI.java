package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;
/**
 * API related actions using Rest assured
 * Singleton class for creating only 1 instance of ToDoAPI
 * Adding Todo using API call -> addToDo(User, String)
 * */
public class ToDoAPI {
    private static ToDoAPI toDoAPI;
    private ToDoAPI() {}

    public static ToDoAPI getInstance() {
        if (toDoAPI == null) {
            toDoAPI = new ToDoAPI();
        }
        return toDoAPI;
    }

    public Response addToDo(User user, String item) throws IOException {
        return given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType(ContentType.JSON)
                .body("{\"item\":\""+item+"\",\"isCompleted\":false}")
                .auth().oauth2(user.getAccessToken())
                .when()
                .post("/api/v1/tasks")
                .then()
                .extract().response();
    }
}
