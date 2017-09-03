package ro.microservices.inventory.mappers;

import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.models.ProductModel;

public final class ProductMapper {

    public static ProductModel toModel(final Product product) {
        return ProductModel.builder()
                .code(product.getCode())
                .stock(product.getStock())
                .price(product.getPrice())
                .build();
    }

    public static Product toEntity(final ProductModel productModel) {
        return Product.builder()
                .code(productModel.getCode())
                .price(productModel.getPrice())
                .stock(productModel.getStock())
                .build();
    }
}
