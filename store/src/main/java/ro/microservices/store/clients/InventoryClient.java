package ro.microservices.store.clients;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.microservices.store.clients.models.InventoryModel;

@Service
public class InventoryClient {
    private final String apiUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public InventoryClient(@Value("${inventory.api.url}") final String apiUrl) {
        this.apiUrl = Objects.requireNonNull(apiUrl, "apiUrl should not be null");
    }

    public InventoryModel getProductInventory(final String code) {
        final String url = apiUrl + "/products/" + code;
        try {
            return restTemplate.getForEntity(url, InventoryModel.class).getBody();
        } catch (HttpClientErrorException exception) {
            return InventoryModel.builder()
                    .price(BigDecimal.ZERO)
                    .stock(0)
                    .build();
        }
    }
}
