package com.example.tradeexplorer.trade.repository;

import com.example.tradeexplorer.trade.entity.TradeObservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeObservationRepository extends JpaRepository<TradeObservationEntity, Long> {

    @Query(value = """
        SELECT
            t.partner_iso3 AS partnerIso3,
            c.name AS partnerName,
            SUM(t.trade_value) AS totalTradeValue
        FROM trade_observation t
        JOIN country c
          ON c.iso3_code = t.partner_iso3
        WHERE UPPER(t.reporter_iso3) = UPPER(:reporterIso3)
          AND t.period_year = :periodYear
          AND UPPER(t.flow) = UPPER(:flow)
        GROUP BY t.partner_iso3, c.name
        ORDER BY SUM(t.trade_value) DESC
        """, nativeQuery = true)
    List<PartnerTradeTotalView> findTopPartnersByReporterYearAndFlow(
            @Param("reporterIso3") String reporterIso3,
            @Param("periodYear") Integer periodYear,
            @Param("flow") String flow
    );
}
