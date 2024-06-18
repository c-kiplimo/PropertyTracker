CREATE TABLE IF NOT EXISTS tbl_agents
(
    agent_code      bigint       NOT NULL PRIMARY KEY,
    user_id         bigint       NOT NULL UNIQUE,
    first_name      varchar(255) NOT NULL,
    last_name       bigint(255) NOT NULL,
    full_name       varchar(255),
    msisdn          varchar(255) UNIQUE,
    email_address   varchar(255) UNIQUE,
    status          varchar(255) NOT NULL,
    onboarded       boolean,
    reason          varchar(255),
    ref_id          varchar(255),
    knew_us_through varchar(255),
    created_at      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp    NULL,
    audit_info      text
);
