package com.example.tradeexplorer.trade.importer.web;

import com.example.tradeexplorer.trade.importer.dto.ImportSliceStatusResponse;
import com.example.tradeexplorer.trade.importer.dto.ImportStatusResponse;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dev/imports")
public class TradeImportStatusController {

    private final TradeObservationRepository tradeObservationRepository;

    public TradeImportStatusController(
        TradeObservationRepository tradeObservationRepository
    ) {
        this.tradeObservationRepository = tradeObservationRepository;
    }

    @GetMapping("/status")
    public ImportStatusResponse getImportStatus() {
        long totalRows = tradeObservationRepository.count();

        List<ImportSliceStatusResponse> slices = tradeObservationRepository
            .findImportSlices()
            .stream()
            .map(slice -> {
                Long recordCount = slice.getRecordCount();
                long rowCount = recordCount == null ? 0L : recordCount;

                return new ImportSliceStatusResponse(
                    slice.getReporterIso3(),
                    slice.getPeriodYear(),
                    slice.getFlow(),
                    slice.getSource(),
                    rowCount
                );
            })
            .toList();

        return new ImportStatusResponse(totalRows, slices);
    }
}
