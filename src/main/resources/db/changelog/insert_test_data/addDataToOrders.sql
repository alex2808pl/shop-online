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

# INSERT INTO Orders (OrderId, UserID, Created_At, Delivery_Address, Contact_Phone, Delivery_Method, Status, Updeted_At)
# VALUES (2, 'FKpnm1eeupqm4tykds7k3okqegv', '2024-03-23', 'Hirokustr.25','0175-245-54-543','Direct','OPEN','2024-03-23');


