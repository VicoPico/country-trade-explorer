package com.example.tradeexplorer.trade.repository;

import com.example.tradeexplorer.trade.entity.TradeObservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = """
        SELECT
            t.period_year AS periodYear,
            SUM(t.trade_value) AS totalTradeValue
        FROM trade_observation t
        WHERE UPPER(t.reporter_iso3) = UPPER(:reporterIso3)
          AND UPPER(t.flow) = UPPER(:flow)
        GROUP BY t.period_year
        ORDER BY t.period_year
        """, nativeQuery = true)
    List<YearlyTradeTotalView> findYearlyTotalsByReporterAndFlow(
            @Param("reporterIso3") String reporterIso3,
            @Param("flow") String flow
    );

    @Query(value = """
        SELECT
            t.product_code AS productCode,
            t.product_name AS productName,
            SUM(t.trade_value) AS totalTradeValue
        FROM trade_observation t
        WHERE UPPER(t.reporter_iso3) = UPPER(:reporterIso3)
          AND t.period_year = :periodYear
          AND UPPER(t.flow) = UPPER(:flow)
          AND t.product_code IS NOT NULL
          AND t.product_name IS NOT NULL
        GROUP BY t.product_code, t.product_name
        ORDER BY SUM(t.trade_value) DESC
        """, nativeQuery = true)
    List<ProductTradeTotalView> findTopProductsByReporterYearAndFlow(
            @Param("reporterIso3") String reporterIso3,
            @Param("periodYear") Integer periodYear,
            @Param("flow") String flow
    );

    @Query(value = """
        SELECT DISTINCT t.period_year
        FROM trade_observation t
        ORDER BY t.period_year
        """, nativeQuery = true)
    List<Integer> findAvailableYears();

    @Query(value = """
        SELECT DISTINCT t.flow
        FROM trade_observation t
        ORDER BY t.flow
        """, nativeQuery = true)
    List<String> findAvailableFlows();

    @Modifying
    void deleteByReporterIso3IgnoreCaseAndPeriodYearAndFlowIgnoreCaseAndSource(
            String reporterIso3,
            Integer periodYear,
            String flow,
            String source
    );
}
