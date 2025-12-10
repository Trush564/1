-- Створення бази даних addressbook (виконайте окремо в psql або pgAdmin)
-- CREATE DATABASE addressbook;

-- Використання бази даних (виконайте після підключення до БД)
-- \c addressbook

-- Створення таблиці addressbook для PostgreSQL
CREATE TABLE IF NOT EXISTS addressbook (
    id SERIAL PRIMARY KEY,
    pip VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL
);

-- Приклад вставки тестових даних (опціонально)
-- INSERT INTO addressbook (pip, phone) VALUES 
-- ('Yulia', '12231'),
-- ('Oksana', '02365'),
-- ('Petro', '465875');






