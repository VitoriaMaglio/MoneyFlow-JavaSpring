CREATE TABLE USERS (
                    id bigint PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    age INT,
                    cpf VARCHAR(14) UNIQUE NOT NULL)