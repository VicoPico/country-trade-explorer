package com.example.tradeexplorer.trade.importer.source;

import java.util.List;

public interface TradeImportSource {
    List<ExternalTradeRecord> fetchTradeRecords(String reporterIso3, Integer periodYear, String flow);
    String sourceName();
}
