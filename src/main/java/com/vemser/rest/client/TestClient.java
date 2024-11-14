package com.vemser.rest.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TestClient extends BaseClient {

    private final String TEST = "/test";

    @Step("Cria a requisição de GET em /test")
    public Response requestTest(){
        return
                given()
                        .spec(super.set())
                        .when()
                        .get(TEST)
                ;
    }
}
