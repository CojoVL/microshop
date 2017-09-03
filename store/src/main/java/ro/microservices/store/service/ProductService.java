package ro.microservices.store.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ro.microservices.store.clients.InventoryClient;
import ro.microservices.store.clients.models.InventoryModel;
import ro.microservices.store.entities.Product;
import ro.microservices.store.mappers.ProductMapper;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.repositories.ProductRepository;

@Service
public class ProductService {

    private final InventoryClient inventoryClient;
    private final ProductRepository productRepository;


    public ProductService(final InventoryClient inventoryClient, final ProductRepository productRepository) {
        this.inventoryClient = Objects.requireNonNull(inventoryClient, "inventoryClient should not be null");
        this.productRepository = Objects.requireNonNull(productRepository, "productRepository should not be null");
    }

    public Optional<ProductModel> getByCode(final String code) {
        return productRepository.findByCode(code).stream()
                .findFirst()
                .map(this::productToProductModel);
    }

    public List<ProductModel> getByCategoryAndInStock(final Long categoryId) {
        return productRepository.findByCategoryIdAndIsPublished(categoryId, true).stream()
                .map(this::productToProductModel)
                .collect(Collectors.toList());
    }

    private ProductModel productToProductModel(final Product product) {
        InventoryModel productInventory = inventoryClient.getProductInventory(product.getCode());
        return ProductMapper.toModel(product, productInventory);
    }
}
