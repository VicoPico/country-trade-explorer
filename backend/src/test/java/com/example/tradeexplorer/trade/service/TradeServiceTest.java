package com.example.tradeexplorer.trade.service;

import com.example.tradeexplorer.trade.dto.BilateralTradePointResponse;
import com.example.tradeexplorer.trade.dto.ProductGroupResponse;
import com.example.tradeexplorer.trade.dto.TradePartnerResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TradeServiceTest {

    private final TradeService tradeService = new TradeService();

    @Test
    void shouldReturnTopPartnersForSweden() {
        List<TradePartnerResponse> partners = tradeService.getTopPartners("SWE");

        assertNotNull(partners);
        assertEquals(5, partners.size());
        assertEquals("DEU", partners.getFirst().partnerCode());
        assertEquals("Germany", partners.getFirst().partnerName());
    }

    @Test
    void shouldReturnDefaultTopPartnersForUnknownReporter() {
        List<TradePartnerResponse> partners = tradeService.getTopPartners("XXX");

        assertNotNull(partners);
        assertEquals(5, partners.size());
        assertEquals("USA", partners.getFirst().partnerCode());
    }

    @Test
    void shouldReturnBilateralTrendForUsa() {
        List<BilateralTradePointResponse> trend = tradeService.getBilateralTrend("USA");

        assertNotNull(trend);
        assertEquals(4, trend.size());
        assertEquals("2021", trend.getFirst().year());
        assertEquals(620, trend.getFirst().tradeValue());
    }

    @Test
    void shouldReturnProductGroupsForChina() {
        List<ProductGroupResponse> products = tradeService.getTopProducts("CHN");

        assertNotNull(products);
        assertEquals(5, products.size());
        assertEquals("85", products.getFirst().productCode());
        assertEquals("Electrical equipment", products.getFirst().productName());
    }
}
