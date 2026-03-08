package com.example.tradeexplorer.trade.importer.application;

import com.example.tradeexplorer.trade.importer.dto.TradeImportRequest;
import com.example.tradeexplorer.trade.importer.dto.TradeImportResponse;
import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSource;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TradeImportServiceTest {

    @Test
    void shouldImportRecordsFromSourceIntoRepository() {
        TradeImportSource source = mock(TradeImportSource.class);
        TradeObservationRepository repository = mock(TradeObservationRepository.class);
        TradeImportService service = new TradeImportService(source, repository);

        TradeImportRequest request = new TradeImportRequest("SWE", 2024, "EXPORT");

        when(source.sourceName()).thenReturn("mock-import");
        when(source.fetchTradeRecords("SWE", 2024, "EXPORT")).thenReturn(List.of(
                new ExternalTradeRecord("SWE", "DEU", 2024, "EXPORT", "84", "Machinery", new BigDecimal("100.00")),
                new ExternalTradeRecord("SWE", "USA", 2024, "EXPORT", "30", "Pharmaceuticals", new BigDecimal("50.00"))
        ));

        TradeImportResponse response = service.importTradeData(request);

        assertEquals("SWE", response.reporter());
        assertEquals(2024, response.year());
        assertEquals("EXPORT", response.flow());
        assertEquals("mock-import", response.source());
        assertEquals(2, response.importedCount());

        verify(repository).deleteByReporterIso3IgnoreCaseAndPeriodYearAndFlowIgnoreCaseAndSource(
                "SWE",
                2024,
                "EXPORT",
                "mock-import"
        );
        verify(repository).saveAll(anyList());
    }
}
