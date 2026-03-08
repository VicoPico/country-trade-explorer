package com.example.tradeexplorer.trade.importer.source.mock;

import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MockTradeImportSource implements TradeImportSource {

    @Override
    public List<ExternalTradeRecord> fetchTradeRecords(String reporterIso3, Integer periodYear, String flow) {
        String reporter = reporterIso3.toUpperCase();
        String normalizedFlow = flow.toUpperCase();

        return switch (reporter + "-" + normalizedFlow + "-" + periodYear) {
            case "SWE-EXPORT-2024" -> List.of(
                    new ExternalTradeRecord("SWE", "DEU", 2024, "EXPORT", "84", "Machinery", new BigDecimal("130.00")),
                    new ExternalTradeRecord("SWE", "NOR", 2024, "EXPORT", "27", "Mineral fuels", new BigDecimal("115.00")),
                    new ExternalTradeRecord("SWE", "USA", 2024, "EXPORT", "30", "Pharmaceuticals", new BigDecimal("98.00")),
                    new ExternalTradeRecord("SWE", "DNK", 2024, "EXPORT", "04", "Dairy products", new BigDecimal("88.00")),
                    new ExternalTradeRecord("SWE", "FIN", 2024, "EXPORT", "48", "Paper", new BigDecimal("77.00"))
            );
            case "SWE-IMPORT-2024" -> List.of(
                    new ExternalTradeRecord("SWE", "DEU", 2024, "IMPORT", "84", "Machinery", new BigDecimal("100.00")),
                    new ExternalTradeRecord("SWE", "CHN", 2024, "IMPORT", "85", "Electrical equipment", new BigDecimal("90.00")),
                    new ExternalTradeRecord("SWE", "NOR", 2024, "IMPORT", "27", "Mineral fuels", new BigDecimal("72.00")),
                    new ExternalTradeRecord("SWE", "USA", 2024, "IMPORT", "30", "Pharmaceuticals", new BigDecimal("62.00"))
            );
            case "USA-EXPORT-2024" -> List.of(
                    new ExternalTradeRecord("USA", "CAN", 2024, "EXPORT", "87", "Vehicles", new BigDecimal("225.00")),
                    new ExternalTradeRecord("USA", "MEX", 2024, "EXPORT", "85", "Electrical equipment", new BigDecimal("215.00")),
                    new ExternalTradeRecord("USA", "CHN", 2024, "EXPORT", "12", "Oil seeds", new BigDecimal("205.00")),
                    new ExternalTradeRecord("USA", "DEU", 2024, "EXPORT", "30", "Pharmaceuticals", new BigDecimal("145.00"))
            );
            case "USA-IMPORT-2024" -> List.of(
                    new ExternalTradeRecord("USA", "CHN", 2024, "IMPORT", "85", "Electrical equipment", new BigDecimal("305.00")),
                    new ExternalTradeRecord("USA", "MEX", 2024, "IMPORT", "87", "Vehicles", new BigDecimal("235.00")),
                    new ExternalTradeRecord("USA", "CAN", 2024, "IMPORT", "27", "Mineral fuels", new BigDecimal("185.00")),
                    new ExternalTradeRecord("USA", "DEU", 2024, "IMPORT", "30", "Pharmaceuticals", new BigDecimal("122.00"))
            );
            default -> List.of();
        };
    }

    @Override
    public String sourceName() {
        return "mock-import";
    }
}
