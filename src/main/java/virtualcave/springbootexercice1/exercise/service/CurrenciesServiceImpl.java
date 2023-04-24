package virtualcave.springbootexercice1.exercise.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import virtualcave.springbootexercice1.exercise.dto.FilterDto;
import virtualcave.springbootexercice1.exercise.entity.Currencies;
import virtualcave.springbootexercice1.exercise.repository.CurrenciesRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrenciesServiceImpl implements CurrenciesService {

    private final CurrenciesRepository currenciesRepository;

    @Override
    public Currencies findById(@NonNull Long id) {
        log.info("Find by id: {}", id);
        return currenciesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TRates not found"));

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Currencies save(@NonNull Currencies currencies) {
        log.info("Save: {}", currencies);
        return currenciesRepository.save(currencies);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(@NonNull Long id) {
        log.info("Delete by id: {}", id);
        currenciesRepository.deleteById(id);
    }

    @Override
    public Currencies findByDateProductIdBrandId(@NonNull FilterDto filterDto) {
        return currenciesRepository.findByDateProductIdBrandId(filterDto.getStartDate(),
                        filterDto.getProductId(), filterDto.getBrandId())
                .orElseThrow(() -> new NoSuchElementException("TRates not found"));
    }

    @Override
    public String formatCurrency(@NonNull String currencyCode) {
       return Optional.of(currencyCode)
               .filter(cc -> cc.equals("EUR"))
               .map(cc -> "\u20AC")
               .orElse("$");
    }


}
