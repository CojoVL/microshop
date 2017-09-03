package ro.microservices.store.resources;

import java.util.Collection;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.service.ProductService;

@RestController
@RequestMapping("v1/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(final ProductService productService) {
        this.productService = Objects.requireNonNull(productService, "productService should not be null");
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable("code") final String code) {
        return productService.getByCode(code).
                map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{category}/list")
    public Collection<ProductModel> getCategoryProducts(@PathVariable("category") final Long categoryId) {
        return productService.getByCategoryAndInStock(categoryId);
    }
}
