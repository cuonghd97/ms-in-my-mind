-- Create a database named shopping_cart
CREATE DATABASE IF NOT EXISTS shopping_cart;

-- Use the shopping_cart database
USE shopping_cart;

-- Create a table to store products
CREATE TABLE IF NOT EXISTS products (
    id VARCHAR(40) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    descriptions TEXT,
    quantity int,
    image_file_name VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
);

-- Create a table to store users
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(40) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

-- Create a table to store cart items
CREATE TABLE IF NOT EXISTS carts (
    id VARCHAR(40) PRIMARY KEY,
    user_id VARCHAR(40) NOT NULL,
    product_id VARCHAR(40) NOT NULL,
    quantity INT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

-- Create a table to store orders
CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY,
    user_id VARCHAR(40) NOT NULL,
    date DATETIME NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
    
);

-- Create a table to store order items
CREATE TABLE IF NOT EXISTS order_items (
    id VARCHAR(40) PRIMARY KEY,
    order_id VARCHAR(40) NOT NULL,
    product_id VARCHAR(40) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

-- Create a table to store payments
CREATE TABLE IF NOT EXISTS payments (
    id INT PRIMARY KEY,
    order_id VARCHAR(40) NOT NULL,
    method VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    confirmed BOOLEAN NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

