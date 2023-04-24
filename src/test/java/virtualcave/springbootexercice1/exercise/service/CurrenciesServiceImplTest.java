package virtualcave.springbootexercice1.exercise.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import virtualcave.springbootexercice1.exercise.dto.FilterDto;
import virtualcave.springbootexercice1.exercise.entity.Currencies;
import virtualcave.springbootexercice1.exercise.repository.CurrenciesRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class CurrenciesServiceImplTest {

    @Mock
    private CurrenciesRepository currenciesRepository;

    @InjectMocks
    private CurrenciesServiceImpl tRateService;

    @Test
    @DisplayName("Test findById")
    void findByIdTest() {

        var opTRates = Optional.of(Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("EUR")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build());

        Mockito.when(currenciesRepository.findById(Mockito.anyLong())).thenReturn(opTRates);

        var trates = tRateService.findById(Mockito.anyLong());

        assertNotNull(trates);

        Mockito.verify(currenciesRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Test formatCurrency")
    void formatCurrencyTest() {
        var currency = tRateService.formatCurrency("EUR");
        assertEquals("€", currency);
        currency = tRateService.formatCurrency("USD");
        assertEquals("$", currency);
    }

    @Test
    @DisplayName("Test save")
    void saveTest() {
        var trates = Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("EUR")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build();

        Mockito.when(currenciesRepository.save(Mockito.any(Currencies.class))).thenReturn(trates);

        var tratesSaved = tRateService.save(trates);

        assertNotNull(tratesSaved);

        Mockito.verify(currenciesRepository,
                Mockito.times(1)).save(Mockito.any(Currencies.class));
    }

    @Test
    @DisplayName("Test delete")
    void deleteTest() {

        Mockito.doNothing().when(currenciesRepository).deleteById(Mockito.any());

        tRateService.deleteById(1L);

        Mockito.verify(currenciesRepository,
                Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Test findByDateProductIdBrandId")
    void findByDateProductIdBrandIdTest() {

        var opTRates = Optional.of(Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("EUR")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build());

        FilterDto filterDto = FilterDto.builder()
                .brandId(1L)
                .productId(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .build();

        Mockito.when(currenciesRepository
                .findByDateProductIdBrandId(Mockito.any(),Mockito.any(), Mockito.any()))
                .thenReturn(opTRates);

        var trates = tRateService.findByDateProductIdBrandId(filterDto);

        assertNotNull(trates);

        Mockito.verify(currenciesRepository,
                Mockito.times(1))
                .findByDateProductIdBrandId(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
