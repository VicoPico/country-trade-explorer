package com.example.tradeexplorer.trade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TradeController {

    @GetMapping("/api/trade/partners")
    public List<Map<String, Object>> getTopPartners(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return switch (reporter.toUpperCase()) {
            case "USA" -> List.of(
                    Map.of("partnerCode", "CAN", "partnerName", "Canada", "tradeValue", 820),
                    Map.of("partnerCode", "MEX", "partnerName", "Mexico", "tradeValue", 760),
                    Map.of("partnerCode", "CHN", "partnerName", "China", "tradeValue", 680),
                    Map.of("partnerCode", "DEU", "partnerName", "Germany", "tradeValue", 410),
                    Map.of("partnerCode", "JPN", "partnerName", "Japan", "tradeValue", 320)
            );
            case "CHN" -> List.of(
                    Map.of("partnerCode", "USA", "partnerName", "United States", "tradeValue", 910),
                    Map.of("partnerCode", "KOR", "partnerName", "South Korea", "tradeValue", 540),
                    Map.of("partnerCode", "JPN", "partnerName", "Japan", "tradeValue", 500),
                    Map.of("partnerCode", "DEU", "partnerName", "Germany", "tradeValue", 330),
                    Map.of("partnerCode", "AUS", "partnerName", "Australia", "tradeValue", 290)
            );
            case "DEU" -> List.of(
                    Map.of("partnerCode", "FRA", "partnerName", "France", "tradeValue", 390),
                    Map.of("partnerCode", "NLD", "partnerName", "Netherlands", "tradeValue", 370),
                    Map.of("partnerCode", "USA", "partnerName", "United States", "tradeValue", 350),
                    Map.of("partnerCode", "CHN", "partnerName", "China", "tradeValue", 340),
                    Map.of("partnerCode", "POL", "partnerName", "Poland", "tradeValue", 260)
            );
            case "SWE" -> List.of(
                    Map.of("partnerCode", "DEU", "partnerName", "Germany", "tradeValue", 210),
                    Map.of("partnerCode", "NOR", "partnerName", "Norway", "tradeValue", 190),
                    Map.of("partnerCode", "USA", "partnerName", "United States", "tradeValue", 175),
                    Map.of("partnerCode", "DNK", "partnerName", "Denmark", "tradeValue", 160),
                    Map.of("partnerCode", "FIN", "partnerName", "Finland", "tradeValue", 145)
            );
            default -> List.of(
                    Map.of("partnerCode", "USA", "partnerName", "United States", "tradeValue", 300),
                    Map.of("partnerCode", "CHN", "partnerName", "China", "tradeValue", 250),
                    Map.of("partnerCode", "DEU", "partnerName", "Germany", "tradeValue", 200),
                    Map.of("partnerCode", "JPN", "partnerName", "Japan", "tradeValue", 150),
                    Map.of("partnerCode", "FRA", "partnerName", "France", "tradeValue", 120)
            );
        };
    }

    @GetMapping("/api/trade/bilateral")
    public List<Map<String, Object>> getBilateralTrend(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return switch (reporter.toUpperCase()) {
            case "USA" -> List.of(
                    Map.of("year", "2021", "tradeValue", 620),
                    Map.of("year", "2022", "tradeValue", 710),
                    Map.of("year", "2023", "tradeValue", 690),
                    Map.of("year", "2024", "tradeValue", 760)
            );
            case "CHN" -> List.of(
                    Map.of("year", "2021", "tradeValue", 800),
                    Map.of("year", "2022", "tradeValue", 860),
                    Map.of("year", "2023", "tradeValue", 830),
                    Map.of("year", "2024", "tradeValue", 910)
            );
            case "DEU" -> List.of(
                    Map.of("year", "2021", "tradeValue", 420),
                    Map.of("year", "2022", "tradeValue", 470),
                    Map.of("year", "2023", "tradeValue", 455),
                    Map.of("year", "2024", "tradeValue", 495)
            );
            case "SWE" -> List.of(
                    Map.of("year", "2021", "tradeValue", 120),
                    Map.of("year", "2022", "tradeValue", 200),
                    Map.of("year", "2023", "tradeValue", 150),
                    Map.of("year", "2024", "tradeValue", 280)
            );
            default -> List.of(
                    Map.of("year", "2021", "tradeValue", 180),
                    Map.of("year", "2022", "tradeValue", 220),
                    Map.of("year", "2023", "tradeValue", 210),
                    Map.of("year", "2024", "tradeValue", 260)
            );
        };
    }

    @GetMapping("/api/trade/products")
    public List<Map<String, Object>> getTopProducts(
            @RequestParam(defaultValue = "SWE") String reporter
    ) {
        return switch (reporter.toUpperCase()) {
            case "USA" -> List.of(
                    Map.of("productCode", "84", "productName", "Machinery", "tradeValue", 540),
                    Map.of("productCode", "85", "productName", "Electrical equipment", "tradeValue", 510),
                    Map.of("productCode", "87", "productName", "Vehicles", "tradeValue", 470),
                    Map.of("productCode", "30", "productName", "Pharmaceuticals", "tradeValue", 320),
                    Map.of("productCode", "88", "productName", "Aircraft", "tradeValue", 290)
            );
            case "CHN" -> List.of(
                    Map.of("productCode", "85", "productName", "Electrical equipment", "tradeValue", 780),
                    Map.of("productCode", "84", "productName", "Machinery", "tradeValue", 720),
                    Map.of("productCode", "94", "productName", "Furniture", "tradeValue", 360),
                    Map.of("productCode", "61", "productName", "Apparel, knit", "tradeValue", 310),
                    Map.of("productCode", "39", "productName", "Plastics", "tradeValue", 280)
            );
            case "DEU" -> List.of(
                    Map.of("productCode", "87", "productName", "Vehicles", "tradeValue", 520),
                    Map.of("productCode", "84", "productName", "Machinery", "tradeValue", 500),
                    Map.of("productCode", "30", "productName", "Pharmaceuticals", "tradeValue", 330),
                    Map.of("productCode", "85", "productName", "Electrical equipment", "tradeValue", 310),
                    Map.of("productCode", "90", "productName", "Optical instruments", "tradeValue", 250)
            );
            case "SWE" -> List.of(
                    Map.of("productCode", "84", "productName", "Machinery", "tradeValue", 180),
                    Map.of("productCode", "85", "productName", "Electrical equipment", "tradeValue", 165),
                    Map.of("productCode", "87", "productName", "Vehicles", "tradeValue", 150),
                    Map.of("productCode", "30", "productName", "Pharmaceuticals", "tradeValue", 140),
                    Map.of("productCode", "44", "productName", "Wood", "tradeValue", 110)
            );
            default -> List.of(
                    Map.of("productCode", "84", "productName", "Machinery", "tradeValue", 250),
                    Map.of("productCode", "85", "productName", "Electrical equipment", "tradeValue", 220),
                    Map.of("productCode", "87", "productName", "Vehicles", "tradeValue", 180),
                    Map.of("productCode", "30", "productName", "Pharmaceuticals", "tradeValue", 140),
                    Map.of("productCode", "39", "productName", "Plastics", "tradeValue", 120)
            );
        };
    }
}
