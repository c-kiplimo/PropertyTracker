CREATE SEQUENCE IF NOT EXISTS room_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS tbl_rooms
(
    room_id      bigint       NOT NULL,
    condition    varchar(255) NOT NULL,
    room_name    varchar(255) NOT NULL,
    apartment_id bigint       NOT NULL,
    created_at   timestamp    NOT NULL,
    updated_at   timestamp    NULL,
    audit_info   text DEFAULT '{}',
    PRIMARY KEY (room_id)
);