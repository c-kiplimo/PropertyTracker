CREATE TABLE IF NOT EXISTS tbl_rooms
(
    room_id      bigint       NOT NULL,
    condition    varchar(255) NOT NULL,
    apartment_id bigint       NOT NULL,
    created_at   timestamp    NOT NULL,
    updated_at   timestamp    NULL,
    audit_info   text DEFAULT '{}',
    PRIMARY KEY (room_id)
);