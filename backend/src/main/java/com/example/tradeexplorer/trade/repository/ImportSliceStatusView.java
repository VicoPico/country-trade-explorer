package com.example.tradeexplorer.trade.repository;

public interface ImportSliceStatusView {
    String getReporterIso3();
    Integer getPeriodYear();
    String getFlow();
    String getSource();
    Long getRecordCount();
}
