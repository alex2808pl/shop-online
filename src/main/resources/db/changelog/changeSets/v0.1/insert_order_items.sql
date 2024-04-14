-- liquibase formatted sql

-- changeset alex2:insert_test_orderitems
INSERT INTO OrderItems (OrderID, ProductID, Quantity, PriceAtPurchase)
VALUES (1, 1, 100, 200.00),
       (2, 2, 200, 500.00),
       (3, 3, 50, 100.00);