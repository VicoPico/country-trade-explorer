package com.example.tradeexplorer.trade.dto;

public record TradePartnerResponse(
        String partnerCode,
        String partnerName,
        Integer tradeValue
) {
}
