package com.example.tradeexplorer.trade.repository;

import java.math.BigDecimal;

public interface YearlyTradeTotalView {
    Integer getPeriodYear();
    BigDecimal getTotalTradeValue();
}
