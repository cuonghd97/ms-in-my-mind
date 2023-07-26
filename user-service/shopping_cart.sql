-- To create a shopping cart system database, you can use the following SQL statements:

-- Create a database named shopping_cart
CREATE DATABASE shopping_cart;

-- Use the shopping_cart database
USE shopping_cart;

-- Create a table to store products
CREATE TABLE products (
id VARCHAR(255) PRIMARY KEY,
name VARCHAR(255) NOT NULL,
price DECIMAL(10,2) NOT NULL,
descriptions TEXT,
quantity int,
image_file_name VARCHAR(255),
created_at DATETIME,
updated_at DATETIME
);

-- Create a table to store users
CREATE TABLE users (
id INT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL
);

-- Create a table to store cart items
CREATE TABLE cart_items (
id INT PRIMARY KEY,
user_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Create a table to store orders
CREATE TABLE orders (
id INT PRIMARY KEY,
user_id INT NOT NULL,
date DATE NOT NULL,
status VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create a table to store order items
CREATE TABLE order_items (
id INT PRIMARY KEY,
order_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
price DECIMAL(10,2) NOT NULL,
FOREIGN KEY (order_id) REFERENCES orders(id),
FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Create a table to store payments
CREATE TABLE payments (
id INT PRIMARY KEY,
order_id INT NOT NULL,
method VARCHAR(255) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
confirmed BOOLEAN NOT NULL,
FOREIGN KEY (order_id) REFERENCES orders(id)
);

