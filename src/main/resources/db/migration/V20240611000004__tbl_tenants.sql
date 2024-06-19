CREATE SEQUENCE IF NOT EXISTS tenants_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS tbl_tenants
(
    tenant_id   bigint NOT NULL,
    first_name    varchar(255) NOT NULL,
    last_name    varchar(255) NOT NULL,
    address    varchar(255) NOT NULL,
    phone_number   varchar(255) NOT NULL,
    kra_pin   varchar(255)   NOT NULL,
    email   varchar(255) NOT NULL,
    id_number   varchar(255) NOT NULL,
    passport_number varchar(255)    NOT NULL,
    created_at   timestamp    NOT NULL,
    updated_at   timestamp    NULL,
    audit_info   text DEFAULT '{}',
    PRIMARY KEY (tenant_id)
);