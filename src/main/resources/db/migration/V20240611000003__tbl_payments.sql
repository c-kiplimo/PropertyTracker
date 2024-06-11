CREATE TABLE IF NOT EXISTS tbl_payments
(
    payment_id    SERIAL,
    tenant_id     bigint NOT NULL,
    balance       bigint NOT NULL,
    amount_due    bigint NULL,
    transaction_cost  bigint NOT NULL,
    payment_date  timestamp NOT NULL,
    due_date      timestamp  NOT NULL,
    transaction_type  varchar(255) NULL,
    amount_paid   bigint  NOT NULL,
    penalty_fee bigint NOT NULL,
    created_at   timestamp    NOT NULL,
    updated_at   timestamp    NULL,
    audit_info   text DEFAULT '{}',
    PRIMARY KEY (payment_id)


);