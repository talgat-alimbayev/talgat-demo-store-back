CREATE TABLE IF NOT EXISTS items (
    id SERIAL NOT NULL PRIMARY KEY,
    name char(50) NOT NULL,
    price real NOT NULL);

--CREATE TABLE IF NOT EXISTS items (
--    id SERIAL NOT NULL PRIMARY KEY,
--    name char(50) NOT NULL,
--    price real NOT NULL);