package guru.springframework.brewery.model.events;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseItem{

    private UUID customerId;
    private String customerRef;
    private String orderStatus;
    private List<BeerOrderLineDto> beerOrderLines;
    private String orderStatusCallbackUrl;

    @Builder
    public BeerOrderDto(UUID id, Integer version, OffsetDateTime createdDate,
                        OffsetDateTime lastModifiedDate, UUID customerId, String customerRef,
                        String orderStatus, List<BeerOrderLineDto> beerOrderLines
            , String orderStatusCallbackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.orderStatus = orderStatus;
        this.beerOrderLines = beerOrderLines;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }
}
