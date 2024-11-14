package com.vemser.rest.login;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.client.UserClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.model.request.LoginRequest;
import com.vemser.rest.model.response.LoginResponse;
import com.vemser.rest.model.response.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static Storys.LoginStory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic(EPIC_LOGIN)
@Story(USER_STORY_LOGIN_POST)
@DisplayName("Login")
public class LoginTest {

    LoginClient loginClient = new LoginClient();
    static UserClient userClient = new UserClient();
    static UserResponse firstUser = new UserResponse();

    @BeforeAll
    public static void getFirstUser(){

         firstUser =
                userClient.getUsers()
                    .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getObject("users[0]", UserResponse.class)
                ;
    }


    @Test
    @Description(CT_001)
    @DisplayName("[CT-LOGIN-01]: Validar Login com credenciais válidas")
    @Tag("Functional")
    @Step("Envia a requisição com um body com as credenciais válidas")
    public void testLoginWithValidCredentials(){

        LoginResponse response =
                loginClient.postLogin(LoginDataFactory.validUserLogin())
                    .then()
                        .statusCode(201)
                        .extract()
                        .as(LoginResponse.class)
                ;

        Assertions.assertAll("response",
                () -> Assertions.assertEquals(1, response.getId()),
                () -> Assertions.assertEquals("emilys", response.getUsername()),
                () -> Assertions.assertNotNull(response.getToken()),
                () -> Assertions.assertNotNull(response.getRefreshToken())
        );
    }

    @Test
    @Description(CT_002)
    @DisplayName("[CT-LOGIN-02]: Tentar Login com credenciais inválidas")
    @Tag("Functional")
    @Step("Envia a requisição com um body com as credenciais inválidas")
    public void testLoginWithInvalidCredentials(){

        LoginResponse response =
                loginClient.postLogin(LoginDataFactory.invalidUserLogin())
                    .then()
                        .statusCode(400)
                        .extract()
                        .as(LoginResponse.class)
                ;

        Assertions.assertEquals("Invalid credentials", response.getMessage());
    }

    @Test
    @Description(CT_003)
    @DisplayName("[CT-LOGIN-03]: Tentar Login com credenciais nulas")
    @Tag("Functional")
    @Step("Envia a requisição com um body com as credenciais nulas")
    public void testLoginWithNullCredentials(){

        LoginResponse response =
                loginClient.postLogin(LoginDataFactory.nullUserLogin())
                    .then()
                        .statusCode(400)
                        .extract()
                        .as(LoginResponse.class)
                ;

        Assertions.assertEquals("Username and password required", response.getMessage());
    }

    @Test
    @Description(CT_016)
    @DisplayName("[CT-LOGIN-04]: Validar contrato (JSON)")
    @Tag("Contract")
    @Step("Envia a requisição com um body com as credenciais válidas e valida contrato")
    public void testLoginJSONContract(){

        loginClient.postLogin(LoginDataFactory.validUserLogin())
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/valid_login_schema.json"));
    }

    @Test
    @Description(CT_015)
    @DisplayName("[CT-LOGIN-05]: Validar se informações retornadas são validas para login")
    @Step("Envia a requisição com um body com as credenciais do primeiro usuário cadastrado")
    public void testIfFirstCredentialsInGetUsersIsValidToLogin(){

        loginClient.postLogin(new LoginRequest(firstUser.getUsername(), firstUser.getPassword()))
            .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("refreshToken", notNullValue())
                .body("username", equalTo(firstUser.getUsername()))
                .body("id", equalTo(firstUser.getId()))
        ;
    }
}
