package guru.springframework.brewery.model.events;

import guru.springframework.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private final long serialVersionUID = 1043409355393575887L;

    private BeerDto beerDto;

}
