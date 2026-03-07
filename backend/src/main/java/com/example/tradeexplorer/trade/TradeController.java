package com.example.tradeexplorer.trade;

import com.example.tradeexplorer.trade.dto.BilateralTradePointResponse;
import com.example.tradeexplorer.trade.dto.ProductGroupResponse;
import com.example.tradeexplorer.trade.dto.TradePartnerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @GetMapping("/api/trade/partners")
    public List<TradePartnerResponse> getTopPartners(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return switch (reporter.toUpperCase()) {
            case "USA" -> List.of(
                    new TradePartnerResponse("CAN", "Canada", 820),
                    new TradePartnerResponse("MEX", "Mexico", 760),
                    new TradePartnerResponse("CHN", "China", 680),
                    new TradePartnerResponse("DEU", "Germany", 410),
                    new TradePartnerResponse("JPN", "Japan", 320)
            );
            case "CHN" -> List.of(
                    new TradePartnerResponse("USA", "United States", 910),
                    new TradePartnerResponse("KOR", "South Korea", 540),
                    new TradePartnerResponse("JPN", "Japan", 500),
                    new TradePartnerResponse("DEU", "Germany", 330),
                    new TradePartnerResponse("AUS", "Australia", 290)
            );
            case "DEU" -> List.of(
                    new TradePartnerResponse("FRA", "France", 390),
                    new TradePartnerResponse("NLD", "Netherlands", 370),
                    new TradePartnerResponse("USA", "United States", 350),
                    new TradePartnerResponse("CHN", "China", 340),
                    new TradePartnerResponse("POL", "Poland", 260)
            );
            case "SWE" -> List.of(
                    new TradePartnerResponse("DEU", "Germany", 210),
                    new TradePartnerResponse("NOR", "Norway", 190),
                    new TradePartnerResponse("USA", "United States", 175),
                    new TradePartnerResponse("DNK", "Denmark", 160),
                    new TradePartnerResponse("FIN", "Finland", 145)
            );
            default -> List.of(
                    new TradePartnerResponse("USA", "United States", 300),
                    new TradePartnerResponse("CHN", "China", 250),
                    new TradePartnerResponse("DEU", "Germany", 200),
                    new TradePartnerResponse("JPN", "Japan", 150),
                    new TradePartnerResponse("FRA", "France", 120)
            );
        };
    }

    @GetMapping("/api/trade/bilateral")
    public List<BilateralTradePointResponse> getBilateralTrend(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
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

    @GetMapping("/api/trade/products")
    public List<ProductGroupResponse> getTopProducts(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
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
