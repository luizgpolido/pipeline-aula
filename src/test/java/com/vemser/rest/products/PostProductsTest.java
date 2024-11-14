package com.vemser.rest.products;

import com.vemser.rest.client.ProductClient;
import com.vemser.rest.data.factory.ProductDataFactory;
import com.vemser.rest.model.request.ProductRequest;
import com.vemser.rest.model.response.ProductResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.vemser.rest.storys.ProductStory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.not;

@Epic(EPIC_PRODUCT)
@Story(USER_STORY_PRODUCT_POST)
@DisplayName("Post Products")
public class PostProductsTest {

    ProductClient productClient = new ProductClient();


    @Test
    @Description(CT_008)
    @DisplayName("[CT-PRODUCTS-03]: Validar criar produto")
    @Step("Envia requisição com body válido")
    @Tag("Functional")
    public void testCreateValidProduct(){

        ProductRequest request = ProductDataFactory.validProduct();

        ProductResponse response =
                productClient.postProduct(request)
                    .then()
                        .statusCode(201)
                        .extract()
                        .as(ProductResponse.class)
                ;

        Assertions.assertAll("response",
                () -> Assertions.assertEquals(request.getTitle(), response.getTitle()),
                () -> Assertions.assertEquals(request.getPrice(), response.getPrice()),
                () -> Assertions.assertEquals(request.getStock(), response.getStock()),
                () -> Assertions.assertEquals(request.getRating(), response.getRating()),
                () -> Assertions.assertEquals(request.getThumbnail(), response.getThumbnail()),
                () -> Assertions.assertEquals(request.getDescription(), response.getDescription()),
                () -> Assertions.assertEquals(request.getBrand(), response.getBrand()),
                () -> Assertions.assertEquals(request.getCategory(), response.getCategory()),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @ParameterizedTest
    @MethodSource("com.vemser.sicredi.data.provider.ProductDataProvider#productDataProvider")
    @Description(CT_009)
    @DisplayName("[CT-PRODUCTS-04]: Tenta criar produto com campos vazios/negativos ")
    @Step("Envia várias requisições, com diferentes tipos de campos inválidos a cada requisição.")
    @Tag("Functional")
    public void testParameterizedBlankAndNegativeValues(ProductRequest productRequest){

        productClient.postProduct(productRequest)
            .then()
                .statusCode(not(201))
        ;
    }

    @Test
    @Description(CT_010)
    @DisplayName("[CT-PRODUCTS-05]: Validar contrato do JSON em cadastrar produto")
    @Tag("Contract")
    @Step("Envia requsição válida e verifica se o contrato é válido")
    public void testPostValidProductAndJsonSchema(){

        productClient.postProduct(ProductDataFactory.validProduct())
            .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/valid_post_product_schema.json"));
    }

}
