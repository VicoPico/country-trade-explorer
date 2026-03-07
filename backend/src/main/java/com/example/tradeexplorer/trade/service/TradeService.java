package com.example.tradeexplorer.trade.service;

import com.example.tradeexplorer.trade.dto.BilateralTradePointResponse;
import com.example.tradeexplorer.trade.dto.ProductGroupResponse;
import com.example.tradeexplorer.trade.dto.TradePartnerResponse;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private final TradeObservationRepository tradeObservationRepository;

    public TradeService(TradeObservationRepository tradeObservationRepository) {
        this.tradeObservationRepository = tradeObservationRepository;
    }

    public List<TradePartnerResponse> getTopPartners(String reporter, Integer year, String flow) {
        return tradeObservationRepository.findTopPartnersByReporterYearAndFlow(
                        reporter,
                        year,
                        flow
                )
                .stream()
                .limit(5)
                .map(result -> new TradePartnerResponse(
                        result.getPartnerIso3(),
                        result.getPartnerName(),
                        result.getTotalTradeValue().intValue()
                ))
                .toList();
    }

    public List<BilateralTradePointResponse> getBilateralTrend(String reporter, String flow) {
        return tradeObservationRepository.findYearlyTotalsByReporterAndFlow(
                        reporter,
                        flow
                )
                .stream()
                .map(result -> new BilateralTradePointResponse(
                        String.valueOf(result.getPeriodYear()),
                        result.getTotalTradeValue().intValue()
                ))
                .toList();
    }

    public List<ProductGroupResponse> getTopProducts(String reporter, Integer year, String flow) {
        return tradeObservationRepository.findTopProductsByReporterYearAndFlow(
                        reporter,
                        year,
                        flow
                )
                .stream()
                .limit(5)
                .map(result -> new ProductGroupResponse(
                        result.getProductCode(),
                        result.getProductName(),
                        result.getTotalTradeValue().intValue()
                ))
                .toList();
    }
}
