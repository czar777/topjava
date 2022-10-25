DELETE
FROM user_roles;
DELETE
FROM meal;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq_meal RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meal(datetime, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 20:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 00:00:00', 'Еда на граничное значение', 100, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 1000, 100000),
       ('2020-01-31 13:00:00', 'Обед', 500, 100000),
       ('2020-01-31 20:00:00', 'Ужин', 410, 100000),
       ('2020-01-27 10:00:00', 'Завтрак', 500, 100001),
       ('2020-01-27 13:00:00', 'Обед', 1000, 100001),
       ('2020-01-27 20:00:00', 'Ужин', 500, 100001),
       ('2020-01-28 00:00:00', 'Еда на граничное значение', 100, 100001),
       ('2020-01-28 10:00:00', 'Завтрак', 1000, 100001),
       ('2020-01-28 13:00:00', 'Обед', 500, 100001),
       ('2020-01-28 20:00:00', 'Ужин', 410, 100001);