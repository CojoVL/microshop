package ro.microservices.store.clients.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InventoryModel {
    private String code;
    private BigDecimal price;
    private Integer stock;
}
