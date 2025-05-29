CREATE TABLE rayco_schema.favorite_universities (
    id_user BIGINT NOT NULL,
    id_university BIGINT NOT NULL,
    PRIMARY KEY (id_user, id_university),
    CONSTRAINT fk_favorite_user FOREIGN KEY (id_user) REFERENCES rayco_schema.users(id) ON DELETE CASCADE,
    CONSTRAINT fk_favorite_university FOREIGN KEY (id_university) REFERENCES rayco_schema.universities(id) ON DELETE CASCADE
);
