CREATE TABLE IF NOT EXISTS items (
    id SERIAL NOT NULL PRIMARY KEY,
    name char(50) NOT NULL,
    price real NOT NULL);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL NOT NULL PRIMARY KEY,
    delivery_address char(50) NOT NULL,
    item_ids INTEGER ARRAY[10]);