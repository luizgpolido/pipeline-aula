package com.vemser.rest.users;

import com.vemser.rest.client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.vemser.rest.storys.UserStory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

@Epic(EPIC_USER)
@Story(USER_STORY_USERS_GET)
@DisplayName("Get Users")
public class GetUsersTest {

    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("[CT-USERS-01]: Validar listar todos os usuários")
    @Description(CT004)
    @Step("Envia requisição de GET de /users e valida os campos importantes para o negócio")
    @Tag("Functional")
    public void testValidGetClients() {

        userClient.getUsers()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("users", notNullValue())
                .body("users[0].username", notNullValue())
                .body("users[0].password", notNullValue())
        ;
    }

    @Test
    @DisplayName("[CT-USERS-02]: Validar contrato JSON no endpoint users")
    @Description(CT015)
    @Step("Envia requisição de GET em /users e valida o contrato")
    @Tag("Contract")
    public void testGetUsersJsonSchema(){

        userClient.getUsers()
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/valid_get_users_schema.json"));
    }
}

