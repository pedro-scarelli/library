CREATE TABLE tb_addresses (
    pk_id UUID PRIMARY KEY,
    st_cep VARCHAR(8) NOT NULL,
    st_logradouro VARCHAR(255) NOT NULL,
    st_complemento VARCHAR(255),
    st_bairro VARCHAR(100) NOT NULL,
    st_localidade VARCHAR(100) NOT NULL,
    st_uf VARCHAR(2) NOT NULL,
    st_numero VARCHAR(20),
    dt_created_at TIMESTAMPTZ DEFAULT now() NOT NULL
);

ALTER TABLE tb_users
    ADD COLUMN fk_address_id UUID;

ALTER TABLE tb_users
    ADD CONSTRAINT fk_users_address
    FOREIGN KEY (fk_address_id)
    REFERENCES tb_addresses(pk_id)
    ON DELETE SET NULL;

CREATE INDEX ix_users_address_id ON tb_users(fk_address_id);

CREATE INDEX ix_addresses_cep ON tb_addresses(st_cep);

CREATE INDEX ix_addresses_location ON tb_addresses(st_localidade, st_uf);