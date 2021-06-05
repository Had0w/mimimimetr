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