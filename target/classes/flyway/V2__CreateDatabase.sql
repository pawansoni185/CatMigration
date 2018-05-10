SET foreign_key_checks=0;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
	`id` BIGINT(20) PRIMARY KEY,
	`name` VARCHAR(100) NOT NULL,
	`emailId` VARCHAR(100) NOT NULL,
	`password` VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories(
	`id` BIGINT(20) PRIMARY KEY,
	`name` VARCHAR(100) NOT NULL	
);

DROP TABLE IF EXISTS threads;

CREATE TABLE threads(
	`id` BIGINT(20) PRIMARY KEY,
	`subject` VARCHAR(100) NOT NULL,
	`description` VARCHAR(2000) NOT NULL,
	`userId` BIGINT(20) NOT NULL,
	`startTime` TIMESTAMP NOT NULL,
	`modifiedTime` TIMESTAMP,
	CONSTRAINT fk_threads_user FOREIGN KEY (userId)
	REFERENCES users(id)
);

DROP TABLE IF EXISTS thread_attachments;

CREATE TABLE thread_attachments(
	`id` BIGINT(20) PRIMARY KEY,
	`threadId` BIGINT(20) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`file_path` VARCHAR(255) NOT NULL,
	`modifiedTime` TIMESTAMP NOT NULL,
	CONSTRAINT fk_thread_attachments_thread FOREIGN KEY (threadId)
	REFERENCES threads(id)
);

DROP TABLE IF EXISTS responses;

CREATE TABLE responses(
	`id` BIGINT(20) PRIMARY KEY,
	`threadId` BIGINT(20) NOT NULL,
	`userId` BIGINT(20) NOT NULL,
	`response` VARCHAR(2000) NOT NULL,	
	`responseTimestamp` TIMESTAMP NOT NULL,
	CONSTRAINT fk_responses_thread FOREIGN KEY (threadId)
	REFERENCES threads(id),
	CONSTRAINT fk_responses_users FOREIGN KEY (userId)
	REFERENCES users(id)
);

DROP TABLE IF EXISTS `response_attachments`;

CREATE TABLE `response_attachments` (
  `id` bigint(20) NOT NULL,
  `responseId` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `filePath` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_response_attachments_responses` FOREIGN KEY (`responseId`) REFERENCES `responses` (`id`)
);

SET foreign_key_checks=1;