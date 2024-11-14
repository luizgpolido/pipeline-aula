package com.vemser.rest.products;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.client.ProductClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.vemser.rest.storys.ProductStory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic(EPIC_PRODUCT)
@Story(USER_STORY_PRODUCT_GET)
@DisplayName("Get Products")
public class GetProductsTest {

    private final ProductClient productClient = new ProductClient();
    private final LoginClient loginClient = new LoginClient();
    private String token;


    @BeforeEach
    public void setup(TestInfo testInfo){
        if (testInfo.getTags().contains("ignoreAuth")){
            return;
        }

        token =
                loginClient.postLogin(LoginDataFactory.validUserLogin())
                    .then()
                        .statusCode(200)
                        .extract()
                        .path("token")
        ;
    }

    @Test
    @Description(CT_006)
    @DisplayName("[CT-PRODUCTS-01]: Validar listar todos os produtos com autenticação")
    @Tag("Functional")
    @Step("Envia requisição com token válido")
    public void testGetAllAuthProductsWithAuth(){

        productClient.getAllProductsWithAuth(token)
            .then()
                .statusCode(200)
                .body("products[0].id", equalTo(1))
                .body("products[0].title", notNullValue())
                .body("products[0].price", greaterThan(0F))
                .body("products[0].stock", notNullValue())
                .body("products[0].brand", notNullValue())
                .body("products[0].images", notNullValue())
        ;
    }

    @Test
    @Description(CT_007)
    @Tag("ignoreAuth")
    @DisplayName("[CT-PRODUCTS-02]: Tentar listar todos os produtos sem autenticação")
    @Tag("Functional")
    @Step("Envia requisição sem autenticação")
    public void testGetAllAuthProductsWithoutAuth(){

        productClient.getAllProductsWithAuth("")
            .then()
                .statusCode(403)
                .body("message", equalTo("Authentication Problem"))
        ;
    }

    @Test
    @Description(CT_011)
    @Tag("ignoreAuth")
    @DisplayName("[CT-PRODUCTS-06]: Validar contrato do JSON em listar produtos sem autenticação")
    @Tag("Contract")
    @Step("Envia requisição sem autenticação")
    public void testGetAllProductsWithoutAuthAndValidateJsonSchema(){

        productClient.getAllProductsWithoutAuth()
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/valid_get_all_products_schema.json"));
    }

    @Test
    @Description(CT_012)
    @DisplayName("[CT-PRODUCTS-07]: Validar contrato do JSON em listar produtos com autenticação")
    @Tag("Contract")
    @Step("Envia requisição com token válido")
    public void testGetAllProductsWithAuthAndValidateJsonSchema(){

        productClient.getAllProductsWithAuth(token)
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/valid_get_all_products_auth_schema.json"));
    }

    @Test
    @Description(CT_017)
    @Tag("ignoreAuth")
    @DisplayName("[CT-PRODUCTS-08]: Tenta listar todos os produtos (AUTH) com token inválido")
    @Tag("Functional")
    @Step("Envia requisição com token inválido")
    public void testGetAuthProductsWithInvalidToken(){

        productClient.getAllProductsWithAuth("a.b.c")
            .then()
                .statusCode(not(200))
            ;
    }

    @Test
    @Description(CT_018)
    @Tag("Functional")
    @Tag("ignoreAuth")
    @DisplayName("[CT-PRODUCTS-09]: Validar listar produto por ID com ID válido")
    @Step("Envia requisição com ID válido")
    public void testGetProductByIDWithValidParam(){

        productClient.getProductById("1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .body("price", greaterThan(0F))
                .body("stock", notNullValue())
                .body("brand", notNullValue())
                .body("images", notNullValue())
        ;
    }

    @Test
    @Description(CT_019)
    @Tag("Functional")
    @Tag("ignoreAuth")
    @DisplayName("[CT-PRODUCTS-10]: Tentar listar produto por ID com ID negativo")
    @Step("Envia requisição com ID negativo")
    public void testGetProductByIDWithNegativeParam(){

        productClient.getProductById("-1")
                .then()
                .statusCode(404)
                .body("message", equalTo("Product with id '-1' not found"))
        ;
    }

    @Test
    @Description(CT_020)
    @Tag("Functional")
    @Tag("ignoreAuth")
    @DisplayName("[CT-PRODUCTS-11]: Tentar listar produto por ID com letras")
    @Step("Envia requisição com ID com letras")
    public void testGetProductByIDWithTextParam(){

        productClient.getProductById("abcde")
                .then()
                .statusCode(404)
                .body("message", equalTo("Product with id 'abcde' not found"))
        ;
    }

    @Test
    @Description(CT_021)
    @DisplayName("[CT-PRODUCTS-12]: Validar contrato do JSON em listar produto por ID")
    @Tag("Contract")
    @Step("Envia requisição com token válido")
    public void testGetProductByIDAndValidateJsonSchema(){

        productClient.getProductById("1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/valid_get_product_by_id_schema.json"));
    }
}
