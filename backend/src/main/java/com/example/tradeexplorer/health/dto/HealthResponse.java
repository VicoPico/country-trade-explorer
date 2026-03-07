package com.example.tradeexplorer.health.dto;

public record HealthResponse(
        String status,
        String service,
        String timestamp
) {
}
