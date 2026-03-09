package com.example.tradeexplorer.trade.importer.application;

import com.example.tradeexplorer.country.repository.CountryRepository;
import com.example.tradeexplorer.trade.importer.dto.TradeImportRequest;
import com.example.tradeexplorer.trade.importer.dto.TradeImportResponse;
import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSource;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class TradeImportServiceTest {

    @Test
    void shouldImportOnlyRecordsWithKnownPartnerCountries() {
        TradeImportSource source = mock(TradeImportSource.class);
        TradeObservationRepository repository = mock(TradeObservationRepository.class);
        CountryRepository countryRepository = mock(CountryRepository.class);
        TradeImportService service = new TradeImportService(source, repository, countryRepository);

        TradeImportRequest request = new TradeImportRequest("SWE", 2024, "EXPORT");

        when(source.sourceName()).thenReturn("uncomtrade");
        when(source.fetchTradeRecords("SWE", 2024, "EXPORT")).thenReturn(List.of(
                new ExternalTradeRecord("SWE", "DEU", 2024, "EXPORT", "84", "Machinery", new BigDecimal("100.00")),
                new ExternalTradeRecord("SWE", "USA", 2024, "EXPORT", "30", "Pharmaceuticals", new BigDecimal("50.00")),
                new ExternalTradeRecord("SWE", "ZZZ", 2024, "EXPORT", "99", "Unknown", new BigDecimal("10.00"))
        ));

        when(countryRepository.existsByIso3Code("DEU")).thenReturn(true);
        when(countryRepository.existsByIso3Code("USA")).thenReturn(true);
        when(countryRepository.existsByIso3Code("ZZZ")).thenReturn(false);

        TradeImportResponse response = service.importTradeData(request);

        assertEquals("SWE", response.reporter());
        assertEquals(2024, response.year());
        assertEquals("EXPORT", response.flow());
        assertEquals("uncomtrade", response.source());
        assertEquals(2, response.importedCount());

        verify(repository).deleteByReporterIso3IgnoreCaseAndPeriodYearAndFlowIgnoreCaseAndSource(
                "SWE",
                2024,
                "EXPORT",
                "uncomtrade"
        );
        verify(repository).saveAll(anyList());
    }
}
