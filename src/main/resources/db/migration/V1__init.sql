CREATE TABLE IF NOT EXISTS "person"
(
    "id"                 BIGSERIAL PRIMARY KEY,
    "version"            INTEGER         NOT NULL DEFAULT 0,
    "created_date"       TIMESTAMP       NOT NULL,
    "last_modified_date" TIMESTAMP       NOT NULL,
    "national_id"        CHAR(10) UNIQUE NOT NULL,
    "first_name"         VARCHAR(50),
    "last_name"          VARCHAR(50),
    "birthdate"          DATE
);

CREATE TABLE IF NOT EXISTS "course"
(
    "id"                 BIGSERIAL PRIMARY KEY,
    "version"            INTEGER   NOT NULL DEFAULT 0,
    "created_date"       TIMESTAMP NOT NULL,
    "last_modified_date" TIMESTAMP NOT NULL,
    "instructor_id"      BIGINT REFERENCES "person" (id),
    "title"              VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS "person_course"
(
    PRIMARY KEY ("person_id", "course_id"),
    "version"            INTEGER   NOT NULL DEFAULT 0,
    "created_date"       TIMESTAMP NOT NULL,
    "last_modified_date" TIMESTAMP NOT NULL,
    "person_id"          BIGINT REFERENCES "person" (id),
    "course_id"          BIGINT REFERENCES "course" (id)
);
