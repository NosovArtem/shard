CREATE TABLE persons
(
    id       BIGSERIAL PRIMARY KEY NOT NULL,
    version  BIGINT             NOT NULL,
    username VARCHAR(255)       NOT NULL
);

