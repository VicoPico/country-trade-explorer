package com.example.tradeexplorer.trade.repository;

import java.math.BigDecimal;

public interface ProductTradeTotalView {
    String getProductCode();
    String getProductName();
    BigDecimal getTotalTradeValue();
}
