ALTER TABLE country
    ADD COLUMN numeric_code VARCHAR(3);

UPDATE country SET numeric_code = CASE iso3_code
    WHEN 'AUS' THEN '036'
    WHEN 'BRA' THEN '076'
    WHEN 'CAN' THEN '124'
    WHEN 'CHN' THEN '156'
    WHEN 'DEU' THEN '276'
    WHEN 'DNK' THEN '208'
    WHEN 'FIN' THEN '246'
    WHEN 'FRA' THEN '250'
    WHEN 'GBR' THEN '826'
    WHEN 'IND' THEN '356'
    WHEN 'JPN' THEN '392'
    WHEN 'KOR' THEN '410'
    WHEN 'MEX' THEN '484'
    WHEN 'NLD' THEN '528'
    WHEN 'NOR' THEN '578'
    WHEN 'POL' THEN '616'
    WHEN 'SWE' THEN '752'
    WHEN 'USA' THEN '840'
    ELSE NULL
END;

ALTER TABLE country
    ALTER COLUMN numeric_code SET NOT NULL;

CREATE UNIQUE INDEX ux_country_numeric_code
    ON country (numeric_code);
