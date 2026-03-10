package com.example.tradeexplorer.trade.importer.bootstrap;

import com.example.tradeexplorer.trade.importer.application.TradeImportService;
import com.example.tradeexplorer.trade.importer.config.UnComtradeProperties;
import com.example.tradeexplorer.trade.importer.dto.TradeImportRequest;
import com.example.tradeexplorer.trade.importer.dto.TradeImportResponse;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BootstrapTradeImportRunner {

    private static final Logger log = LoggerFactory.getLogger(
        BootstrapTradeImportRunner.class
    );

    private final TradeImportService tradeImportService;
    private final BootstrapImportProperties bootstrapImportProperties;
    private final UnComtradeProperties unComtradeProperties;
    private final TaskExecutor taskExecutor;

    private final AtomicBoolean started = new AtomicBoolean(false);

    public BootstrapTradeImportRunner(
        TradeImportService tradeImportService,
        BootstrapImportProperties bootstrapImportProperties,
        UnComtradeProperties unComtradeProperties,
        TaskExecutor taskExecutor
    ) {
        this.tradeImportService = tradeImportService;
        this.bootstrapImportProperties = bootstrapImportProperties;
        this.unComtradeProperties = unComtradeProperties;
        this.taskExecutor = taskExecutor;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if (!bootstrapImportProperties.isEnabled()) {
            log.info(
                "Bootstrap import disabled (tradeexplorer.bootstrap-import.enabled=false)."
            );
            return;
        }

        if (!unComtradeProperties.isEnabled()) {
            log.info(
                "UN Comtrade import disabled (uncomtrade.enabled=false); skipping bootstrap import."
            );
            return;
        }

        if (!StringUtils.hasText(unComtradeProperties.getFinalDataUrl())) {
            log.warn(
                "UN Comtrade final-data URL not configured; set UNCOMTRADE_FINAL_DATA_URL to enable bootstrap import."
            );
            return;
        }

        if (!started.compareAndSet(false, true)) {
            return;
        }

        String reporter = StringUtils.hasText(
                bootstrapImportProperties.getReporter()
            )
            ? bootstrapImportProperties.getReporter().trim().toUpperCase()
            : "SWE";
        Integer configuredYear = bootstrapImportProperties.getYear();
        Integer year = configuredYear != null ? configuredYear : 2022;
        String flow = StringUtils.hasText(bootstrapImportProperties.getFlow())
            ? bootstrapImportProperties.getFlow().trim().toUpperCase()
            : "EXPORT";

        TradeImportRequest request = new TradeImportRequest(
            reporter,
            year,
            flow
        );

        log.info(
            "Bootstrapping trade import on startup: reporter={}, year={}, flow={}",
            reporter,
            year,
            flow
        );

        taskExecutor.execute(() -> {
            try {
                TradeImportResponse response = tradeImportService.importTradeData(
                    request
                );
                log.info(
                    "Bootstrap trade import finished: {} records (source={})",
                    response.importedCount(),
                    response.source()
                );
            } catch (Exception ex) {
                log.error("Bootstrap trade import failed.", ex);
            }
        });
    }
}
