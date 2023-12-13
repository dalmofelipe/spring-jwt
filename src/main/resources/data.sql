INSERT INTO tb_roles (name) values ('ADMIN');

-- $2a$10$YLdTQgW0akzz1bo9zq/exuOv/DXUqwcKw1e84laGhYIYDP/y4WDJ. = '123123'
INSERT INTO tb_users (name, username, email, password)
VALUES ('ADMIN', 'admin', 'admin@email.com', '$2a$10$YLdTQgW0akzz1bo9zq/exuOv/DXUqwcKw1e84laGhYIYDP/y4WDJ.');

INSERT INTO TB_USERS_ROLES (ROLES_ID, USER_ID) VALUES (1, 1);