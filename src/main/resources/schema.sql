DROP TABLE IF EXISTS items_store;
CREATE TABLE IF NOT EXISTS items_store (
    id SERIAL NOT NULL PRIMARY KEY,
    name char(50) NOT NULL,
    price real NOT NULL);

DROP TABLE IF EXISTS items_order;
CREATE TABLE IF NOT EXISTS items_order (
    id SERIAL NOT NULL PRIMARY KEY,
    name char(50) NOT NULL,
    price real NOT NULL,
    order_id INT NOT NULL);

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL NOT NULL PRIMARY KEY,
    delivery_address char(50) NOT NULL,
    delivery_name char(50) NOT NULL,
    email char(50) NOT NULL,
    comment char(50),
    user_id INT NOT NULL);

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id SERIAL NOT NULL PRIMARY KEY,
    username char(50) NOT NULL,
    password char(60) NOT NULL,
    full_name char(50) NOT NULL,
    address char(50) NOT NULL,
    email char(50) NOT NULL,
    role char(50) NOT NULL);

ALTER TABLE items_order ADD CONSTRAINT FK_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE;
ALTER TABLE orders ADD CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;