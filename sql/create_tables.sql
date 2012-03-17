DROP TABLE filter;
DROP TABLE supply;
DROP TABLE brand;
DROP TABLE style;
DROP VIEW filter_view;

CREATE TABLE filter
(
	filter_id INT NOT NULL AUTO_INCREMENT,
	supply_id INT NOT NULL,
	brand_id INT NOT NULL,
	style_id INT NOT NULL,
	air VARCHAR(30),
	machine_oil VARCHAR(30),
	fuel_oil VARCHAR(30),
	air_condition_std VARCHAR(30),
	air_condition_carbon VARCHAR(30),
	PRIMARY KEY (filter_id)
);

CREATE TABLE supply
(
	supply_id INT NOT NULL AUTO_INCREMENT,
	supply_name VARCHAR(50) NOT NULL UNIQUE,
	supply_img VARCHAR(100),
	PRIMARY KEY(supply_id)
);

CREATE TABLE brand
(
	brand_id INT NOT NULL AUTO_INCREMENT,
	brand_name VARCHAR(50) NOT NULL UNIQUE,
	brand_img VARCHAR(100),
	PRIMARY KEY(brand_id)
);

CREATE TABLE style
(
	style_id INT NOT NULL AUTO_INCREMENT,
	brand_id INT NOT NULL,
	style_name VARCHAR(100) NOT NULL UNIQUE,
	style_img VARCHAR(100),
	PRIMARY KEY(style_id)
);

CREATE VIEW filter_view AS
(
SELECT f.filter_id, sp.supply_name, sp.supply_img, b.brand_name, b.brand_img, st.style_name, st.style_img, f.air, f.machine_oil, f.fuel_oil, f.air_condition_std, f.air_condition_carbon
FROM ((filter f
INNER JOIN supply sp ON f.supply_id = sp.supply_id)
INNER JOIN style st ON f.style_id = st.style_id)
INNER JOIN brand b ON f.brand_id = b.brand_id
);
