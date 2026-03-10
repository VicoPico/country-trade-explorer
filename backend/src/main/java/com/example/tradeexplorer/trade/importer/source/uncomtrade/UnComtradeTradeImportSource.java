package com.example.tradeexplorer.trade.importer.source.uncomtrade;

import com.example.tradeexplorer.trade.importer.config.UnComtradeProperties;
import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSource;
import com.example.tradeexplorer.trade.importer.source.TradeImportSourceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UnComtradeTradeImportSource implements TradeImportSource {

    private static final Logger log = LoggerFactory.getLogger(
        UnComtradeTradeImportSource.class
    );

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final UnComtradeProperties properties;

    private static final Map<String, String> REPORTER_ISO3_TO_NUMERIC = Map.of(
        "SWE",
        "752",
        "USA",
        "842",
        "CHN",
        "156"
    );

    private static final Map<String, String> PARTNER_NUMERIC_TO_ISO3 = new HashMap<>();

    static {
        PARTNER_NUMERIC_TO_ISO3.put("36", "AUS");
        PARTNER_NUMERIC_TO_ISO3.put("124", "CAN");
        PARTNER_NUMERIC_TO_ISO3.put("156", "CHN");
        PARTNER_NUMERIC_TO_ISO3.put("208", "DNK");
        PARTNER_NUMERIC_TO_ISO3.put("246", "FIN");
        PARTNER_NUMERIC_TO_ISO3.put("276", "DEU");
        PARTNER_NUMERIC_TO_ISO3.put("392", "JPN");
        PARTNER_NUMERIC_TO_ISO3.put("410", "KOR");
        PARTNER_NUMERIC_TO_ISO3.put("484", "MEX");
        PARTNER_NUMERIC_TO_ISO3.put("578", "NOR");
        PARTNER_NUMERIC_TO_ISO3.put("752", "SWE");
        PARTNER_NUMERIC_TO_ISO3.put("840", "USA");
    }

    public UnComtradeTradeImportSource(
        ObjectMapper objectMapper,
        UnComtradeProperties properties
    ) {
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.restClient = RestClient.builder().build();
    }

    @Override
    public List<ExternalTradeRecord> fetchTradeRecords(
        String reporterIso3,
        Integer periodYear,
        String flow
    ) {
        if (!properties.isEnabled()) {
            throw new TradeImportSourceException(
                "UN Comtrade import is disabled. Set UNCOMTRADE_ENABLED=true."
            );
        }

        if (!StringUtils.hasText(properties.getFinalDataUrl())) {
            throw new TradeImportSourceException(
                "UN Comtrade final-data URL is not configured. Set UNCOMTRADE_FINAL_DATA_URL."
            );
        }

        String reporterNumeric = REPORTER_ISO3_TO_NUMERIC.get(
            reporterIso3.toUpperCase()
        );
        if (reporterNumeric == null) {
            throw new TradeImportSourceException(
                "Reporter not supported yet for real import: " + reporterIso3
            );
        }

        String flowCode = mapFlow(flow);
        String commodityCodeList = String.join(
            ",",
            properties.getCommodityCodes()
        );

        String partnerCode = StringUtils.hasText(properties.getPartnerCode())
            ? properties.getPartnerCode().trim()
            : "all";

        String url = UriComponentsBuilder
            .fromUriString(properties.getFinalDataUrl())
            .queryParam("typeCode", "C")
            .queryParam("freqCode", "A")
            .queryParam("clCode", "HS")
            .queryParam("period", periodYear)
            .queryParam("reporterCode", reporterNumeric)
            .queryParam("cmdCode", commodityCodeList)
            .queryParam("flowCode", flowCode)
            .queryParam("partnerCode", partnerCode)
            .queryParam("partner2Code", "")
            .queryParam("customsCode", "")
            .queryParam("motCode", "")
            .queryParam("maxRecords", properties.getMaxRecords())
            .queryParam("format_output", "JSON")
            .queryParam("aggregateBy", "")
            .queryParam("breakdownMode", "classic")
            .queryParam("countOnly", "false")
            .queryParam("includeDesc", "true")
            .build(true)
            .toUriString();

        log.debug(
            "UN Comtrade request: reporter={}, year={}, flow={}, partnerCode={}, cmdCodes={}, maxRecords={}",
            reporterIso3.toUpperCase(),
            periodYear,
            flow.toUpperCase(),
            partnerCode,
            commodityCodeList,
            properties.getMaxRecords()
        );

        try {
            RestClient.RequestHeadersSpec<?> request = restClient
                .get()
                .uri(url)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            if (StringUtils.hasText(properties.getApiKey())) {
                request =
                    request.header(
                        properties.getApiKeyHeaderName(),
                        properties.getApiKey()
                    );
            }

            String body = request.retrieve().body(String.class);
            return parseRecords(
                body,
                reporterIso3.toUpperCase(),
                periodYear,
                flow.toUpperCase()
            );
        } catch (Exception e) {
            throw new TradeImportSourceException(
                "Failed to fetch data from UN Comtrade.",
                e
            );
        }
    }

    @Override
    public String sourceName() {
        return "uncomtrade";
    }

    private List<ExternalTradeRecord> parseRecords(
        String body,
        String reporterIso3,
        Integer periodYear,
        String flow
    ) {
        try {
            JsonNode root = objectMapper.readTree(body);
            JsonNode dataNode = root.path("data");

            if (!dataNode.isArray()) {
                throw new TradeImportSourceException(
                    "UN Comtrade response did not contain a 'data' array."
                );
            }

            return java.util.stream.StreamSupport
                .stream(dataNode.spliterator(), false)
                .map(item ->
                    toExternalTradeRecord(item, reporterIso3, periodYear, flow)
                )
                .filter(java.util.Objects::nonNull)
                .toList();
        } catch (TradeImportSourceException e) {
            throw e;
        } catch (Exception e) {
            throw new TradeImportSourceException(
                "Failed to parse UN Comtrade response.",
                e
            );
        }
    }

    private ExternalTradeRecord toExternalTradeRecord(
        JsonNode item,
        String reporterIso3,
        Integer periodYear,
        String flow
    ) {
        String partnerIso3Raw = firstNonBlank(
            text(item, "partnerISO"),
            text(item, "partnerISO3"),
            text(item, "partnerIso3"),
            text(item, "pt3ISO"),
            mapPartnerNumericToIso3(text(item, "partnerCode"))
        );
        String partnerIso3 = normalizeIso3(partnerIso3Raw);

        String productCode = firstNonBlank(
            text(item, "cmdCode"),
            text(item, "commodityCode"),
            text(item, "productCode")
        );

        String productName = firstNonBlank(
            text(item, "cmdDesc"),
            text(item, "commodityDesc"),
            text(item, "productDesc"),
            text(item, "productName")
        );

        BigDecimal tradeValue = decimal(item, "primaryValue");
        if (tradeValue == null) {
            tradeValue = decimal(item, "tradeValue");
        }

        if (
            !StringUtils.hasText(partnerIso3) ||
            !StringUtils.hasText(productCode) ||
            tradeValue == null
        ) {
            return null;
        }

        return new ExternalTradeRecord(
            reporterIso3,
            partnerIso3,
            periodYear,
            flow,
            productCode,
            productName,
            tradeValue
        );
    }

    private String text(JsonNode node, String fieldName) {
        JsonNode value = node.get(fieldName);
        return value == null || value.isNull() ? null : value.asText();
    }

    private BigDecimal decimal(JsonNode node, String fieldName) {
        JsonNode value = node.get(fieldName);
        if (value == null || value.isNull() || value.asText().isBlank()) {
            return null;
        }
        return new BigDecimal(value.asText());
    }

    private String firstNonBlank(String... values) {
        for (String value : values) {
            if (StringUtils.hasText(value)) {
                return value.trim();
            }
        }
        return null;
    }

    private String normalizeIso3(String iso3) {
        if (!StringUtils.hasText(iso3)) {
            return null;
        }
        return iso3.trim().toUpperCase();
    }

    private String mapFlow(String flow) {
        return switch (flow.toUpperCase()) {
            case "IMPORT" -> "M";
            case "EXPORT" -> "X";
            default -> throw new TradeImportSourceException(
                "Unsupported flow: " + flow
            );
        };
    }

    private String mapPartnerNumericToIso3(String partnerNumeric) {
        if (!StringUtils.hasText(partnerNumeric)) {
            return null;
        }
        return PARTNER_NUMERIC_TO_ISO3.get(partnerNumeric.trim());
    }
}
