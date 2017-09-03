package ro.microservices.inventory.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservices.inventory.mappers.ProductMapper;
import ro.microservices.inventory.models.ProductModel;
import ro.microservices.inventory.repositories.ProductRepository;

@RestController
@RequestMapping("/v1/products")
public class ProductResource {

    private final ProductRepository productRepository;

    @Autowired
    public ProductResource(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<ProductModel> getProductsByCode(@PathVariable("code") final String productCode) {
        return productRepository.findByCode(productCode).stream()
                .findFirst()
                .map(p -> ResponseEntity.ok(ProductMapper.toModel(p)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
