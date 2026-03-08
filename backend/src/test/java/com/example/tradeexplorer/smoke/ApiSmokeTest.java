package com.example.tradeexplorer.smoke;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpointShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("country-trade-explorer-backend"));
    }

    @Test
    void countriesEndpointShouldReturnData() throws Exception {
        mockMvc.perform(get("/api/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThan(0)));
    }

    @Test
    void tradeMetadataEndpointShouldReturnData() throws Exception {
        mockMvc.perform(get("/api/trade/metadata"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.years.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.flows.length()").value(greaterThan(0)));
    }

    @Test
    void partnersEndpointShouldReturnData() throws Exception {
        mockMvc.perform(
                        get("/api/trade/partners")
                                .param("reporter", "SWE")
                                .param("year", "2024")
                                .param("flow", "EXPORT")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].partnerCode").value("DEU"));
    }

    @Test
    void bilateralEndpointShouldReturnData() throws Exception {
        mockMvc.perform(
                        get("/api/trade/bilateral")
                                .param("reporter", "SWE")
                                .param("flow", "EXPORT")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$[0].year").value("2021"));
    }

    @Test
    void productsEndpointShouldReturnData() throws Exception {
        mockMvc.perform(
                        get("/api/trade/products")
                                .param("reporter", "SWE")
                                .param("year", "2024")
                                .param("flow", "EXPORT")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].productCode").value("84"));
    }
}
