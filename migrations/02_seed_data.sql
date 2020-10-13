DELETE FROM `categories`;
DELETE FROM `items`;
DELETE FROM `orders`;
DELETE FROM `order_details`;
DELETE FROM `users`;

ALTER TABLE `categories` AUTO_INCREMENT = 1;
ALTER TABLE `items` AUTO_INCREMENT = 1;
ALTER TABLE `orders` AUTO_INCREMENT = 1;
ALTER TABLE `order_details` AUTO_INCREMENT = 1;
ALTER TABLE `users` AUTO_INCREMENT = 1;

--
-- Source: https://www.zomato.com/ncr/mcdonalds-janpath-new-delhi/order
--

INSERT INTO `categories` (`category_name`) VALUES
  ('Meal Combos'),
  ('Sides'),
  ('Desserts'),
  ('Beverages');

INSERT INTO `items` (`item_name`, `item_price`, `item_image`, `category_id`) VALUES
  ('Krabby Patty Value Meal', 5.15, 'https://b.zmtcdn.com/data/dish_photos/452/f6297bbafae110b76453ca78a2cf6452.jpg', 1),
  ('Seaweed Wrap', 3.89, 'https://b.zmtcdn.com/data/dish_photos/1c8/2111a3b479c81a8d738cf42ef37f31c8.jpg', 1),
  ('Patrik Star Meal', 2.16, 'https://b.zmtcdn.com/data/dish_photos/a4e/bf134f09c17995a4ec54b55b32928a4e.jpg', 1),
  ('Spongy Fries', 1.99, 'https://b.zmtcdn.com/data/dish_photos/671/a72661987ae2d3efa3c336e7fdd40671.jpg', 2),
  ('Pirate Snack', 3.20, 'https://b.zmtcdn.com/data/dish_photos/6b8/28ef02f89c1bfb24a39fd347780fe6b8.jpg', 2),
  ('Mrs Puff Pastry', 1.31, 'https://b.zmtcdn.com/data/dish_photos/91f/11e3d724c0fa28bf53b6b93180dad91f.png', 2),
  ('Cream Cookies', 2.77, 'https://b.zmtcdn.com/data/dish_photos/6a1/a8b772965acc98c27e900046eed366a1.jpg', 3),
  ('Sandy Brownie', 2.79, 'https://b.zmtcdn.com/data/dish_photos/3fd/9f787bf19d49e2b3c20e558ffb5e73fd.jpg', 3),
  ('Strawberry Soft Serve', 3.65, 'https://b.zmtcdn.com/data/dish_photos/32e/cc1d9c77ced500c2b1df7c019b0c632e.jpg', 3),
  ('Squid Ink Drink', 1.40, 'https://b.zmtcdn.com/data/dish_photos/441/4a5318a0ce6e68ae345c91a0f3139441.png', 4),
  ('Orange Juice', 1.66, 'https://b.zmtcdn.com/data/dish_photos/ca5/3891a51f9ba71bd9f37ee1616bcceca5.png', 4),
  ('Cold Coffee', 2.30, 'https://b.zmtcdn.com/data/dish_photos/1fe/715ff2c6ed6b30b5b26fbeca40d4c1fe.jpg', 4);

INSERT INTO `orders` (`order_eating_location`, `order_payment_method`, `order_status`) VALUES
  ('EAT_IN', 'PAY_HERE', 1),
  ('TAKE_OUT', 'PAY_HERE', 0),
  ('TAKE_OUT', 'PAY_COUNTER', 0);

INSERT INTO `order_details` (`order_id`, `item_id`, `item_quantity`, `item_size`, `item_order_price`) VALUES
  (1, 1, 1, 'm', 5.15),
  (1, 10, 1, 'm', 1.40),
  (2, 2, 1, 's', 3.89),
  (2, 11, 1, 's', 1.66),
  (3, 4, 2, 'm', 1.99),
  (3, 6, 2, 'm', 1.31),
  (3, 12, 2, 'l', 2.30);

INSERT INTO `users` (`user_fullname`, `username`, `password`) VALUES
  ('Eugene Krabs', 'krabs', '123'),
  ('SpongeBob SquarePants ', 'bob', '123');
