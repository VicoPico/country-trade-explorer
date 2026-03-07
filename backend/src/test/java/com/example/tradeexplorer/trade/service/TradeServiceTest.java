package com.example.tradeexplorer.trade.service;

import com.example.tradeexplorer.trade.dto.BilateralTradePointResponse;
import com.example.tradeexplorer.trade.dto.ProductGroupResponse;
import com.example.tradeexplorer.trade.dto.TradePartnerResponse;
import com.example.tradeexplorer.trade.repository.PartnerTradeTotalView;
import com.example.tradeexplorer.trade.repository.ProductTradeTotalView;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import com.example.tradeexplorer.trade.repository.YearlyTradeTotalView;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Test
    void shouldReturnTopPartnersFromRepository() {
        TradeObservationRepository repository = mock(TradeObservationRepository.class);
        TradeService tradeService = new TradeService(repository);

        PartnerTradeTotalView row1 = mock(PartnerTradeTotalView.class);
        when(row1.getPartnerIso3()).thenReturn("DEU");
        when(row1.getPartnerName()).thenReturn("Germany");
        when(row1.getTotalTradeValue()).thenReturn(new BigDecimal("210.00"));

        PartnerTradeTotalView row2 = mock(PartnerTradeTotalView.class);
        when(row2.getPartnerIso3()).thenReturn("NOR");
        when(row2.getPartnerName()).thenReturn("Norway");
        when(row2.getTotalTradeValue()).thenReturn(new BigDecimal("190.00"));

        when(repository.findTopPartnersByReporterYearAndFlow("SWE", 2024, "EXPORT"))
                .thenReturn(List.of(row1, row2));

        List<TradePartnerResponse> partners = tradeService.getTopPartners("SWE", 2024, "EXPORT");

        assertEquals(2, partners.size());
        assertEquals("DEU", partners.get(0).partnerCode());
        assertEquals("Germany", partners.get(0).partnerName());
        assertEquals(210, partners.get(0).tradeValue());

        verify(repository).findTopPartnersByReporterYearAndFlow("SWE", 2024, "EXPORT");
    }

    @Test
    void shouldReturnBilateralTrendFromRepository() {
        TradeObservationRepository repository = mock(TradeObservationRepository.class);
        TradeService tradeService = new TradeService(repository);

        YearlyTradeTotalView year1 = mock(YearlyTradeTotalView.class);
        when(year1.getPeriodYear()).thenReturn(2021);
        when(year1.getTotalTradeValue()).thenReturn(new BigDecimal("120.00"));

        YearlyTradeTotalView year2 = mock(YearlyTradeTotalView.class);
        when(year2.getPeriodYear()).thenReturn(2022);
        when(year2.getTotalTradeValue()).thenReturn(new BigDecimal("200.00"));

        when(repository.findYearlyTotalsByReporterAndFlow("SWE", "EXPORT"))
                .thenReturn(List.of(year1, year2));

        List<BilateralTradePointResponse> trend = tradeService.getBilateralTrend("SWE", "EXPORT");

        assertEquals(2, trend.size());
        assertEquals("2021", trend.get(0).year());
        assertEquals(120, trend.get(0).tradeValue());
        assertEquals("2022", trend.get(1).year());

        verify(repository).findYearlyTotalsByReporterAndFlow("SWE", "EXPORT");
    }

    @Test
    void shouldReturnTopProductsFromRepository() {
        TradeObservationRepository repository = mock(TradeObservationRepository.class);
        TradeService tradeService = new TradeService(repository);

        ProductTradeTotalView row1 = mock(ProductTradeTotalView.class);
        when(row1.getProductCode()).thenReturn("84");
        when(row1.getProductName()).thenReturn("Machinery");
        when(row1.getTotalTradeValue()).thenReturn(new BigDecimal("180.00"));

        ProductTradeTotalView row2 = mock(ProductTradeTotalView.class);
        when(row2.getProductCode()).thenReturn("85");
        when(row2.getProductName()).thenReturn("Electrical equipment");
        when(row2.getTotalTradeValue()).thenReturn(new BigDecimal("165.00"));

        when(repository.findTopProductsByReporterYearAndFlow("SWE", 2024, "EXPORT"))
                .thenReturn(List.of(row1, row2));

        List<ProductGroupResponse> products = tradeService.getTopProducts("SWE", 2024, "EXPORT");

        assertEquals(2, products.size());
        assertEquals("84", products.get(0).productCode());
        assertEquals("Machinery", products.get(0).productName());
        assertEquals(180, products.get(0).tradeValue());

        verify(repository).findTopProductsByReporterYearAndFlow("SWE", 2024, "EXPORT");
    }
}
