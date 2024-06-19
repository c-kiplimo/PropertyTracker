CREATE SEQUENCE IF NOT EXISTS users_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS tbl_users
(
    id                         bigint       NOT NULL PRIMARY KEY,
    first_name                 varchar(255) NOT NULL,
    last_name                  varchar(255) NOT NULL,
    full_name                  varchar(255) NOT NULL,
    email                      varchar(255) UNIQUE,
    msisdn                     varchar(255) UNIQUE,
    username                   varchar(255) NOT NULL UNIQUE,
    password                   varchar(255) NOT NULL,
    activation_key             varchar(255),
    activation_key_expiry_date timestamp,
    show_important_info        timestamp,
    created_at                 timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                 timestamp    NULL,
    user_role                  varchar(255) NOT NULL,
    agent_code                 bigint,
    locked                     boolean               DEFAULT FALSE,
    enabled                    boolean               DEFAULT FALSE
);