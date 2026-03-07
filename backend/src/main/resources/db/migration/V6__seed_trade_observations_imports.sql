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
-- Sweden imports
('SWE', 'DEU', 2021, 'IMPORT', '84', 'Machinery', 45.00, 'seed'),
('SWE', 'CHN', 2021, 'IMPORT', '85', 'Electrical equipment', 35.00, 'seed'),
('SWE', 'NOR', 2021, 'IMPORT', '27', 'Mineral fuels', 25.00, 'seed'),

('SWE', 'DEU', 2022, 'IMPORT', '84', 'Machinery', 65.00, 'seed'),
('SWE', 'CHN', 2022, 'IMPORT', '85', 'Electrical equipment', 55.00, 'seed'),
('SWE', 'NOR', 2022, 'IMPORT', '27', 'Mineral fuels', 40.00, 'seed'),

('SWE', 'DEU', 2023, 'IMPORT', '84', 'Machinery', 60.00, 'seed'),
('SWE', 'CHN', 2023, 'IMPORT', '85', 'Electrical equipment', 50.00, 'seed'),
('SWE', 'NOR', 2023, 'IMPORT', '27', 'Mineral fuels', 40.00, 'seed'),

('SWE', 'DEU', 2024, 'IMPORT', '84', 'Machinery', 95.00, 'seed'),
('SWE', 'CHN', 2024, 'IMPORT', '85', 'Electrical equipment', 85.00, 'seed'),
('SWE', 'NOR', 2024, 'IMPORT', '27', 'Mineral fuels', 70.00, 'seed'),
('SWE', 'USA', 2024, 'IMPORT', '30', 'Pharmaceuticals', 60.00, 'seed'),
('SWE', 'DNK', 2024, 'IMPORT', '04', 'Dairy products', 40.00, 'seed'),

-- USA imports
('USA', 'CHN', 2021, 'IMPORT', '85', 'Electrical equipment', 240.00, 'seed'),
('USA', 'MEX', 2021, 'IMPORT', '87', 'Vehicles', 180.00, 'seed'),
('USA', 'CAN', 2021, 'IMPORT', '27', 'Mineral fuels', 140.00, 'seed'),

('USA', 'CHN', 2022, 'IMPORT', '85', 'Electrical equipment', 270.00, 'seed'),
('USA', 'MEX', 2022, 'IMPORT', '87', 'Vehicles', 210.00, 'seed'),
('USA', 'CAN', 2022, 'IMPORT', '27', 'Mineral fuels', 160.00, 'seed'),

('USA', 'CHN', 2023, 'IMPORT', '85', 'Electrical equipment', 260.00, 'seed'),
('USA', 'MEX', 2023, 'IMPORT', '87', 'Vehicles', 205.00, 'seed'),
('USA', 'CAN', 2023, 'IMPORT', '27', 'Mineral fuels', 155.00, 'seed'),

('USA', 'CHN', 2024, 'IMPORT', '85', 'Electrical equipment', 300.00, 'seed'),
('USA', 'MEX', 2024, 'IMPORT', '87', 'Vehicles', 230.00, 'seed'),
('USA', 'CAN', 2024, 'IMPORT', '27', 'Mineral fuels', 180.00, 'seed'),
('USA', 'DEU', 2024, 'IMPORT', '30', 'Pharmaceuticals', 120.00, 'seed'),
('USA', 'JPN', 2024, 'IMPORT', '84', 'Machinery', 110.00, 'seed'),

-- China imports
('CHN', 'KOR', 2021, 'IMPORT', '85', 'Electrical equipment', 200.00, 'seed'),
('CHN', 'JPN', 2021, 'IMPORT', '84', 'Machinery', 170.00, 'seed'),
('CHN', 'AUS', 2021, 'IMPORT', '26', 'Ores, slag and ash', 150.00, 'seed'),

('CHN', 'KOR', 2022, 'IMPORT', '85', 'Electrical equipment', 230.00, 'seed'),
('CHN', 'JPN', 2022, 'IMPORT', '84', 'Machinery', 190.00, 'seed'),
('CHN', 'AUS', 2022, 'IMPORT', '26', 'Ores, slag and ash', 170.00, 'seed'),

('CHN', 'KOR', 2023, 'IMPORT', '85', 'Electrical equipment', 220.00, 'seed'),
('CHN', 'JPN', 2023, 'IMPORT', '84', 'Machinery', 180.00, 'seed'),
('CHN', 'AUS', 2023, 'IMPORT', '26', 'Ores, slag and ash', 160.00, 'seed'),

('CHN', 'KOR', 2024, 'IMPORT', '85', 'Electrical equipment', 260.00, 'seed'),
('CHN', 'JPN', 2024, 'IMPORT', '84', 'Machinery', 210.00, 'seed'),
('CHN', 'AUS', 2024, 'IMPORT', '26', 'Ores, slag and ash', 190.00, 'seed'),
('CHN', 'DEU', 2024, 'IMPORT', '90', 'Optical instruments', 90.00, 'seed'),
('CHN', 'USA', 2024, 'IMPORT', '12', 'Oil seeds', 80.00, 'seed');
