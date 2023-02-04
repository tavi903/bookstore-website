SET SCHEMA 'bookstoredb';

CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `flag_deleted` tinyint(1) NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30),
  `last_update` timestamp ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(30),
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_UN` (`name`)
);

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL,
  `fullname` varchar(30) NOT NULL,
  `address` varchar(128) NOT NULL,
  `city` varchar(32) NOT NULL,
  `country` varchar(64) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `zipcode` varchar(24) NOT NULL,
  `password` varchar(16) NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_UN` (`email`)
);

CREATE TABLE IF NOT EXISTS `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `author` varchar(64) NOT NULL,
  `description` mediumtext NOT NULL,
  `isbn` varchar(15) NOT NULL,
  `image` mediumblob,
  `price` float NOT NULL,
  `publish_date` date NOT NULL,
  `category_id` int NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30),
  `last_update` timestamp ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(30),
  PRIMARY KEY (`id`),
  UNIQUE KEY `book_UN` (`isbn`),
  CONSTRAINT `book_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
);

CREATE TABLE IF NOT EXISTS `book_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `order_date` datetime NOT NULL,
  `shipping_address` varchar(256) NOT NULL,
  `recipient_name` varchar(30) NOT NULL,
  `recipient_phone` varchar(15) NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `total` float NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30),
  `last_update` timestamp ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(30),
  PRIMARY KEY (`id`),
  CONSTRAINT `book_order_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  `subtotal` float NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30),
  `last_update` timestamp ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(30),
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_detail_UN` (`order_id`, `book_id`),
  CONSTRAINT `order_detail_fk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `order_detail_fk_2` FOREIGN KEY (`order_id`) REFERENCES `book_order` (`id`)
);

CREATE TABLE IF NOT EXISTS `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `rating` int NOT NULL,
  `headline` varchar(128) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `review_time` datetime NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30),
  `last_update` timestamp ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(30),
  PRIMARY KEY (`id`),
  CONSTRAINT `review_fk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `review_fk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(16) NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30),
  `last_update` timestamp ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(30),
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UN` (`email`)
);

CREATE TABLE IF NOT EXISTS `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL,
  `exception` varchar(48),
  `level` varchar(30) NOT NULL,
  `message` text not null,
  `stack_trace` text,
  PRIMARY KEY (`id`)
);


