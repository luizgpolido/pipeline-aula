package com.vemser.rest.data.provider;

import com.vemser.rest.data.factory.ProductDataFactory;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProductDataProvider {


    public static Stream<Arguments> productDataProvider(){
        return Stream.of(
                Arguments.of(Named.of("Produto com title em branco", ProductDataFactory.blankTitleProduct())),
                Arguments.of(Named.of("Produto com description em branco",ProductDataFactory.blankDescriptionProduct())),
                Arguments.of(Named.of("Produto com price negativo",ProductDataFactory.negativePriceProduct())),
                Arguments.of(Named.of("Produto com discount percentage negativo",ProductDataFactory.negativeDiscountPercentageProduct())),
                Arguments.of(Named.of("Produto com rating negativa",ProductDataFactory.negativeRatingProduct())),
                Arguments.of(Named.of("Produto com stock negativo",ProductDataFactory.negativeStockProduct())),
                Arguments.of(Named.of("Produto com brand em branco",ProductDataFactory.blankBrandProduct())),
                Arguments.of(Named.of("Produto com category em branco",ProductDataFactory.blankCategoryProduct())),
                Arguments.of(Named.of("Produto com rating um número extremamente alto",ProductDataFactory.maxDoubleRatingProduct())),
                Arguments.of(Named.of("Produto com discount percentage acima de 100%",ProductDataFactory.overTheLimitDiscountPercentage()))

        );
    }
}
