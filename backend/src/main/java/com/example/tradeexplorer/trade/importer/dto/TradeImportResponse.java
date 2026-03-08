package com.example.tradeexplorer.trade.importer.dto;

public record TradeImportResponse(
        String reporter,
        Integer year,
        String flow,
        String source,
        int importedCount
) {
}
