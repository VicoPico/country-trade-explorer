package com.example.tradeexplorer.trade.importer.source;

public class TradeImportSourceException extends RuntimeException {

    public TradeImportSourceException(String message) {
        super(message);
    }

    public TradeImportSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
