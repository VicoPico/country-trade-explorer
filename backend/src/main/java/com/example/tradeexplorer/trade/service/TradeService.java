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

    public List<TradePartnerResponse> getTopPartners(String reporter) {
        return tradeObservationRepository.findTopPartnersByReporterYearAndFlow(
                        reporter,
                        2024,
                        "EXPORT"
                )
                .stream()
                .map(result -> new TradePartnerResponse(
                        result.getPartnerIso3(),
                        result.getPartnerName(),
                        result.getTotalTradeValue().intValue()
                ))
                .toList();
    }

    public List<BilateralTradePointResponse> getBilateralTrend(String reporter) {
        return switch (reporter.toUpperCase()) {
            case "USA" -> List.of(
                    new BilateralTradePointResponse("2021", 620),
                    new BilateralTradePointResponse("2022", 710),
                    new BilateralTradePointResponse("2023", 690),
                    new BilateralTradePointResponse("2024", 760)
            );
            case "CHN" -> List.of(
                    new BilateralTradePointResponse("2021", 800),
                    new BilateralTradePointResponse("2022", 860),
                    new BilateralTradePointResponse("2023", 830),
                    new BilateralTradePointResponse("2024", 910)
            );
            case "DEU" -> List.of(
                    new BilateralTradePointResponse("2021", 420),
                    new BilateralTradePointResponse("2022", 470),
                    new BilateralTradePointResponse("2023", 455),
                    new BilateralTradePointResponse("2024", 495)
            );
            case "SWE" -> List.of(
                    new BilateralTradePointResponse("2021", 120),
                    new BilateralTradePointResponse("2022", 200),
                    new BilateralTradePointResponse("2023", 150),
                    new BilateralTradePointResponse("2024", 280)
            );
            default -> List.of(
                    new BilateralTradePointResponse("2021", 180),
                    new BilateralTradePointResponse("2022", 220),
                    new BilateralTradePointResponse("2023", 210),
                    new BilateralTradePointResponse("2024", 260)
            );
        };
    }

    public List<ProductGroupResponse> getTopProducts(String reporter) {
        return switch (reporter.toUpperCase()) {
            case "USA" -> List.of(
                    new ProductGroupResponse("84", "Machinery", 540),
                    new ProductGroupResponse("85", "Electrical equipment", 510),
                    new ProductGroupResponse("87", "Vehicles", 470),
                    new ProductGroupResponse("30", "Pharmaceuticals", 320),
                    new ProductGroupResponse("88", "Aircraft", 290)
            );
            case "CHN" -> List.of(
                    new ProductGroupResponse("85", "Electrical equipment", 780),
                    new ProductGroupResponse("84", "Machinery", 720),
                    new ProductGroupResponse("94", "Furniture", 360),
                    new ProductGroupResponse("61", "Apparel, knit", 310),
                    new ProductGroupResponse("39", "Plastics", 280)
            );
            case "DEU" -> List.of(
                    new ProductGroupResponse("87", "Vehicles", 520),
                    new ProductGroupResponse("84", "Machinery", 500),
                    new ProductGroupResponse("30", "Pharmaceuticals", 330),
                    new ProductGroupResponse("85", "Electrical equipment", 310),
                    new ProductGroupResponse("90", "Optical instruments", 250)
            );
            case "SWE" -> List.of(
                    new ProductGroupResponse("84", "Machinery", 180),
                    new ProductGroupResponse("85", "Electrical equipment", 165),
                    new ProductGroupResponse("87", "Vehicles", 150),
                    new ProductGroupResponse("30", "Pharmaceuticals", 140),
                    new ProductGroupResponse("44", "Wood", 110)
            );
            default -> List.of(
                    new ProductGroupResponse("84", "Machinery", 250),
                    new ProductGroupResponse("85", "Electrical equipment", 220),
                    new ProductGroupResponse("87", "Vehicles", 180),
                    new ProductGroupResponse("30", "Pharmaceuticals", 140),
                    new ProductGroupResponse("39", "Plastics", 120)
            );
        };
    }
}
