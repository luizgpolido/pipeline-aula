package com.vemser.rest.client;

import com.vemser.rest.model.request.LoginRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private final String LOGIN = "/auth/login";

    @Step("Cria a requisição POST de Login com um body")
    public Response postLogin(LoginRequest body){
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(body)
                .when()
                        .post(LOGIN)

                ;
    }
}
