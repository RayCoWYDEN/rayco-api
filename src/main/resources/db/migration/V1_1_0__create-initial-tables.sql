SET search_path = rayco_schema;

CREATE TABLE courses (
    id BIGINT PRIMARY KEY DEFAULT nextval('id_courses_seq'),
    name VARCHAR(255) NOT NULL
);


CREATE TABLE entry_types (
    id BIGINT PRIMARY KEY DEFAULT nextval('id_entry_types_seq'),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE universities (
    id BIGINT PRIMARY KEY DEFAULT nextval('id_universities_seq'),
    name VARCHAR(255) NOT NULL,
    average_rank DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    private BOOLEAN NOT NULL
);

CREATE TABLE academics_info (
    id BIGINT PRIMARY KEY DEFAULT nextval('id_academics_info_seq'),
    id_course BIGINT NOT NULL,
    period INT NOT NULL,
    tuition_fee DOUBLE PRECISION NOT NULL,
    id_university BIGINT NOT NULL,
    CONSTRAINT fk_academics_course FOREIGN KEY (id_course) REFERENCES courses (id),
    CONSTRAINT fk_academics_university FOREIGN KEY (id_university) REFERENCES universities (id)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('id_users_seq'),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    id_academic_info BIGINT,
    CONSTRAINT fk_user_academic_info FOREIGN KEY (id_academic_info) REFERENCES academics_info (id),
    CONSTRAINT unique_user_email UNIQUE (email)
);

CREATE TABLE university_course (
    id_university BIGINT NOT NULL,
    id_course BIGINT NOT NULL,
    PRIMARY KEY (id_university, id_course),
    CONSTRAINT fk_university_course_university FOREIGN KEY (id_university) REFERENCES universities (id),
    CONSTRAINT fk_university_course_course FOREIGN KEY (id_course) REFERENCES courses (id)
);

CREATE TABLE university_entry_type (
    id_university BIGINT NOT NULL,
    id_entry_type BIGINT NOT NULL,
    PRIMARY KEY (id_university, id_entry_type),
    CONSTRAINT fk_university_entry_type_university FOREIGN KEY (id_university) REFERENCES universities (id),
    CONSTRAINT fk_university_entry_type_entry_type FOREIGN KEY (id_entry_type) REFERENCES entry_types (id)
);

