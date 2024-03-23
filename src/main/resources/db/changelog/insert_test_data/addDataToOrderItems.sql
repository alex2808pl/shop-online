CREATE TABLE IF NOT EXISTS OrderItems (
                                      OrderItemID INT, # AUTO_INCREMENT PRIMARY KEY
                                      OrderID INT,
                                      ProductID INT,
                                      Quantity INT,
                                      PriceAtPurchase DECIMAL(8,2)
);

INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (1, 1, 1, 100, 200.00);

