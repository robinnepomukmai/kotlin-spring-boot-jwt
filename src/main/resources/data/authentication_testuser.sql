
INSERT INTO `user` (`id`,`email`,`password`) VALUES ('24a460e1-f20d-4556-8f66-5a82e0553886','james.dean@nepomuk.io','$2a$10$Nc8S4y/KBvHI7bWmX7qfuOLU0k8W5JHJ4o1jnkaR8OZh5AaRdMXdW');

INSERT INTO role (id, role_name, description) VALUES ('ed1db5c6-a8a3-447a-a35c-9c52492785e5', 'JDT', 'James Dean Team');

INSERT INTO user_role(user_id, role_id) VALUES ('24a460e1-f20d-4556-8f66-5a82e0553886','ed1db5c6-a8a3-447a-a35c-9c52492785e5');
