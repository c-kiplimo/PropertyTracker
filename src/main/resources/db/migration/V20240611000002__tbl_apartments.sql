CREATE TABLE IF NOT EXISTS tbl_apartments
(
    apartment_id      bigint       NOT NULL,
    apartment_name    varchar(255) NOT NULL,
    agent_code bigint       NOT NULL,
    address varchar(255)    NOT NULL,
    location varchar(255)   NOT NULL,
    created_at   timestamp    NOT NULL,
    updated_at   timestamp    NULL,
    audit_info   text DEFAULT '{}',
    PRIMARY KEY (apartment_id)
);