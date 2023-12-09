INSERT INTO tb_roles (name) values ('ADMIN');

INSERT INTO tb_users (name, username, email, password)
VALUES ('Dalmo', 'dalmo', 'dalmo@email.com', '123123');

INSERT INTO TB_USERS_ROLES (ROLES_ID, USER_ID) VALUES (1, 1);