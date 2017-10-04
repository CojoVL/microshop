package ro.microservices.inventory.resources;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservices.inventory.mappers.ProductMapper;
import ro.microservices.inventory.models.ProductModel;
import ro.microservices.inventory.services.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping(value = "/{code}")
    public ResponseEntity<ProductModel> getProductsByCode(@PathVariable("code") final String productCode, final Principal user) {
        return productService.getByCode(productCode)
                .map(p -> ResponseEntity.ok(ProductMapper.toModel(p)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping(value = "/")
    public ResponseEntity<ProductModel> addProducts(@RequestBody final ProductModel product) {
        return ResponseEntity.ok(productService.save(product));
    }
}
