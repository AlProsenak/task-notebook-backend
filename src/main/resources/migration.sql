CREATE TABLE IF NOT EXISTS tbl_task (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    accountable VARCHAR(50),
    description VARCHAR(255) NOT NULL,
    deadline DATE,
    completion_date DATE,
    completed BOOLEAN NOT NULL
);
