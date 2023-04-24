package virtualcave.springbootexercice1.exercise.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import virtualcave.springbootexercice1.exercise.entity.Currencies;
import virtualcave.springbootexercice1.exercise.service.CurrenciesService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrenciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CurrenciesService currenciesService;

    @InjectMocks
    private CurrenciesController currenciesController;

    @Test
    @DisplayName("Test findById")
    void findByIdTest() throws Exception {

        var trates = Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("EUR")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build();
        Mockito.when(currenciesService.findById(Mockito.anyLong())).thenReturn(trates);
        mockMvc = MockMvcBuilders.standaloneSetup(currenciesController).build();
        Mockito.when(currenciesService.formatCurrency(Mockito.anyString())).thenReturn("€");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/currencies/find-by-id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(1L))
                .andExpect(jsonPath("$.startDate").value("2023-04-20"))
                .andExpect(jsonPath("$.endDate").value("2023-04-22"))
                .andExpect(jsonPath("$.currencyCode").value("€"));

        Mockito.verify(currenciesService, Mockito.times(1)).formatCurrency(Mockito.anyString());
        Mockito.verify(currenciesService, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Test save")
    void saveTest() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(currenciesController).build();
        var trates = Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("EUR")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build();
        Mockito.when(currenciesService.save(Mockito.any(Currencies.class))).thenReturn(trates);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/currencies/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"productId\":1,\"brandId\":1,\"currencyCode\":\"EUR\",\"price\":1,\"startDate\":\"2023-04-20\",\"endDate\":\"2023-04-22\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(1L))
                .andExpect(jsonPath("$.startDate").value("2023-04-20"))
                .andExpect(jsonPath("$.endDate").value("2023-04-22"))
                .andExpect(jsonPath("$.currencyCode").value("EUR"));

        Mockito.verify(currenciesService, Mockito.times(1)).save(Mockito.any(Currencies.class));
    }

    @Test
    @DisplayName("Test delete")
    void deleteTest() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(currenciesController).build();
        Mockito.doNothing().when(currenciesService).deleteById(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/currencies/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(currenciesService, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Test findByDateProductIdBrandId")
    void findByDateProductIdBrandIdTest() throws Exception {

        var trates = Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("USD")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build();

        Mockito.when(currenciesService.findByDateProductIdBrandId(Mockito.any())).thenReturn(trates);
        mockMvc = MockMvcBuilders.standaloneSetup(currenciesController).build();
        Mockito.when(currenciesService.formatCurrency(Mockito.anyString())).thenReturn("$");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/currencies/find-by-params")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"startDate\":\"2023-04-20\",\"productId\":1,\"brandId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(1L))
                .andExpect(jsonPath("$.startDate").value("2023-04-20"))
                .andExpect(jsonPath("$.endDate").value("2023-04-22"))
                .andExpect(jsonPath("$.currencyCode").value("$"));

        Mockito.verify(currenciesService, Mockito.times(1)).formatCurrency(Mockito.anyString());
        Mockito.verify(currenciesService, Mockito.times(1)).findByDateProductIdBrandId(Mockito.any());
    }

    @Test
    @DisplayName("Test update")
    void updateTest() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(currenciesController).build();
        var trates = Currencies.builder()
                .id(1L)
                .productId(1L)
                .brandId(1L)
                .currencyCode("EUR")
                .price(1L)
                .startDate(LocalDate.parse("2023-04-20"))
                .endDate(LocalDate.parse("2023-04-22"))
                .build();
        Mockito.when(currenciesService.save(Mockito.any(Currencies.class))).thenReturn(trates);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/currencies/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"productId\":1,\"brandId\":1,\"currencyCode\":\"EUR\",\"price\":1,\"startDate\":\"2023-04-20\",\"endDate\":\"2023-04-22\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.price").value(1L))
                .andExpect(jsonPath("$.startDate").value("2023-04-20"))
                .andExpect(jsonPath("$.endDate").value("2023-04-22"))
                .andExpect(jsonPath("$.currencyCode").value("EUR"));

        Mockito.verify(currenciesService, Mockito.times(1)).save(Mockito.any(Currencies.class));
    }
}
