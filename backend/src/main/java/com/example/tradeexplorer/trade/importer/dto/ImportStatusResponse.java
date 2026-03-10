package com.example.tradeexplorer.trade.importer.dto;

import java.util.List;

public record ImportStatusResponse(
    long totalRows,
    List<ImportSliceStatusResponse> slices
) {}
