package com.vemser.rest.client;

import com.vemser.rest.model.request.ProductRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProductClient extends BaseClient {

    private final String PRODUCTS = "/products";
    private final String AUTH_PRODUCTS = "auth/products";

    @Step("Cria a requisição GET em auth/products")
    public Response getAllProductsWithAuth(String token){
        return
                given()
                        .spec(super.set())
                        .header("Authorization", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(AUTH_PRODUCTS)
                ;
    }

    @Step("Cria a requisição GET em /products")
    public Response getAllProductsWithoutAuth(){
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(PRODUCTS)
                ;
    }

    @Step("Cria a requisição GET em /products/{id}")
    public Response getProductById(String id){
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .pathParam("id" , id)
                        .when()
                        .get(PRODUCTS + "/{id}")
                ;
    }

    @Step("Cria a requisição POST em /products/add")
    public Response postProduct(ProductRequest body){
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .post(PRODUCTS + "/add")
                ;
    }
}
