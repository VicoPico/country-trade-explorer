package com.example.tradeexplorer.trade.dto;

import java.util.List;

public record TradeMetadataResponse(
        List<Integer> years,
        List<String> flows
) {
}
