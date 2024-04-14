-- liquibase formatted sql

-- changeset alex2:insert_test_cartitems
insert into cartitems (CartID,Quantity,ProductID)
values (1,5,1),
       (2,1,2),
       (3,2,3)