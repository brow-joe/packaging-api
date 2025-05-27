INSERT INTO department (name) VALUES 
    ('Vila do Chaves'),
    ('Escola do Professor Girafales');

INSERT INTO title (name) VALUES 
    ('Professor de Primário'),
    ('Professor Visitante');

INSERT INTO professor (name, department_id, title_id) VALUES
    ('Professor Girafales', 2, 1),
    ('Seu Madruga', 1, 2),
    ('Dona Clotilde', 1, 1);

INSERT INTO subject (code, name) VALUES
    ('VILA101', 'Como Ficar Devendo Aluguel'),
    ('VILA102', 'Defesa Contra Sapatos da Dona Florinda'),
    ('ESC101', 'Boa Conduta na Sala de Aula');

INSERT INTO subject_prerequisite (subject_id, prerequisite_id) VALUES
    (2, 1);

INSERT INTO building (name) VALUES 
    ('Pátio da Vila'),
    ('Escola Municipal');

INSERT INTO room (building_id, name) VALUES
    (1, 'Barril do Chaves'),
    (1, 'Casa da Dona Florinda'),
    (2, 'Sala 1');

INSERT INTO class (subject_id, yenr, semester, code, professor_id) VALUES
    (1, 2025, 1, 'VILA101-01', 2),
    (2, 2025, 1, 'VILA102-01', 3),
    (3, 2025, 1, 'ESC101-01', 1);

INSERT INTO class_schedule (class_id, room_id, day_of_week, start_time, end_time) VALUES
    (1, 1, 'Monday', '08:00', '10:00'),
    (1, 1, 'Wednesday', '08:00', '10:00'),

    (2, 2, 'Tuesday', '10:00', '12:00'),
    (2, 2, 'Thursday', '10:00', '12:00'),

    (3, 3, 'Monday', '10:00', '12:00');
