package com.example.tradeexplorer.health.service;

import com.example.tradeexplorer.health.dto.HealthResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HealthService {

    public HealthResponse getHealth() {
        return new HealthResponse(
                "UP",
                "country-trade-explorer-backend",
                Instant.now().toString()
        );
    }
}
