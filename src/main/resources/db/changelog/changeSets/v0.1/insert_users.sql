-- liquibase formatted sql

-- changeset alex2:insert_test_users
INSERT INTO users (Email, Name, PasswordHash, PhoneNumber, Role) VALUES
    ('user1@example.com', 'User 1', 'password1', '+1234567890', 'CLIENT'),
    ('user2@example.com', 'User 2', 'password2', '+9876543210', 'CLIENT'),
    ('admin@example.com', 'Admin', 'adminpass', '+1112223334', 'ADMIN');