CREATE TABLE IF NOT EXISTS tbl_room (
    id SERIAL,
    address varchar(255) NULL,
    city varchar(255)  NULL,
    state varchar(255)  NULL,
    zip_code varchar(100)  NULL,
    price numeric NOT NULL,
    PRIMARY KEY (id)
);