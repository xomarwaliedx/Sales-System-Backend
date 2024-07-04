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
