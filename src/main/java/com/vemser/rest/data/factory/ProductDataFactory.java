package com.vemser.rest.data.factory;

import com.vemser.rest.model.request.ProductRequest;
import net.datafaker.Faker;

public class ProductDataFactory {

    private static final Faker faker = new Faker();

    private static ProductRequest createNewProduct(){
        return new ProductRequest(
                faker.book().title(),
                faker.text().text(),
                faker.random().nextInt(10, 300),
                Math.round((faker.random().nextDouble(1, 99) * 10.0) / 10.0),
                Math.round((faker.random().nextDouble(1, 4) * 10.0) / 10.0),
                faker.random().nextInt(10, 1000),
                faker.book().publisher(),
                "Books",
                faker.internet().url()
        );
    }

    public static ProductRequest validProduct(){
        return createNewProduct();
    }

    public static ProductRequest blankTitleProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setTitle("");
        return productRequest;
    }

    public static ProductRequest blankDescriptionProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setDescription("");
        return productRequest;
    }

    public static ProductRequest negativePriceProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setPrice(-1);
        return productRequest;
    }

    public static ProductRequest negativeDiscountPercentageProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setDiscountPercentage(-1);
        return productRequest;
    }

    public static ProductRequest negativeRatingProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setRating(-1);
        return productRequest;
    }

    public static ProductRequest negativeStockProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setStock(-1);
        return productRequest;
    }

    public static ProductRequest blankBrandProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setBrand("");
        return productRequest;
    }

    public static ProductRequest blankCategoryProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setCategory("");
        return productRequest;
    }

    public static ProductRequest blankThumbnailProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setThumbnail("");
        return productRequest;
    }

    public static ProductRequest maxDoubleRatingProduct(){
        ProductRequest productRequest = createNewProduct();
        productRequest.setRating(Double.MAX_VALUE);
        return productRequest;
    }

    public static ProductRequest overTheLimitDiscountPercentage() {
        ProductRequest productRequest = createNewProduct();
        productRequest.setDiscountPercentage(150);
        return productRequest;
    }
}
