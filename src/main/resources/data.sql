-- Inserindo usuário inicial (user: zeus@email.com | pass: 12345678)
INSERT INTO users (
    email,
    name,
    password,
    id
) VALUES ('zeus@email.com','Zeus','$2a$10$SSp0LWqietQIyQrihq16LeGwQwXYmOUHQxTrxtjxYHZqG22zdbav.',DEFAULT);

-- Inserindo tarefas
INSERT INTO tasks (
    completed,
    description,
    title,
    user_id,
    id
) VALUES (false,'Descrição da task','Tarefa (1)',1,DEFAULT);

INSERT INTO tasks (
    completed,
    description,
    title,
    user_id,
    id
) VALUES (false,'Descrição da task','Tarefa (2)',1,DEFAULT);

INSERT INTO tasks (
    completed,
    description,
    title,
    user_id,
    id
) VALUES (false,'Descrição da task','Tarefa (3)',1,DEFAULT);

INSERT INTO tasks (
    completed,
    description,
    title,
    user_id,
    id
) VALUES (false,'Descrição da task','Tarefa (4)',1,DEFAULT);