package ro.microservices.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import ro.microservices.store.entities.Category;
import ro.microservices.store.entities.Product;
import ro.microservices.store.repositories.CategoryRepository;
import ro.microservices.store.repositories.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}

@Component
class DummyCLR implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(final String... strings) throws Exception {
		Category category = categoryRepository.save(Category.builder().name("Category Name").build());

		Product product = Product.builder()
				.code("prod1")
				.name("Product Name")
				.category(category)
				.build();
		productRepository.save(product);
	}
}
