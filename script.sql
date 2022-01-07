CREATE TABLE cats (
    id serial PRIMARY KEY,
    name varchar(30),
    picture varchar(50),
    popularity integer DEFAULT 0
);

INSERT INTO cats (name, picture) VALUES ('Куся', 'cat10.jpg'),
                                        ('Барсик', 'cat1.jpg'),
                                        ('Мурзик', 'cat2.jpg'),
                                        ('Нюша', 'cat9.jpg'),
                                        ('Декстер', 'cat3.jpg'),
                                        ('Глаша', 'cat4.jpg'),
                                        ('Ууууу-котъ', 'cat5.jpg'),
                                        ('Тосик', 'cat6.jpg'),
                                        ('Соус', 'cat7.jpg'),
                                        ('Муся', 'cat8.jpg');

CREATE TABLE IF NOT EXISTS users (
                                     id serial,
                                     username varchar(30),
                                     password varchar(100),
                                     catorder varchar(30),
                                     queue int DEFAULT 0,
                                     UNIQUE (username),
                                     PRIMARY KEY (id)
);

INSERT INTO users (username, password, catorder) VALUES ('user1', '$2y$12$2M9G1Ydm1au5cAbjNtSmcOiRrFCz5.XRnD4IQu3KjmCaWh9YFv3DO', '1 2 3 4 5 6 7 8 9 10');


