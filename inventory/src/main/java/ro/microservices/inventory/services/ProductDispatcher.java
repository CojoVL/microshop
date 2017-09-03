package ro.microservices.inventory.services;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.microservices.inventory.config.KafkaGateway;
import ro.microservices.inventory.models.ProductModel;

@Service
public class ProductDispatcher {
    private static Logger LOGGER = LoggerFactory.getLogger(ProductDispatcher.class);

    private final KafkaGateway kafkaGateway;

    @Autowired
    public ProductDispatcher(final KafkaGateway kafkaGateway) {
        this.kafkaGateway = Objects.requireNonNull(kafkaGateway, "kafkaGateway must not be null");
    }

    public void dispatch(final ProductModel product) {
        LOGGER.info("[dispatch] " + product.toString());
        kafkaGateway.write(product);
    }
}
