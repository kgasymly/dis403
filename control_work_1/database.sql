CREATE DATABASE library;

\c library;

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    year INTEGER NOT NULL,
    genre VARCHAR(100) NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

INSERT INTO books (title, author, isbn, year, genre, available) VALUES
('Война и мир', 'Лев Толстой', '978-5-389-07435-4', 1869, 'Роман', TRUE),
('Преступление и наказание', 'Фёдор Достоевский', '978-5-699-40431-8', 1866, 'Роман', TRUE),
('Мастер и Маргарита', 'Михаил Булгаков', '978-5-389-04829-4', 1967, 'Роман', FALSE),
('1984', 'Джордж Оруэлл', '978-5-17-080115-2', 1949, 'Антиутопия', TRUE),
('Гарри Поттер и философский камень', 'Джоан Роулинг', '978-5-389-07435-3', 1997, 'Фэнтези', TRUE);