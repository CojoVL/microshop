package ro.microservices.store.clients;

import java.math.BigDecimal;

import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ro.microservices.store.clients.models.InventoryModel;

@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {

    @Override
    public InventoryClient create(final Throwable throwable) {
        return new InventoryClient() {
            @Override
            public ResponseEntity<InventoryModel> getProductInventory(final String code) {
                return ResponseEntity.ok(
                        InventoryModel.builder()
                                .price(BigDecimal.ZERO)
                                .stock(0)
                                .build());
            }
        };
    }
}
