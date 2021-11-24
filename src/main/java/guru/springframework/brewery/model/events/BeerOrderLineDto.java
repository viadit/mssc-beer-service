package guru.springframework.brewery.model.events;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem{

    private UUID beerId;
    private String beerName;
    private String beerStyle;
    private String upc;
    private Integer orderQuantity = 0;
    private BigDecimal price;

    @Builder
    public BeerOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate,
                            OffsetDateTime lastModifiedDate, UUID beerId, String beerName,
                            String beerStyle, BigDecimal price,
                            String upc, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.price = price;
        this.upc = upc;
        this.orderQuantity = orderQuantity;
    }
}
