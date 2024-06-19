CREATE SEQUENCE IF NOT EXISTS user_sessions_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS tbl_user_sessions
(
    id           bigint    NOT NULL PRIMARY KEY ,
    user_id      bigint    NOT NULL,
    logged_in    boolean   NOT NULL DEFAULT FALSE,
    login_trials int       NOT NULL DEFAULT 0,
    created_at   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   timestamp NULL
);
