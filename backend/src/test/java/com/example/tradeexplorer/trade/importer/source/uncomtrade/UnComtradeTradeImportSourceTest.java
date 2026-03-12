package com.example.tradeexplorer.trade.importer.source.uncomtrade;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.tradeexplorer.country.service.CountryCodeMapper;
import com.example.tradeexplorer.trade.importer.config.UnComtradeProperties;
import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSourceException;
import com.fasterxml.jackson.databind.ObjectMapper;

class UnComtradeTradeImportSourceTest {

    @Test
    void shouldParseSupportedResponseShape() throws Exception {
        UnComtradeProperties properties = new UnComtradeProperties();
        CountryCodeMapper mapper = mock(CountryCodeMapper.class);
        when(mapper.toIso3CodeFromNumeric("276"))
            .thenReturn(Optional.of("DEU"));

        UnComtradeTradeImportSource source = new UnComtradeTradeImportSource(
            new ObjectMapper(),
            properties,
            mapper
        );

        String body =
            """
                {
                  "data": [
                    {
                      "partnerCode": "276",
                      "cmdCode": "84",
                      "cmdDesc": "Machinery",
                      "primaryValue": 123.45
                    },
                    {
                      "partnerISO": "USA",
                      "commodityCode": "30",
                      "commodityDesc": "Pharmaceuticals",
                      "tradeValue": 50
                    }
                  ]
                }
                """;

        Method method =
            UnComtradeTradeImportSource.class.getDeclaredMethod(
                    "parseRecords",
                    String.class,
                    String.class,
                    Integer.class,
                    String.class
                );
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<ExternalTradeRecord> records = (List<ExternalTradeRecord>) method.invoke(
            source,
            body,
            "SWE",
            2024,
            "EXPORT"
        );

        assertEquals(2, records.size());
        assertEquals("DEU", records.get(0).partnerIso3());
        assertEquals("84", records.get(0).productCode());
        assertEquals("USA", records.get(1).partnerIso3());
        assertEquals("30", records.get(1).productCode());
    }

    @Test
    void shouldRejectUnsupportedFlow() {
        UnComtradeProperties properties = new UnComtradeProperties();
        properties.setEnabled(true);
        properties.setFinalDataUrl("https://example.com");
        CountryCodeMapper mapper = mock(CountryCodeMapper.class);
        when(mapper.toNumericCode("SWE")).thenReturn(Optional.of("752"));
        UnComtradeTradeImportSource source = new UnComtradeTradeImportSource(
            new ObjectMapper(),
            properties,
            mapper
        );

        TradeImportSourceException exception = assertThrows(
            TradeImportSourceException.class,
            () -> source.fetchTradeRecords("SWE", 2024, "BALANCE")
        );

        assertTrue(exception.getMessage().contains("Unsupported flow"));
    }
}
