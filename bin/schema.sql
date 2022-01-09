CREATE TABLE user_detail(
	id SERIAL PRIMARY KEY,
	name VARCHAR(64),
	roles VARCHAR(256),
	user_name varchar(64),
	password varchar(64),
	active boolean
);

CREATE INDEX IDX_USERNAME ON user_detail(user_name);