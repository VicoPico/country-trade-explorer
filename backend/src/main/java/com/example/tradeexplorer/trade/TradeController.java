package com.example.tradeexplorer.trade;

import com.example.tradeexplorer.trade.dto.BilateralTradePointResponse;
import com.example.tradeexplorer.trade.dto.ProductGroupResponse;
import com.example.tradeexplorer.trade.dto.TradePartnerResponse;
import com.example.tradeexplorer.trade.service.TradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/api/trade/partners")
    public List<TradePartnerResponse> getTopPartners(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return tradeService.getTopPartners(reporter);
    }

    @GetMapping("/api/trade/bilateral")
    public List<BilateralTradePointResponse> getBilateralTrend(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return tradeService.getBilateralTrend(reporter);
    }

    @GetMapping("/api/trade/products")
    public List<ProductGroupResponse> getTopProducts(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return tradeService.getTopProducts(reporter);
    }
}
