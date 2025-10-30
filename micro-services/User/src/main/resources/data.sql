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

CREATE TABLE IF NOT EXISTS app_user  (
      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
      email VARCHAR(255) NOT NULL UNIQUE,
      password VARCHAR(255),
      role_id UUID REFERENCES role(id),
      first_name VARCHAR(255) NOT NULL,
      last_name VARCHAR(255) NOT NULL,
      bracelet_id VARCHAR(255) UNIQUE,
      doctor_id UUID
);

INSERT INTO app_user (id, email, password, role_id, first_name, last_name, bracelet_id, doctor_id)
SELECT
    gen_random_uuid(), 'doctor1@example.com',
    '$2a$10$cfhHM5E50eZoLjaNEHPy.O/hxpROzA5iM3yDv6NGuYM78BfTd7YLC', -- bcrypt("password")
    r.id, 'Alice', 'Dupont', NULL, NULL
FROM role r WHERE r.name = 'DOCTOR'
    ON CONFLICT (email) DO NOTHING;

INSERT INTO app_user (id, email, password, role_id, first_name, last_name, bracelet_id, doctor_id)
SELECT
    gen_random_uuid(), 'doctor2@example.com',
    '$2a$10$cfhHM5E50eZoLjaNEHPy.O/hxpROzA5iM3yDv6NGuYM78BfTd7YLC',
    r.id, 'Marc', 'Lefevre', NULL, NULL
FROM role r WHERE r.name = 'DOCTOR'
    ON CONFLICT (email) DO NOTHING;

-- === Patients (users assigned to a doctor) ===
INSERT INTO app_user (id, email, password, first_name, last_name, bracelet_id, doctor_id)
VALUES
    (gen_random_uuid(), 'emma.martin@example.com',
     '$2a$10$cfhHM5E50eZoLjaNEHPy.O/hxpROzA5iM3yDv6NGuYM78BfTd7YLC',
     'Emma', 'Martin', 'BRP001',
     (SELECT id FROM app_user WHERE email = 'doctor1@example.com'))
    ON CONFLICT (email) DO NOTHING;

INSERT INTO app_user (id, email, password, first_name, last_name, bracelet_id, doctor_id)
VALUES
    (gen_random_uuid(), 'lucas.bernard@example.com',
     '$2a$10$cfhHM5E50eZoLjaNEHPy.O/hxpROzA5iM3yDv6NGuYM78BfTd7YLC',
     'Lucas', 'Bernard', 'BRP002',
     (SELECT id FROM app_user WHERE email = 'doctor1@example.com'))
    ON CONFLICT (email) DO NOTHING;

INSERT INTO app_user (id, email, password, first_name, last_name, bracelet_id, doctor_id)
VALUES
    (gen_random_uuid(), 'chloe.durand@example.com',
     '$2a$10$cfhHM5E50eZoLjaNEHPy.O/hxpROzA5iM3yDv6NGuYM78BfTd7YLC',
     'Chloé', 'Durand', 'BRP003',
     (SELECT id FROM app_user WHERE email = 'doctor2@example.com'))
    ON CONFLICT (email) DO NOTHING;