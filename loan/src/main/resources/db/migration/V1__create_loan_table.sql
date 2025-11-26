CREATE TABLE tb_loans (
    pk_id UUID PRIMARY KEY,
    fk_user_id UUID NOT NULL,
    fk_book_id UUID NOT NULL,
    dt_created_at TIMESTAMPTZ DEFAULT now(),
    dt_returned_at TIMESTAMPTZ,
    dt_deleted_at TIMESTAMPTZ
);

CREATE INDEX ix_loans_user ON tb_loans(fk_user_id);
CREATE INDEX ix_loans_book ON tb_loans(fk_book_id);
