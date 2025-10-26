ALTER TABLE role
    ALTER COLUMN id SET DEFAULT gen_random_uuid();
INSERT INTO role (name, description)
VALUES
    ('DOCTOR', 'Role for medical doctors'),
    ('RELATIVE', 'Role for relatives of patients');
