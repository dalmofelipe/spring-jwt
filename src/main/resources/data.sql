INSERT INTO tb_roles (name) values ('ADMIN');

INSERT INTO tb_users (name, username, email, password)
VALUES ('ADMIN', 'admin', 'admin@email.com', '$2a$10$kcp6MrSrJTs9mGVEtHEIbuhaFYu9PG04J1iG2ixrpY.aQtw1qiuU.');

INSERT INTO TB_USERS_ROLES (ROLES_ID, USER_ID) VALUES (1, 1);