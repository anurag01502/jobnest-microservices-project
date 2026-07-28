use company_db;

CREATE TABLE `company` (
  `company_id` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) NOT NULL,
  `established_year` int DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  `description` text,
  `company_size` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `company_location` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `company_id` bigint NOT NULL,
  `latitude` decimal(38,2) DEFAULT NULL,
  `longitude` decimal(38,2) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `fk_company_location_company` (`company_id`),
  CONSTRAINT `fk_company_location_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `company_statistics` (
  `company_id` bigint NOT NULL,
  `projects_completed` int DEFAULT '0',
  `average_rating` decimal(38,2) DEFAULT NULL,
  `average_time` decimal(38,2) DEFAULT NULL,
  `total_clients` int DEFAULT '0',
  PRIMARY KEY (`company_id`),
  CONSTRAINT `fk_statistics_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `verification_status` (
  `company_id` bigint NOT NULL,
  `status` enum('PENDING','VERIFIED','REJECTED') DEFAULT 'PENDING',
  `verified_at` timestamp NULL DEFAULT NULL,
  `verified_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  CONSTRAINT `fk_verification_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `business_domain` (
  `domain_id` bigint NOT NULL AUTO_INCREMENT,
  `domain_name` varchar(100) NOT NULL,
  PRIMARY KEY (`domain_id`),
  UNIQUE KEY `domain_name` (`domain_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `companies_available_domains` (
  `company_id` bigint NOT NULL,
  `domain_id` bigint NOT NULL,
  PRIMARY KEY (`company_id`,`domain_id`),
  KEY `fk_company_domain_domain` (`domain_id`),
  CONSTRAINT `fk_company_domain_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_company_domain_domain` FOREIGN KEY (`domain_id`) REFERENCES `business_domain` (`domain_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
