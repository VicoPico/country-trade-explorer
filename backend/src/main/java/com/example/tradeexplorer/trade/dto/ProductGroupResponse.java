package com.example.tradeexplorer.trade.dto;

public record ProductGroupResponse(
        String productCode,
        String productName,
        Integer tradeValue
) {
}
