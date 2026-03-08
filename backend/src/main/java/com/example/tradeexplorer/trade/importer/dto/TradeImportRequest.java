package com.example.tradeexplorer.trade.importer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TradeImportRequest(
        @NotBlank
        @Size(min = 3, max = 3)
        String reporter,

        @Min(2000)
        @Max(2100)
        Integer year,

        @NotBlank
        @Pattern(regexp = "EXPORT|IMPORT")
        String flow
) {
}
