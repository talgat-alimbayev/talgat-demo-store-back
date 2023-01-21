TRUNCATE TABLE items_store;
INSERT INTO items_store (name, price) VALUES ('сметана', 615.5);
INSERT INTO items_store (name, price) VALUES ('хлеб', 80.5);
INSERT INTO items_store (name, price) VALUES ('молоко', 302.2);
INSERT INTO items_store (name, price) VALUES ('йогурт', 380.4);
INSERT INTO items_store (name, price) VALUES ('рис', 104.15);
INSERT INTO items_store (name, price) VALUES ('гречка', 280.5);

TRUNCATE TABLE orders CASCADE;