package com.example.tradeexplorer;

import com.example.tradeexplorer.trade.importer.bootstrap.BootstrapImportProperties;
import com.example.tradeexplorer.trade.importer.config.UnComtradeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(
    { UnComtradeProperties.class, BootstrapImportProperties.class }
)
public class TradeExplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeExplorerApplication.class, args);
    }
}
