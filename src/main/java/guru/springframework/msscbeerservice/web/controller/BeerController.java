package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.brewery.model.BeerDto;
import guru.springframework.brewery.model.BeerPagedList;
import guru.springframework.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.services.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @GetMapping(produces = {"application/json"}, path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
         return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        return new ResponseEntity<>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @Cacheable(cacheNames = "beerUpcCache")
    @GetMapping("beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable String upc) {
        return new ResponseEntity<BeerDto>(beerService.getBeerByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "beer")
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {

        return new ResponseEntity(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @Validated @RequestBody BeerDto beerDto) {

        return new ResponseEntity(beerService.updateBeerById(beerId, beerDto), HttpStatus.NO_CONTENT);
    }
}
