-- Insert orders into the 'orders' table
INSERT INTO orders (order_status, total_price) VALUES
('ORDERED', 150.75),
('PENDING', 200.00),
('CANCELLED', 0.00),
('PENDING', 120.50),
('ORDERED', 300.25),
('ORDERED', 175.00),
('CANCELLED', 0.00),
('ORDERED', 450.00),
('PENDING', 220.80),
('ORDERED', 180.00);

-- Insert order items into the 'order_items' table associated with orders
-- Assuming order IDs generated are 1 to 10

INSERT INTO order_items (product_id, quantity, order_id) VALUES
(101, 2, 1),
(102, 1, 1),
(103, 3, 2),
(104, 1, 2),
(105, 5, 3),
(106, 1, 4),
(107, 2, 4),
(108, 3, 5),
(109, 4, 6),
(110, 2, 7),
(111, 1, 8),
(112, 6, 8),
(113, 2, 9),
(114, 1, 9),
(115, 3, 10);