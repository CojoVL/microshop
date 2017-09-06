package ro.microservices.inventory;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.repositories.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Component
	class DummyData implements CommandLineRunner {

		@Autowired
		private ProductRepository productRepository;

		@Override
		public void run(final String... args) throws Exception {
			productRepository.save(Product.builder().code("prod1").price(BigDecimal.TEN).stock(15).build());
		}
	}
}
