package virtualcave.springbootexercice1.exercise.service;

import virtualcave.springbootexercice1.exercise.dto.FilterDto;
import virtualcave.springbootexercice1.exercise.entity.Currencies;

public interface CurrenciesService {

    Currencies findById(Long id);
    Currencies save(Currencies currencies);
    void deleteById(Long id);
    Currencies findByDateProductIdBrandId(FilterDto filterDto);
    String formatCurrency(String currencyCode);
}
