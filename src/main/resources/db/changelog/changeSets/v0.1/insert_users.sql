-- liquibase formatted sql

-- changeset alex2:insert_test_users
INSERT INTO Users (Email, Name, PasswordHash, PhoneNumber, Role) VALUES
    ('user1@example.com', 'User 1', '$2a$10$I7r0aNpBBMMOi5IYDr//l.4I37aLoXWNaf7oGXH0SbvCA5NkaaiwK', '+1234567890', 'CLIENT'),
    ('user2@example.com', 'User 2', '$2a$10$I7r0aNpBBMMOi5IYDr//l.4I37aLoXWNaf7oGXH0SbvCA5NkaaiwK', '+9876543210', 'CLIENT'),
    ('admin@example.com', 'Admin', '$2a$10$I7r0aNpBBMMOi5IYDr//l.4I37aLoXWNaf7oGXH0SbvCA5NkaaiwK', '+1112223334', 'ADMIN');