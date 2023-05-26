DROP TABLE IF EXISTS `db_to_watch`.`ratings_x_media`;
DROP TABLE IF EXISTS `db_to_watch`.`streaming_platforms_x_media`;
DROP TABLE IF EXISTS `db_to_watch`.`users_logs`;
DROP TABLE IF EXISTS `db_to_watch`.`watchlists_x_media`;
DROP TABLE IF EXISTS `db_to_watch`.`watchlists`;
DROP TABLE IF EXISTS `db_to_watch`.`users`;
DROP TABLE IF EXISTS `db_to_watch`.`media`;
DROP TABLE IF EXISTS `db_to_watch`.`media_premieres`;
DROP TABLE IF EXISTS `db_to_watch`.`streaming_platforms`;

CREATE TABLE `media` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `nationality` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `synopsis` text COLLATE utf8mb3_spanish_ci,
  `duration` int DEFAULT NULL,
  `average_duration` int DEFAULT NULL,
  `sessions_number` int DEFAULT NULL,
  `episodes_number` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `media_premieres` (
  `premiere_id` bigint NOT NULL,
  `media_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `media_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `release_date` timestamp NOT NULL,
  PRIMARY KEY (`premiere_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `ratings_x_media` (
  `media_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `rating` int NOT NULL,
  `rating_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`rating_id`),
  UNIQUE KEY `rating_id_UNIQUE` (`rating_id`),
  KEY `ratings_x_media_fk_01_idx` (`media_id`),
  KEY `ratings_x_media_fk_02_idx` (`user_id`),
  CONSTRAINT `ratings_x_media_fk_01` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ratings_x_media_fk_02` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `streaming_platforms` (
  `platform_id` bigint NOT NULL,
  `platform_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`platform_id`),
  UNIQUE KEY `platform_id_UNIQUE` (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `streaming_platforms_x_media` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `platform_id` bigint NOT NULL,
  `media_id` bigint NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `streaming_platforms_x_media_fk_02_idx` (`media_id`),
  CONSTRAINT `streaming_platforms_x_media_fk_01` FOREIGN KEY (`platform_id`) REFERENCES `streaming_platforms` (`platform_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `streaming_platforms_x_media_fk_02` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `users_logs` (
  `log_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `creation_date` timestamp NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `log_id_UNIQUE` (`log_id`),
  KEY `users_logs_fk_01_idx` (`user_id`),
  CONSTRAINT `users_logs_fk_01` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `watchlists` (
  `watchlist_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `active` tinyint NOT NULL DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`watchlist_id`),
  UNIQUE KEY `watchlist_id_UNIQUE` (`watchlist_id`),
  KEY `watchlists_fk_01_idx` (`user_id`),
  CONSTRAINT `watchlists_fk_01` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

CREATE TABLE `watchlists_x_media` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `watchlist_id` bigint NOT NULL,
  `media_id` bigint NOT NULL,
  `orden` int NOT NULL,
  `viewed` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `watchlists_x_media_fk_02_idx` (`media_id`),
  KEY `watchlists_x_media_fk_01_idx` (`watchlist_id`),
  CONSTRAINT `watchlists_x_media_fk_01` FOREIGN KEY (`watchlist_id`) REFERENCES `watchlists` (`watchlist_id`) ON DELETE CASCADE,
  CONSTRAINT `watchlists_x_media_fk_02` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;