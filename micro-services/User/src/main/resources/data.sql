CREATE TABLE IF NOT EXISTS role (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
    );

ALTER TABLE role
    ALTER COLUMN id SET DEFAULT gen_random_uuid();

INSERT INTO role (name, description)
VALUES
    ('DOCTOR', 'Role for medical doctors'),
    ('NURSE', 'Role for nurses assisting patients')
    ON CONFLICT (name) DO NOTHING;
