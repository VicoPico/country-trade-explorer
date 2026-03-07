package com.example.tradeexplorer.trade.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "trade_observation")
public class TradeObservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter_iso3", nullable = false, length = 3)
    private String reporterIso3;

    @Column(name = "partner_iso3", nullable = false, length = 3)
    private String partnerIso3;

    @Column(name = "period_year", nullable = false)
    private Integer periodYear;

    @Column(name = "flow", nullable = false, length = 20)
    private String flow;

    @Column(name = "product_code", length = 20)
    private String productCode;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "trade_value", nullable = false, precision = 18, scale = 2)
    private BigDecimal tradeValue;

    @Column(name = "source", nullable = false, length = 50)
    private String source;

    protected TradeObservationEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getReporterIso3() {
        return reporterIso3;
    }

    public String getPartnerIso3() {
        return partnerIso3;
    }

    public Integer getPeriodYear() {
        return periodYear;
    }

    public String getFlow() {
        return flow;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getTradeValue() {
        return tradeValue;
    }

    public String getSource() {
        return source;
    }
}
