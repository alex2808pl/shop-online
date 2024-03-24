CREATE TABLE IF NOT EXISTS Orders (
                                      OrderID INT, # AUTO_INCREMENT PRIMARY KEY
                                      UserID INT,
                                      CreatedAt TIMESTAMP,
                                      DeliveryAddress VARCHAR(50),
                                      ContactPhone VARCHAR(50),
                                      DeliveryMethod VARCHAR(50),
                                      Status VARCHAR(50) NOT NULL,
                                      UpdetedAt TIMESTAMP
);

INSERT INTO Orders (OrderID, UserID, CreatedAt, DeliveryAddress, ContactPhone, DeliveryMethod, Status, UpdetedAt)
            VALUES (1, 1, '2024-03-23', 'Hirokustr.25','0175-245-54-543','Direct','OPEN','2024-03-23');

INSERT INTO Orders (OrderID, UserID, CreatedAt, DeliveryAddress, ContactPhone, DeliveryMethod, Status, UpdetedAt)
            VALUES (2, 2, '2024-03-23', 'Hauptstr.15','0135-445-44-743','Direct','OPEN','2024-03-23');

INSERT INTO Orders (OrderID, UserID, CreatedAt, DeliveryAddress, ContactPhone, DeliveryMethod, Status, UpdetedAt)
            VALUES (3, 3, '2024-03-23', 'Onionstr.7','0173-145-24-643','Direct','OPEN','2024-03-23');


