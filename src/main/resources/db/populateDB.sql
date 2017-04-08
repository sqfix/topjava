DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (meal_id, user_id, description, calories, time) VALUES
  (1, 1, 'Завтрак', 500, '2017-03-08 10:00:00'),
  (2, 1, 'Обед', 1000, '2017-03-08 13:00:00'),
  (3, 1, 'Ужин', 500, '2017-03-08 20:00:00'),
  (4, 1, 'Завтрак', 1000, '2017-03-08 10:00:00'),
  (5, 1, 'Обед', 500, '2017-03-08 13:00:00'),
  (6, 1, 'Ужин', 510, '2017-03-08 20:00:00');