package com.vemser.rest.test;

import com.vemser.rest.client.TestClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.vemser.rest.storys.TestStory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Epic(EPIC_TEST)
@Story(USER_STORY_TEST_GET)
@DisplayName("Health Check")
public class HealthCheckTest {

    TestClient testClient = new TestClient();

    @Test
    @DisplayName("[CT-TEST-01]: Validar Test - Health Check")
    @Description(CT_000)
    @Step("Envia requisição e valida o status code e response body")
    @Tag("Health-Check")
    public void testHealthCheckEndpoint(){

        testClient.requestTest()
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("status", equalTo("ok"))
                .body("method", equalTo("GET"))
        ;
    }

    @Test
    @DisplayName("[CT-TEST-02]: Validar contrato (JSON)")
    @Description(CT_013)
    @Step("Envia requisição e valida o contrato")
    @Tag("Contract")
    public void testHealthCheckJSONContract(){

        testClient.requestTest()
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/valid_health_check_schema.json"));
    }
}
