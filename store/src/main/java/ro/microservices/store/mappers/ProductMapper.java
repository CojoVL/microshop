package ro.microservices.store.mappers;

import ro.microservices.store.clients.models.InventoryModel;
import ro.microservices.store.entities.Product;
import ro.microservices.store.models.ProductModel;

public final class ProductMapper {

    public static ProductModel toModel(final Product product, final InventoryModel inventory) {
        return ProductModel.builder()
                .name(product.getName())
                .code(product.getCode())
                .category(product.getCategory())
                .price(inventory.getPrice())
                .stock(inventory.getStock())
                .build();
    }
}
