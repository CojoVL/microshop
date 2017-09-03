package ro.microservices.inventory.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ro.microservices.inventory.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from products where code = :code
    @RestResource(path = "by-code")
    Collection<Product> findByCode(@Param("code") final String code);
}
