CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE title (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    department_id INTEGER NOT NULL,
    title_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(id),
    FOREIGN KEY (title_id) REFERENCES title(id)
);

CREATE TABLE subject (
    id SERIAL PRIMARY KEY,
    subject_id INTEGER,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE subject_prerequisite (
    id SERIAL PRIMARY KEY,
    subject_id INTEGER NOT NULL,
    prerequisite_id INTEGER NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(id),
    FOREIGN KEY (prerequisite_id) REFERENCES subject(id)
);

CREATE TABLE building (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE room (
    id SERIAL PRIMARY KEY,
    building_id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    FOREIGN KEY (building_id) REFERENCES building(id)
);

CREATE TABLE class (
    id SERIAL PRIMARY KEY,
    subject_id INTEGER NOT NULL,
    yenr INTEGER NOT NULL,
    semester INTEGER NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    professor_id INTEGER,
    FOREIGN KEY (subject_id) REFERENCES subject(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE SET NULL
);

CREATE TABLE class_schedule (
    id SERIAL PRIMARY KEY,
    class_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    day_of_week VARCHAR(10) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    FOREIGN KEY (class_id) REFERENCES class(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
);
