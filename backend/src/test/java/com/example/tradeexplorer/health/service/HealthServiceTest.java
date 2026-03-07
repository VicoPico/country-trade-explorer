package com.example.tradeexplorer.health.service;

import com.example.tradeexplorer.health.dto.HealthResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthServiceTest {

    private final HealthService healthService = new HealthService();

    @Test
    void shouldReturnUpHealthStatus() {
        HealthResponse response = healthService.getHealth();

        assertNotNull(response);
        assertEquals("UP", response.status());
        assertEquals("country-trade-explorer-backend", response.service());
        assertNotNull(response.timestamp());
        assertFalse(response.timestamp().isBlank());
    }
}
