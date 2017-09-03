package ro.microservices.store.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ro.microservices.store.clients.models.InventoryModel;
import ro.microservices.store.entities.Product;
import ro.microservices.store.repositories.ProductRepository;

@Service
public class InventoryReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryReceiver.class);

    private final ProductRepository productRepository;

    public InventoryReceiver(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @StreamListener("stockChannel")
    public void onReceiving(final Message<InventoryModel> message) {
        InventoryModel inventory = message.getPayload();
            LOGGER.info("Acknowledgment provided: " + inventory.toString());

            Collection<Product> products = productRepository.findByCode(inventory.getCode()).stream()
                .map(p -> {
                    p.setIsPublished(inventory.getStock() > 0);
                    return p;
                }).collect(Collectors.toList());

        productRepository.save(products);
    }

}
