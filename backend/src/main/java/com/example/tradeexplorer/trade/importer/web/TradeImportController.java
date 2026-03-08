package com.example.tradeexplorer.trade.importer.web;

import com.example.tradeexplorer.trade.importer.application.TradeImportService;
import com.example.tradeexplorer.trade.importer.dto.TradeImportRequest;
import com.example.tradeexplorer.trade.importer.dto.TradeImportResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dev/imports/trade")
public class TradeImportController {

    private final TradeImportService tradeImportService;

    public TradeImportController(TradeImportService tradeImportService) {
        this.tradeImportService = tradeImportService;
    }

    @PostMapping
    public TradeImportResponse importTradeData(@Valid @RequestBody TradeImportRequest request) {
        return tradeImportService.importTradeData(request);
    }
}
