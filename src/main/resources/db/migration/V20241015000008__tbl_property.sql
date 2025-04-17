CREATE SEQUENCE IF NOT EXISTS property-sequence START WITH 1 INCREMENT BY 1;

 CREATE TABLE IF NOT EXISTS tbl_property
 (
 property_id   bigint    NOT NULL
 property_name  varchar(255)  NOT NULL
 description    varchar(255)  NOT NULL
 price          numeric        NOT NULL
 address        varchar(255)  NOT NULL
 location       varchar(255)  NOT NULL
 created_at     timestamp     NOT NULL
update_at       timestamp     NULL
audit_info      text DEFAULT'{}'
PRIMARY KEY (property_id)
 )