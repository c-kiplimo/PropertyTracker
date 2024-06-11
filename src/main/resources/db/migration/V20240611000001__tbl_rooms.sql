CREATE TABLE IF NOT EXISTS tbl_room (
    room_id SERIAL,
    condition varchar(255) NOT NULL,
    apartment_id bigint NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NULL,
    audit_info text DEFAULT '{}',
    PRIMARY KEY (room_id)
);