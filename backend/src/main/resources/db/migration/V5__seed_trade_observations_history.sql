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
-- Sweden historical exports
('SWE', 'DEU', 2021, 'EXPORT', '84', 'Machinery', 55.00, 'seed'),
('SWE', 'NOR', 2021, 'EXPORT', '27', 'Mineral fuels', 35.00, 'seed'),
('SWE', 'USA', 2021, 'EXPORT', '30', 'Pharmaceuticals', 30.00, 'seed'),

('SWE', 'DEU', 2022, 'EXPORT', '84', 'Machinery', 80.00, 'seed'),
('SWE', 'NOR', 2022, 'EXPORT', '27', 'Mineral fuels', 60.00, 'seed'),
('SWE', 'USA', 2022, 'EXPORT', '30', 'Pharmaceuticals', 60.00, 'seed'),

('SWE', 'DEU', 2023, 'EXPORT', '84', 'Machinery', 65.00, 'seed'),
('SWE', 'NOR', 2023, 'EXPORT', '27', 'Mineral fuels', 45.00, 'seed'),
('SWE', 'USA', 2023, 'EXPORT', '30', 'Pharmaceuticals', 40.00, 'seed'),

-- USA historical exports
('USA', 'CAN', 2021, 'EXPORT', '87', 'Vehicles', 220.00, 'seed'),
('USA', 'MEX', 2021, 'EXPORT', '85', 'Electrical equipment', 180.00, 'seed'),
('USA', 'CHN', 2021, 'EXPORT', '12', 'Oil seeds', 220.00, 'seed'),

('USA', 'CAN', 2022, 'EXPORT', '87', 'Vehicles', 250.00, 'seed'),
('USA', 'MEX', 2022, 'EXPORT', '85', 'Electrical equipment', 230.00, 'seed'),
('USA', 'CHN', 2022, 'EXPORT', '12', 'Oil seeds', 230.00, 'seed'),

('USA', 'CAN', 2023, 'EXPORT', '87', 'Vehicles', 240.00, 'seed'),
('USA', 'MEX', 2023, 'EXPORT', '85', 'Electrical equipment', 220.00, 'seed'),
('USA', 'CHN', 2023, 'EXPORT', '12', 'Oil seeds', 230.00, 'seed'),

-- China historical exports
('CHN', 'USA', 2021, 'EXPORT', '85', 'Electrical equipment', 300.00, 'seed'),
('CHN', 'KOR', 2021, 'EXPORT', '39', 'Plastics', 250.00, 'seed'),
('CHN', 'JPN', 2021, 'EXPORT', '84', 'Machinery', 250.00, 'seed'),

('CHN', 'USA', 2022, 'EXPORT', '85', 'Electrical equipment', 320.00, 'seed'),
('CHN', 'KOR', 2022, 'EXPORT', '39', 'Plastics', 270.00, 'seed'),
('CHN', 'JPN', 2022, 'EXPORT', '84', 'Machinery', 270.00, 'seed'),

('CHN', 'USA', 2023, 'EXPORT', '85', 'Electrical equipment', 310.00, 'seed'),
('CHN', 'KOR', 2023, 'EXPORT', '39', 'Plastics', 260.00, 'seed'),
('CHN', 'JPN', 2023, 'EXPORT', '84', 'Machinery', 260.00, 'seed');
