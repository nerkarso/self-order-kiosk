DELETE FROM `categories`;
DELETE FROM `items`;
DELETE FROM `orders`;
DELETE FROM `order_items`;
DELETE FROM `users`;

ALTER TABLE `categories` AUTO_INCREMENT = 1;
ALTER TABLE `items` AUTO_INCREMENT = 1;
ALTER TABLE `orders` AUTO_INCREMENT = 1;
ALTER TABLE `order_items` AUTO_INCREMENT = 1;
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
  ('McChicken Extra Value Meal', 214, 'https://b.zmtcdn.com/data/dish_photos/452/f6297bbafae110b76453ca78a2cf6452.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 1),
  ('McSpicy Chicken Wrap', 189, 'https://b.zmtcdn.com/data/dish_photos/1c8/2111a3b479c81a8d738cf42ef37f31c8.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 1),
  ('McChicken Happy Meal', 216, 'https://b.zmtcdn.com/data/dish_photos/a4e/bf134f09c17995a4ec54b55b32928a4e.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 1),
  ('Fries', 70, 'https://b.zmtcdn.com/data/dish_photos/671/a72661987ae2d3efa3c336e7fdd40671.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 2),
  ('McNuggets', 120, 'https://b.zmtcdn.com/data/dish_photos/6b8/28ef02f89c1bfb24a39fd347780fe6b8.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 2),
  ('Pizza McPuff', 31, 'https://b.zmtcdn.com/data/dish_photos/91f/11e3d724c0fa28bf53b6b93180dad91f.png?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 2),
  ('McFlurry Oreo',77, 'https://b.zmtcdn.com/data/dish_photos/6a1/a8b772965acc98c27e900046eed366a1.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 3),
  ('Chocolate Brownie', 79, 'https://b.zmtcdn.com/data/dish_photos/3fd/9f787bf19d49e2b3c20e558ffb5e73fd.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 3),
  ('Strawberry Soft Serve', 65, 'https://b.zmtcdn.com/data/dish_photos/32e/cc1d9c77ced500c2b1df7c019b0c632e.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 3),
  ('Coke', 59, 'https://b.zmtcdn.com/data/dish_photos/441/4a5318a0ce6e68ae345c91a0f3139441.png?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 4),
  ('Minute Maid Pulpy Orange', 66, 'https://b.zmtcdn.com/data/dish_photos/ca5/3891a51f9ba71bd9f37ee1616bcceca5.png?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 4),
  ('Cold Coffee', 64, 'https://b.zmtcdn.com/data/dish_photos/1fe/715ff2c6ed6b30b5b26fbeca40d4c1fe.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A', 4);

INSERT INTO `orders` (`order_prep_time`) VALUES
  (30),
  (15),
  (40);

INSERT INTO `order_items` (`order_id`, `item_id`, `item_quantity`, `item_size`, `item_order_price`) VALUES
  (1, 1, 1, 'm', 214),
  (1, 10, 1, 'm', 59),
  (2, 2, 1, 's', 216),
  (2, 11, 1, 's', 66),
  (3, 4, 2, 'm', 70),
  (3, 5, 2, 'm', 120),
  (3, 12, 2, 'l', 64);

INSERT INTO `users` (`user_fullname`, `username`, `password`) VALUES
  ('Richard McDonald', 'mcdonald', '123'),
  ('Colonel Sanders', 'sanders', '123');
