-- V1__create_tables.sql

-- Create table for Category
CREATE TABLE `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `description` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Create table for User
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `phone` VARCHAR(20) NOT NULL UNIQUE,
  `address` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `total_spending` DOUBLE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Create table for Product
CREATE TABLE `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `seller_id` BIGINT NOT NULL,
  `available_quantity` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_seller` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Create table for Sale
CREATE TABLE `sale` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `client_id` BIGINT NOT NULL,
  `total` DOUBLE NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sale_client` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Create table for SaleProduct
CREATE TABLE `sale_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sale_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `price` DOUBLE,
  `quantity` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sale_product_sale` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_sale_product_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Create join table for Product and Category many-to-many relationship
CREATE TABLE `product_category` (
  `product_id` BIGINT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  CONSTRAINT `fk_product_category_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB;
