CREATE TABLE country (
    iso3_code VARCHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE trade_observation (
    id BIGSERIAL PRIMARY KEY,
    reporter_iso3 VARCHAR(3) NOT NULL,
    partner_iso3 VARCHAR(3) NOT NULL,
    period_year INTEGER NOT NULL,
    flow VARCHAR(20) NOT NULL,
    product_code VARCHAR(20),
    product_name VARCHAR(255),
    trade_value NUMERIC(18,2) NOT NULL,
    source VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_trade_observation_reporter
        FOREIGN KEY (reporter_iso3) REFERENCES country (iso3_code),

    CONSTRAINT fk_trade_observation_partner
        FOREIGN KEY (partner_iso3) REFERENCES country (iso3_code)
);

CREATE INDEX idx_trade_observation_reporter_year
    ON trade_observation (reporter_iso3, period_year);

CREATE INDEX idx_trade_observation_partner_year
    ON trade_observation (partner_iso3, period_year);

CREATE INDEX idx_trade_observation_reporter_partner
    ON trade_observation (reporter_iso3, partner_iso3);

CREATE INDEX idx_trade_observation_flow
    ON trade_observation (flow);
