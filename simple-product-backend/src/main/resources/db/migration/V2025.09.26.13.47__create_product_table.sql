-- dropping sequence and table for being able to rerun this flyway
DROP SEQUENCE IF EXISTS product_seq CASCADE;
DROP TABLE IF EXISTS product;

CREATE SEQUENCE product_seq START WITH 1000 INCREMENT BY 10;
CREATE TABLE product (
    id BIGINT NOT NULL,
    name VARCHAR(25) NOT NULL,
    created_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    created_by BIGINT NOT NULL,
    updated_by BIGINT NOT NULL,
    PRIMARY KEY (id)
);
