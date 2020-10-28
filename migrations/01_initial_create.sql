DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS categories;

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
);

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(200) NOT NULL,
  `item_price` double NOT NULL,
  `item_image` varchar(200) NULL,
  `category_id` int(11) NULL,
  PRIMARY KEY (`item_id`),
  FOREIGN KEY (`category_id`) REFERENCES categories(`category_id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_eating_location` varchar(100) NOT NULL,
  `order_payment_method` varchar(100) NOT NULL,
  `order_status` int(1) NULL DEFAULT 0,
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
);

CREATE TABLE `order_details` (
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NULL,
  `item_quantity` int(3) NOT NULL,
  `item_size` varchar(10) NULL,
  `item_order_price` double NOT NULL,
  FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`)
  	ON UPDATE CASCADE
  	ON DELETE CASCADE,
  FOREIGN KEY (`item_id`) REFERENCES `items`(`item_id`)
  	ON UPDATE CASCADE
  	ON DELETE SET NULL
);

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_fullname` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`)
);
