DROP TABLE filter;

CREATE TABLE filter
(
	fid INT NOT NULL AUTO_INCREMENT,
	supply VARCHAR(50) NOT NULL,
	brand VARCHAR(50) NOT NULL,
	type VARCHAR(100) NOT NULL,
	air VARCHAR(30),
	machine_oil VARCHAR(30),
	fuel_oil VARCHAR(30),
	air_condition_std VARCHAR(30),
	air_condition_carbon VARCHAR(30),
	PRIMARY KEY(fid)
);
