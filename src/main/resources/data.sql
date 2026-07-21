-- Brands
INSERT IGNORE INTO brands (id, brand) VALUES
  (1, 'BMW'),
  (2, 'AUDI'),
  (3, 'MERCEDES');

-- Models
INSERT IGNORE INTO models (id, model, year, color, type, seats, doors, fuel, minimal_age, brand_id, price_per_day) VALUES
  (1, '_3SERIES', 2020, 'BLACK',  'SEDAN', 5, 4, 'GASOLINE', 21, 1, 45),
  (2, '_5SERIES', 2022, 'BLUE',   'SEDAN', 5, 4, 'DIESEL',   23, 1, 35),
  (3, 'A4',       2021, 'WHITE',  'SEDAN', 5, 4, 'GASOLINE', 21, 2, 25),
  (4, 'C_CLASS',  2023, 'SILVER', 'SUV',   5, 5, 'ELECTRIC', 25, 3, 15);

-- Cars
INSERT IGNORE INTO cars (id, plate, status, model_id) VALUES
  (1, 'CB1111AA', 'AVAILABLE', 1),
  (2, 'CB2222BB', 'AVAILABLE', 2),
  (3, 'CA3333CC', 'RENTED',    3),
  (4, 'PB4444DD', 'AVAILABLE', 4);