CREATE TABLE persons
(
    id       VARCHAR(36) PRIMARY KEY NOT NULL,
    version  BIGINT             NOT NULL,
    username VARCHAR(255)       NOT NULL
);

