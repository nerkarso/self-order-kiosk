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
  ('Krabby Patty Value Meal', 15.99, 'https://b.zmtcdn.com/data/dish_photos/452/f6297bbafae110b76453ca78a2cf6452.jpg', 1),
  ('Seaweed Wrap', 12.50, 'https://b.zmtcdn.com/data/dish_photos/1c8/2111a3b479c81a8d738cf42ef37f31c8.jpg', 1),
  ('Patrik Star Meal', 19.99, 'https://b.zmtcdn.com/data/dish_photos/a4e/bf134f09c17995a4ec54b55b32928a4e.jpg', 1),
  ('Spongy Fries', 3.99, 'https://b.zmtcdn.com/data/dish_photos/671/a72661987ae2d3efa3c336e7fdd40671.jpg', 2),
  ('Pirate Snack', 7.99, 'https://b.zmtcdn.com/data/dish_photos/6b8/28ef02f89c1bfb24a39fd347780fe6b8.jpg', 2),
  ('Mrs Puff Pastry', 3.75, 'https://b.zmtcdn.com/data/dish_photos/91f/11e3d724c0fa28bf53b6b93180dad91f.png', 2),
  ('Cream Cookies', 2.50, 'https://b.zmtcdn.com/data/dish_photos/6a1/a8b772965acc98c27e900046eed366a1.jpg', 3),
  ('Sandy Brownie', 3.50, 'https://b.zmtcdn.com/data/dish_photos/3fd/9f787bf19d49e2b3c20e558ffb5e73fd.jpg', 3),
  ('Strawberry Soft Serve', 6.25, 'https://b.zmtcdn.com/data/dish_photos/32e/cc1d9c77ced500c2b1df7c019b0c632e.jpg', 3),
  ('Squid Ink Drink', 5.40, 'https://b.zmtcdn.com/data/dish_photos/441/4a5318a0ce6e68ae345c91a0f3139441.png', 4),
  ('Orange Juice', 4.00, 'https://b.zmtcdn.com/data/dish_photos/ca5/3891a51f9ba71bd9f37ee1616bcceca5.png', 4),
  ('Cold Coffee', 8.20, 'https://b.zmtcdn.com/data/dish_photos/1fe/715ff2c6ed6b30b5b26fbeca40d4c1fe.jpg', 4),
  ('Filet-O-Fish Double Patty Meal', 16.50, 'https://b.zmtcdn.com/data/dish_photos/64d/f35d02b08a7217af87c914de5afe564d.jpg', 1),
  ('Shark Strips', 11.75, 'https://b.zmtcdn.com/data/dish_photos/fd7/cb17d44f6a9cd3de58076a2ceb4e0fd7.jpg', 2),
  ('Chocolate Soft Serve', 6.25, 'https://b.zmtcdn.com/data/dish_photos/6dd/d40c1df21491e53d782803bf3e56d6dd.jpg', 3),
  ('Polar Ice Tea', 5.99, 'https://b.zmtcdn.com/data/dish_photos/2c4/fda620c078759d62dc052b60c7c682c4.jpg', 4);

INSERT INTO `orders` (`order_eating_location`, `order_payment_method`, `order_status`, `order_date`) VALUES
  ('EAT_IN', 'PAY_HERE', 1, '2020-10-01 12:00:00'),
  ('TAKE_OUT', 'PAY_HERE', 0, '2020-10-03 13:00:00'),
  ('TAKE_OUT', 'PAY_COUNTER', 0, '2020-10-06 14:00:00');

INSERT INTO `order_details` (`order_id`, `item_id`, `item_quantity`, `item_size`, `item_order_price`) VALUES
  (1, 1, 1, 'MEDIUM', 5.15),
  (1, 10, 1, 'MEDIUM', 1.40),
  (2, 2, 1, 'SMALL', 3.89),
  (2, 11, 1, 'SMALL', 1.66),
  (3, 4, 2, 'MEDIUM', 1.99),
  (3, 6, 2, 'MEDIUM', 1.31),
  (3, 12, 2, 'LARGE', 2.30);

INSERT INTO `users` (`user_fullname`, `username`, `password`) VALUES
  ('Eugene Krabs', 'krabs', '123'),
  ('SpongeBob SquarePants ', 'bob', '123');
