DROP TABLE filter;
DROP TABLE supply;
DROP TABLE brand;
DROP TABLE style;
DROP VIEW filter_view;
DROP TABLE spark_plug
DROP VIEW spark_plug_view;


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
SELECT f.filter_id, sp.supply_id, sp.supply_name, sp.supply_img, b.brand_id, b.brand_name, b.brand_img, st.style_id, st.style_name, st.style_img, f.air, f.machine_oil, f.fuel_oil, f.air_condition_std, f.air_condition_carbon
FROM ((filter f
INNER JOIN supply sp ON f.supply_id = sp.supply_id)
INNER JOIN style st ON f.style_id = st.style_id)
INNER JOIN brand b ON f.brand_id = b.brand_id
);

CREATE TABLE spark_plug
(
	sp_id INT NOT NULL AUTO_INCREMENT,
	brand_id INT NOT NULL,
	style_id INT NOT NULL,
	supply_id INT NOT NULL,
	output_volumn VARCHAR(4) NOT NULL,
	motor VARCHAR(30) NOT NULL,
	year VARCHAR(30) NOT NULL,
	remark VARCHAR(50),
	xun_sn VARCHAR(30),
	xun_type VARCHAR(30),
	chao_sn VARCHAR(30),
	chao_type VARCHAR(30),
	rui_sn VARCHAR(30),
	rui_type VARCHAR(30),
	PRIMARY KEY (sp_id)
);

CREATE VIEW spark_plug_view AS
(
SELECT spl.sp_id, spl.supply_id, sp.supply_name, sp.supply_img, spl.brand_id, b.brand_name, b.brand_img, spl.style_id, st.style_name, st.style_img, spl.output_volumn, spl.motor, spl.year, spl.remark, spl.xun_sn, spl.xun_type, spl.chao_sn, spl.chao_type, spl.rui_sn, spl.rui_type
FROM ((spark_plug spl
INNER JOIN supply sp ON spl.supply_id = sp.supply_id)
INNER JOIN style st ON spl.style_id = st.style_id)
INNER JOIN brand b ON spl.brand_id = b.brand_id
);