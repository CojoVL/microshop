package ro.microservices.store.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ro.microservices.store.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findByCode(final String code);
    Collection<Product> findByCategoryId(final Long id);
    Collection<Product> findByCategoryIdAndIsPublished(final Long id, final Boolean published);
}
