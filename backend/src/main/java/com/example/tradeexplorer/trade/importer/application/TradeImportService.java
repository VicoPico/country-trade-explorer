package com.example.tradeexplorer.trade.importer.application;

import com.example.tradeexplorer.country.repository.CountryRepository;
import com.example.tradeexplorer.trade.entity.TradeObservationEntity;
import com.example.tradeexplorer.trade.importer.dto.TradeImportRequest;
import com.example.tradeexplorer.trade.importer.dto.TradeImportResponse;
import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSource;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TradeImportService {

    private static final Logger log = LoggerFactory.getLogger(
        TradeImportService.class
    );

    private final TradeImportSource tradeImportSource;
    private final TradeObservationRepository tradeObservationRepository;
    private final CountryRepository countryRepository;

    public TradeImportService(
        TradeImportSource tradeImportSource,
        TradeObservationRepository tradeObservationRepository,
        CountryRepository countryRepository
    ) {
        this.tradeImportSource = tradeImportSource;
        this.tradeObservationRepository = tradeObservationRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional
    public TradeImportResponse importTradeData(TradeImportRequest request) {
        String reporter = request.reporter().toUpperCase();
        String flow = request.flow().toUpperCase();
        Integer year = request.year();
        String source = tradeImportSource.sourceName();

        List<ExternalTradeRecord> records = tradeImportSource.fetchTradeRecords(
            reporter,
            year,
            flow
        );
        List<TradeObservationEntity> entities = records
            .stream()
            .filter(record ->
                countryRepository.existsByIso3Code(record.partnerIso3())
            )
            .map(record ->
                new TradeObservationEntity(
                    record.reporterIso3(),
                    record.partnerIso3(),
                    record.periodYear(),
                    record.flow(),
                    record.productCode(),
                    record.productName(),
                    record.tradeValue(),
                    source
                )
            )
            .toList();

        if (entities.isEmpty()) {
            log.warn(
                "Import produced 0 persisted rows; skipping delete/replace to avoid wiping existing slice (reporter={}, year={}, flow={}, source={}).",
                reporter,
                year,
                flow,
                source
            );

            return new TradeImportResponse(reporter, year, flow, source, 0);
        }

        tradeObservationRepository.deleteByReporterIso3IgnoreCaseAndPeriodYearAndFlowIgnoreCaseAndSource(
            reporter,
            year,
            flow,
            source
        );

        tradeObservationRepository.saveAll(entities);

        return new TradeImportResponse(
            reporter,
            year,
            flow,
            source,
            entities.size()
        );
    }
}
