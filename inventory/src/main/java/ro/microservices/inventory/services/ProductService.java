package ro.microservices.inventory.services;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.mappers.ProductMapper;
import ro.microservices.inventory.models.ProductModel;
import ro.microservices.inventory.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductDispatcher productDispatcher;

    public ProductService(final ProductRepository repository, final ProductDispatcher productDispatcher) {
        this.repository = repository;
        this.productDispatcher = productDispatcher;
    }

    public ProductModel save(final ProductModel productModel) {
        Product product = getByCode(productModel.getCode())
                .map(updateStock(productModel))
                .map(p -> {
                    p.setPrice(productModel.getPrice());
                    return p;
                })
                .orElseGet(() -> ProductMapper.toEntity(productModel));

        return ProductMapper.toModel(repository.save(product));
    }

    public Optional<Product> getByCode(final String code) {
        return repository.findByCode(code).stream().findFirst();
    }

    private Function<Product, Product> updateStock(final ProductModel productModel) {
        return p -> {
            Integer initStock = p.getStock();
            if(initStock != productModel.getStock()) {
                p.setStock(productModel.getStock());

                if(initStock == 0 || productModel.getStock() == 0) {
                    productDispatcher.dispatch(productModel);
                }
            }
            return p;
        };
    }
}
