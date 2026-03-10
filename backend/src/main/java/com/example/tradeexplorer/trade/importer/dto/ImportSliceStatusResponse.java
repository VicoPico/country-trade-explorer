package com.example.tradeexplorer.trade.importer.dto;

public record ImportSliceStatusResponse(
    String reporter,
    Integer year,
    String flow,
    String source,
    long rowCount
) {}
