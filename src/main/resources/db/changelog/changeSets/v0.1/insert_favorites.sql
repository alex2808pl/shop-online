-- liquibase formatted sql

-- changeset alex2:insert_test_favorites
INSERT INTO favorites (ProductID, UserID) VALUES
(1, 1),
(2, 1),
(3, 2),
(1, 3);