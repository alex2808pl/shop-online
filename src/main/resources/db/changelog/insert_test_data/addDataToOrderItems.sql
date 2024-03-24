CREATE TABLE IF NOT EXISTS OrderItems (
                                      OrderItemID INT, # AUTO_INCREMENT PRIMARY KEY
                                      OrderID INT,
                                      ProductID INT,
                                      Quantity INT,
                                      PriceAtPurchase DECIMAL(8,2)
);

INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (1, 1, 1, 25, 200.00);

INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (2, 1, 2, 35, 150.00);

INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (3, 2, 1, 25, 200.00);

INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (4, 2, 3, 50, 80.00);

INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (5, 3, 4, 100, 75.00);

