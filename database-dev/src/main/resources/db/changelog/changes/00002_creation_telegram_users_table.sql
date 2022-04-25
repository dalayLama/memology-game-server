--liquibase formatted sql
--changeset skorik:creation-telegram-users-table
CREATE TABLE telegram_users
(
    id   BIGINT        NOT NULL,
    inner_user_id UUID NOT NULL
        constraint user_id_fk
            references users (id)
            on update cascade on delete cascade,
    CONSTRAINT pk_telegram_users PRIMARY KEY (id)
);
--rollback DROP TABLE telegram_users