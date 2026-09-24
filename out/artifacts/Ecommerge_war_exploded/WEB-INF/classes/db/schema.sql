CREATE DATABASE IF NOT EXISTS E_com
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE E_com;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone VARCHAR(20) NOT NULL,
                       role ENUM('CUSTOMER', 'ADMIN') NOT NULL DEFAULT 'CUSTOMER'
);

CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE,
                            description VARCHAR(500)
);

CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(200) NOT NULL,
                          price DECIMAL(15, 2) NOT NULL,
                          quantity INT NOT NULL DEFAULT 0,
                          category_id INT NOT NULL,

                          CONSTRAINT chk_product_price CHECK (price >= 0),
                          CONSTRAINT chk_product_quantity CHECK (quantity >= 0),
                          CONSTRAINT fk_product_category
                              FOREIGN KEY (category_id) REFERENCES categories(id)
);