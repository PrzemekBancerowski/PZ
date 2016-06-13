CREATE TABLE main.users (
  id            INT  NOT NULL,
  email         TEXT NOT NULL,
  password_hash TEXT NOT NULL,
  role          TEXT NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);
