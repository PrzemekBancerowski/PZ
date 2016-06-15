CREATE SEQUENCE main.users_seq
START 1
INCREMENT 1
NO MAXVALUE
CACHE 1;


CREATE TABLE main.users (
  id            INT  NOT NULL DEFAULT nextval('main.users_seq'),
  email         TEXT NOT NULL,
  password_hash TEXT NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);
