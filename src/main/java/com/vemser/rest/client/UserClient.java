package com.vemser.rest.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserClient extends BaseClient {

    private static final String USERS = "/users";

    @Step("Cria a requisição do tipo GET em /users")
    public Response getUsers() {

        return
                given()
                        .spec(super.set())
                        .when()
                        .get(USERS)
                ;

    }
}
