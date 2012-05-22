DROP TABLE filter;
DROP TABLE supply;
DROP TABLE brand;
DROP TABLE style;
DROP TABLE spark;

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
	support_count INT default 0,
	suggest_count INT default 0,
	PRIMARY KEY (filter_id)
);

CREATE TABLE filter_suggest
(
	suggest_id INT NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(30),
	content VARCHAR(200),
	PRIMARY KEY (suggest_id)
);


CREATE TABLE supply
(
	supply_id INT NOT NULL AUTO_INCREMENT,
	supply_name VARCHAR(50) NOT NULL UNIQUE,
	supply_img VARCHAR(100),
	order_index INT(2) NOT NULL,
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

CREATE TABLE spark
(
	spark_id INT NOT NULL AUTO_INCREMENT,
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
	PRIMARY KEY (spark_id)
);

/*
DROP VIEW filter_view;
DROP VIEW spark_view;
DROP VIEW style_view;

CREATE VIEW filter_view AS
(
SELECT f.filter_id, sp.supply_id, sp.supply_name, sp.supply_img, b.brand_id, b.brand_name, b.brand_img, st.style_id, st.style_name, st.style_img, f.air, f.machine_oil, f.fuel_oil, f.air_condition_std, f.air_condition_carbon
FROM ((filter f
INNER JOIN supply sp ON f.supply_id = sp.supply_id)
INNER JOIN style st ON f.style_id = st.style_id)
INNER JOIN brand b ON f.brand_id = b.brand_id
);

CREATE VIEW spark_view AS
(
SELECT spk.spark_id, spk.supply_id, sp.supply_name, sp.supply_img, spk.brand_id, b.brand_name, b.brand_img, spk.style_id, st.style_name, st.style_img, spk.output_volumn, spk.motor, spk.year, spk.remark, spk.xun_sn, spk.xun_type, spk.chao_sn, spk.chao_type, spk.rui_sn, spk.rui_type
FROM ((spark spk
INNER JOIN supply sp ON spk.supply_id = sp.supply_id)
INNER JOIN style st ON spk.style_id = st.style_id)
INNER JOIN brand b ON spk.brand_id = b.brand_id
);

CREATE VIEW style_view AS
(
SELECT st.style_id, st.brand_id, st.style_name, st.style_img, b.brand_name, b.brand_img
FROM style st
INNER JOIN brand b ON st.brand_id = b.brand_id
);
*/