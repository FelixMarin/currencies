package virtualcave.springbootexercice1.exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import virtualcave.springbootexercice1.exercise.dto.FilterDto;
import virtualcave.springbootexercice1.exercise.entity.Currencies;
import virtualcave.springbootexercice1.exercise.service.CurrenciesService;

@RestController
@RequestMapping("/v1/currencies")
@RequiredArgsConstructor
public class CurrenciesController {

    private final CurrenciesService currenciesService;

    @GetMapping("/find-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Currencies findById(@PathVariable("id") Long id) {
        //Permite recuperar una tarifa por id, con los precios
        var tRate = currenciesService.findById(id);
        tRate.setCurrencyCode(currenciesService.formatCurrency(tRate.getCurrencyCode()));
        return tRate;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    //Permite crear una tarifa nueva
    public Currencies save(@RequestBody Currencies currencies) {
        return currenciesService.save(currencies);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Currencies update(@RequestBody Currencies currencies) {
        return currenciesService.save(currencies);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        currenciesService.deleteById(id);
    }

    @PostMapping("/find-by-params")
    @ResponseStatus(HttpStatus.OK)
    public Currencies findByParams(@RequestBody FilterDto filterDto) {
        //Permite recuperar una tarifa por parámetros, con los precios
        var tRtate = currenciesService.findByDateProductIdBrandId(filterDto);
        tRtate.setCurrencyCode(currenciesService.formatCurrency(tRtate.getCurrencyCode()));
        return tRtate;
    }
}
