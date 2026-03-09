package com.example.tradeexplorer.trade.importer.application;

import com.example.tradeexplorer.country.repository.CountryRepository;
import com.example.tradeexplorer.trade.entity.TradeObservationEntity;
import com.example.tradeexplorer.trade.importer.dto.TradeImportRequest;
import com.example.tradeexplorer.trade.importer.dto.TradeImportResponse;
import com.example.tradeexplorer.trade.importer.source.ExternalTradeRecord;
import com.example.tradeexplorer.trade.importer.source.TradeImportSource;
import com.example.tradeexplorer.trade.repository.TradeObservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeImportService {

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

        List<ExternalTradeRecord> records = tradeImportSource.fetchTradeRecords(reporter, year, flow);

        tradeObservationRepository.deleteByReporterIso3IgnoreCaseAndPeriodYearAndFlowIgnoreCaseAndSource(
                reporter,
                year,
                flow,
                source
        );

        List<TradeObservationEntity> entities = records.stream()
                .filter(record -> countryRepository.existsByIso3Code(record.partnerIso3()))
                .map(record -> new TradeObservationEntity(
                        record.reporterIso3(),
                        record.partnerIso3(),
                        record.periodYear(),
                        record.flow(),
                        record.productCode(),
                        record.productName(),
                        record.tradeValue(),
                        source
                ))
                .toList();

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
