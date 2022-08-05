CREATE TABLE nace (
	order_id VARCHAR(64) PRIMARY KEY,
	level_id VARCHAR (64),
	code VARCHAR (64),
	parent VARCHAR(64),
	description VARCHAR(1000),
	this_item_includes VARCHAR(1000),
	this_item_also_includes VARCHAR(1000),
	rulings VARCHAR(1000),
	this_item_excludes VARCHAR(1000),
	reference_to_isic_rev4 VARCHAR(64)
);