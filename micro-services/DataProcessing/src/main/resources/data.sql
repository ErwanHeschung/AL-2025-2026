-- Insert heart rate metrics
INSERT INTO heart_metrics (id, timestamp, bracelet_id, value) VALUES
    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP001', 72.5),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP001', 75.3),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP001', 68.9),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP001', 70.2),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP001', 73.1),

    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP002', 80.4),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP002', 82.7),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP002', 79.5),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP002', 81.2),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP002', 78.9),

    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP003', 65.8),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP003', 67.2),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP003', 64.5),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP003', 66.9),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP003', 68.1);

-- Insert blood oxygen metrics
INSERT INTO blood_oxygen_metrics (id, timestamp, bracelet_id, value) VALUES
    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP001', 98),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP001', 97),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP001', 99),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP001', 98),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP001', 97),

    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP002', 96),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP002', 95),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP002', 97),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP002', 96),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP002', 98),

    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP003', 99),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP003', 98),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP003', 99),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP003', 97),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP003', 98);

-- Insert accelerometer metrics
INSERT INTO accelerometer_metrics (id, timestamp, bracelet_id, x, y, z) VALUES
    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP001', 0.12, -0.98, 0.05),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP001', 0.08, -0.97, 0.11),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP001', -0.05, -1.01, 0.02),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP001', 0.15, -0.99, -0.03),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP001', 0.10, -0.98, 0.08),

    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP002', -0.22, -1.05, 0.18),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP002', -0.18, -1.02, 0.15),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP002', -0.25, -1.08, 0.12),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP002', -0.20, -1.03, 0.20),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP002', -0.15, -1.00, 0.16),

    (gen_random_uuid(), NOW() - INTERVAL '1 hour', 'BRP003', 0.05, -0.95, -0.08),
    (gen_random_uuid(), NOW() - INTERVAL '2 hours', 'BRP003', 0.02, -0.96, -0.12),
    (gen_random_uuid(), NOW() - INTERVAL '3 hours', 'BRP003', 0.08, -0.94, -0.05),
    (gen_random_uuid(), NOW() - INTERVAL '1 day', 'BRP003', 0.03, -0.97, -0.10),
    (gen_random_uuid(), NOW() - INTERVAL '2 days', 'BRP003', 0.07, -0.95, -0.06);
