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
    order_id INT);

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL NOT NULL PRIMARY KEY,
    delivery_address char(50) NOT NULL,
    delivery_name char(50) NOT NULL,
    email char(50) NOT NULL,
    comment char(50));

ALTER TABLE items_order ADD CONSTRAINT FK_order FOREIGN KEY (order_id) REFERENCES orders(id);