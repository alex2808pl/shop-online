-- liquibase formatted sql

-- changeset alex2:insert_orders
INSERT INTO orders (UserID, CreatedAt, DeliveryAddress, ContactPhone, DeliveryMethod, Status, UpdatedAt)
VALUES
       (1, '2024-03-23', 'Hirokustr.25','0175-245-54-543','Direct','CREATED','2024-03-23'),
       (2, '2024-03-23', 'Hirokustr.26','0175-245-54-544','Direct','PAID','2024-03-23'),
       (3, '2024-03-23', 'Hirokustr.26','0175-245-54-544','Direct','PAID','2024-03-23');