package com.example.tradeexplorer.trade.repository;

import java.math.BigDecimal;

public interface PartnerTradeTotalView {
    String getPartnerIso3();
    String getPartnerName();
    BigDecimal getTotalTradeValue();
}
