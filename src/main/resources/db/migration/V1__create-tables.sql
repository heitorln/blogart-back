-- Cria a tabela 'user' para armazenar informações dos usuários
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- Cria a tabela 'post' para armazenar informações dos posts
CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id INT REFERENCES "user"(id),
    created_at TIMESTAMP DEFAULT current_timestamp
);

-- Cria a tabela 'comment' para armazenar informações dos comentários
CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    user_id INT REFERENCES "user"(id),
    post_id INT REFERENCES post(id),
    created_at TIMESTAMP DEFAULT current_timestamp
);