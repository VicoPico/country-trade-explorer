package com.example.tradeexplorer.health;

import com.example.tradeexplorer.health.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public HealthResponse health() {
        return new HealthResponse(
                "UP",
                "country-trade-explorer-backend",
                Instant.now().toString()
        );
    }
}
