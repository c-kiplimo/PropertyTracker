CREATE TABLE IF NOT EXISTS tbl_payments
(
    payment_id    bigint NOT NULL,
    tenant_id     bigint NOT NULL,
    balance       numeric  NOT NULL,
    amount_due     numeric NULL,
    transaction_cost  numeric NOT NULL,
    payment_date  timestamp NOT NULL,
    due_date      timestamp  NOT NULL,
    transaction_type  varchar(255) NULL,
    amount_paid   numeric  NOT NULL,
    penalty_fee numeric NOT NULL,
    created_at   timestamp    NOT NULL,
    updated_at   timestamp    NULL,
    audit_info   text DEFAULT '{}',
    PRIMARY KEY (payment_id)


);