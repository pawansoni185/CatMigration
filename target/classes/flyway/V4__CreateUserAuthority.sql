ALTER TABLE users ADD COLUMN username VARCHAR(100) UNIQUE;

CREATE TABLE authorities(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(100) REFERENCES users.`username`,
	role VARCHAR(50),
	CONSTRAINT fk_authorities_user FOREIGN KEY (username)
	REFERENCES users(username)
);