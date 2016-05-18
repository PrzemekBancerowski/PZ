CREATE TABLE main.users (
  id            INT  NOT NULL,
  email         TEXT NOT NULL,
  password_hash TEXT NOT NULL,
  role          TEXT NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE main.user_measurements (
  id               INT    NOT NULL,
  user_id          INT    NOT NULL,
  measurement_name TEXT   NOT NULL,
  operation        TEXT   NOT NULL,
  measure          TEXT   NOT NULL,
  start_time       BIGINT NOT NULL,
  end_time         BIGINT NOT NULL,
  CONSTRAINT pk_user_measurements PRIMARY KEY (id),
  CONSTRAINT fk_user_measurements_to_user FOREIGN KEY (user_id) REFERENCES main.users
);

CREATE TABLE main.measurement_sensors (
  id             INT  NOT NULL,
  measurement_id INT  NOT NULL,
  sensor_name    TEXT NOT NULL,
  CONSTRAINT pk_measurement_sensors PRIMARY KEY (id),
  CONSTRAINT fk_measurement_sensors_to_user_measurements FOREIGN KEY (measurement_id) REFERENCES main.user_measurements
);