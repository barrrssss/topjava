DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (id, description, date_time, calories, user_id)
VALUES (1, 'Завтрак', '2020-01-30 10:00:00', 500, 100000),
       (2, 'Обед', '2020-01-30 13:00:00', 1000, 100000),
       (3, 'Ужин', '2020-01-30 20:00:00', 500, 100000),
       (4, 'Еда на граничное значение', '2020-01-31 00:00:00', 100, 100000),
       (5, 'Завтрак', '2020-01-31 10:00:00', 1000, 100000),
       (6, 'Обед', '2020-01-31 13:00:00', 500, 100000),
       (7, 'Ужин', '2020-01-31 20:00:00', 410, 100000),

       (11, 'Завтрак', '2020-02-1 8:00:00', 410, 100001),
       (12, 'Обед', '2020-02-1 14:00:00', 1000, 100001);
