--liquibase formatted sql
--changeset skorik:creation-users-table
CREATE TABLE users
(
    id   UUID        NOT NULL,
    name VARCHAR(25) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);
--rollback DROP TABLE users