package com.example.tradeexplorer.trade.importer.source;

import java.math.BigDecimal;

public record ExternalTradeRecord(
        String reporterIso3,
        String partnerIso3,
        Integer periodYear,
        String flow,
        String productCode,
        String productName,
        BigDecimal tradeValue
) {
}
