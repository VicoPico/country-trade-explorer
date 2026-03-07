INSERT INTO trade_observation (
    reporter_iso3,
    partner_iso3,
    period_year,
    flow,
    product_code,
    product_name,
    trade_value,
    source
) VALUES
-- Sweden
('SWE', 'DEU', 2024, 'EXPORT', '84', 'Machinery', 120.00, 'seed'),
('SWE', 'DEU', 2024, 'EXPORT', '85', 'Electrical equipment', 90.00, 'seed'),
('SWE', 'NOR', 2024, 'EXPORT', '27', 'Mineral fuels', 110.00, 'seed'),
('SWE', 'NOR', 2024, 'EXPORT', '44', 'Wood', 80.00, 'seed'),
('SWE', 'USA', 2024, 'EXPORT', '30', 'Pharmaceuticals', 95.00, 'seed'),
('SWE', 'USA', 2024, 'EXPORT', '87', 'Vehicles', 80.00, 'seed'),
('SWE', 'DNK', 2024, 'EXPORT', '04', 'Dairy products', 85.00, 'seed'),
('SWE', 'DNK', 2024, 'EXPORT', '84', 'Machinery', 70.00, 'seed'),
('SWE', 'FIN', 2024, 'EXPORT', '48', 'Paper', 75.00, 'seed'),
('SWE', 'FIN', 2024, 'EXPORT', '85', 'Electrical equipment', 70.00, 'seed'),

-- United States
('USA', 'CAN', 2024, 'EXPORT', '87', 'Vehicles', 220.00, 'seed'),
('USA', 'CAN', 2024, 'EXPORT', '84', 'Machinery', 180.00, 'seed'),
('USA', 'MEX', 2024, 'EXPORT', '85', 'Electrical equipment', 210.00, 'seed'),
('USA', 'MEX', 2024, 'EXPORT', '87', 'Vehicles', 190.00, 'seed'),
('USA', 'CHN', 2024, 'EXPORT', '12', 'Oil seeds', 200.00, 'seed'),
('USA', 'CHN', 2024, 'EXPORT', '88', 'Aircraft', 160.00, 'seed'),
('USA', 'DEU', 2024, 'EXPORT', '30', 'Pharmaceuticals', 140.00, 'seed'),
('USA', 'DEU', 2024, 'EXPORT', '90', 'Optical instruments', 120.00, 'seed'),
('USA', 'JPN', 2024, 'EXPORT', '85', 'Electrical equipment', 110.00, 'seed'),
('USA', 'JPN', 2024, 'EXPORT', '84', 'Machinery', 100.00, 'seed'),

-- China
('CHN', 'USA', 2024, 'EXPORT', '85', 'Electrical equipment', 300.00, 'seed'),
('CHN', 'USA', 2024, 'EXPORT', '84', 'Machinery', 260.00, 'seed'),
('CHN', 'KOR', 2024, 'EXPORT', '85', 'Electrical equipment', 170.00, 'seed'),
('CHN', 'KOR', 2024, 'EXPORT', '39', 'Plastics', 120.00, 'seed'),
('CHN', 'JPN', 2024, 'EXPORT', '84', 'Machinery', 160.00, 'seed'),
('CHN', 'JPN', 2024, 'EXPORT', '85', 'Electrical equipment', 150.00, 'seed'),
('CHN', 'DEU', 2024, 'EXPORT', '94', 'Furniture', 110.00, 'seed'),
('CHN', 'DEU', 2024, 'EXPORT', '61', 'Apparel, knit', 95.00, 'seed'),
('CHN', 'AUS', 2024, 'EXPORT', '85', 'Electrical equipment', 100.00, 'seed'),
('CHN', 'AUS', 2024, 'EXPORT', '39', 'Plastics', 90.00, 'seed');
