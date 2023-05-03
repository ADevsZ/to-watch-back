DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `media`;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `db_to_watch`.`media` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) DEFAULT NULL,
  `title` VARCHAR(45) DEFAULT NULL,
  `release_date` DATE DEFAULT NULL,
  `nationality` VARCHAR(45) DEFAULT NULL,
  `synopsis` VARCHAR(255) DEFAULT NULL,
  `duration` INT DEFAULT NULL,
  `average_duration` INT DEFAULT NULL,
  `sessions_number` INT DEFAULT NULL,
  `episodes_number` INT DEFAULT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

