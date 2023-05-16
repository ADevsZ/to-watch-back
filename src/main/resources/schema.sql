DROP TABLE IF EXISTS `db_to_watch`.`ratings_x_media`;
DROP TABLE IF EXISTS `db_to_watch`.`streaming_platforms_x_media`;
DROP TABLE IF EXISTS `db_to_watch`.`users_logs`;
DROP TABLE IF EXISTS `db_to_watch`.`watchlists_x_media`;
DROP TABLE IF EXISTS `db_to_watch`.`watchlists`;
DROP TABLE IF EXISTS `db_to_watch`.`users`;
DROP TABLE IF EXISTS `db_to_watch`.`media`;
DROP TABLE IF EXISTS `db_to_watch`.`media_premieres`;
DROP TABLE IF EXISTS `db_to_watch`.`streaming_platforms`;

CREATE TABLE `db_to_watch`.`users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`));

CREATE TABLE `db_to_watch`.`media` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `average_duration` int DEFAULT NULL,
  `sessions_number` int DEFAULT NULL,
  `episodes_number` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`));

CREATE TABLE `db_to_watch`.`streaming_platforms` (
  `platform_id` BIGINT NOT NULL,
  `platform_name` VARCHAR(45) NULL,
  PRIMARY KEY (`platform_id`),
  UNIQUE INDEX `platform_id_UNIQUE` (`platform_id` ASC) VISIBLE);

CREATE TABLE `db_to_watch`.`media_premieres` (
  `premiere_id` BIGINT NOT NULL,
  `media_type` VARCHAR(45) NOT NULL,
  `media_title` VARCHAR(255) NOT NULL,
  `release_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`premiere_id`));

CREATE TABLE `db_to_watch`.`watchlists` (
  `watchlist_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `active` TINYINT NOT NULL DEFAULT 0,
  `user_id` BIGINT NULL,
  PRIMARY KEY (`watchlist_id`),
  UNIQUE INDEX `watchlist_id_UNIQUE` (`watchlist_id` ASC) VISIBLE,
  INDEX `watchlists_fk_01_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `watchlists_fk_01`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_to_watch`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE `watchlists_x_media` (
  `watchlist_id` bigint NOT NULL,
  `media_id` bigint NOT NULL,
  `order` int NOT NULL,
  `viewed` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`watchlist_id`,`media_id`),
  KEY `watchlists_x_media_fk_02_idx` (`media_id`),
  CONSTRAINT `watchlists_x_media_fk_01` FOREIGN KEY (`watchlist_id`) REFERENCES `watchlists` (`watchlist_id`) ON DELETE CASCADE,
  CONSTRAINT `watchlists_x_media_fk_02` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);


CREATE TABLE `db_to_watch`.`ratings_x_media` (
  `media_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`media_id`, `user_id`),
  INDEX `ratings_x_media_fk_02_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `ratings_x_media_fk_01`
    FOREIGN KEY (`media_id`)
    REFERENCES `db_to_watch`.`media` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ratings_x_media_fk_02`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_to_watch`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `db_to_watch`.`users_logs` (
  `log_id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `creation_date` TIMESTAMP NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`log_id`),
  UNIQUE INDEX `log_id_UNIQUE` (`log_id` ASC) VISIBLE,
  INDEX `users_logs_fk_01_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `users_logs_fk_01`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_to_watch`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `db_to_watch`.`streaming_platforms_x_media` (
  `platform_id` BIGINT NOT NULL,
  `media_id` BIGINT NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`platform_id`, `media_id`),
  INDEX `streaming_platforms_x_media_fk_02_idx` (`media_id` ASC) VISIBLE,
  CONSTRAINT `streaming_platforms_x_media_fk_01`
    FOREIGN KEY (`platform_id`)
    REFERENCES `db_to_watch`.`streaming_platforms` (`platform_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `streaming_platforms_x_media_fk_02`
    FOREIGN KEY (`media_id`)
    REFERENCES `db_to_watch`.`media` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);