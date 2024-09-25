ALTER TABLE person.addresses
    ALTER COLUMN updated DROP NOT NULL,
    ALTER COLUMN archived DROP NOT NULL;

ALTER TABLE person.users
    ALTER COLUMN updated DROP NOT NULL,
    ALTER COLUMN verified_at DROP NOT NULL,
    ALTER COLUMN archived_at DROP NOT NULL;

ALTER TABLE person.merchants
    ALTER COLUMN updated DROP NOT NULL,
    ALTER COLUMN verified_at DROP NOT NULL,
    ALTER COLUMN archived_at DROP NOT NULL;

ALTER TABLE person.merchant_members
    ALTER COLUMN updated DROP NOT NULL;

ALTER TABLE person.individuals
    ALTER COLUMN updated DROP NOT NULL,
    ALTER COLUMN verified_at DROP NOT NULL,
    ALTER COLUMN archived_at DROP NOT NULL;

ALTER TABLE person.verification_statuses
    ALTER COLUMN updated DROP NOT NULL;