CREATE TABLE users (
    id serial,
    username varchar(30),
    password varchar(100),
    catorder varchar(30),
    queue int DEFAULT 0,
    UNIQUE (username),
    PRIMARY KEY (id)
);

INSERT INTO users (username, password, catorder) VALUES ('user1', '$2y$12$2M9G1Ydm1au5cAbjNtSmcOiRrFCz5.XRnD4IQu3KjmCaWh9YFv3DO', '0 1 2 3 4 5 6 7 8 9');