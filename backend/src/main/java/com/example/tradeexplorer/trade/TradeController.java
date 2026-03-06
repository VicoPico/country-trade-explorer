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
}
