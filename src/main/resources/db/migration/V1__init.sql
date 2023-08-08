CREATE TABLE "person" (
    "id" SERIAL PRIMARY KEY,
    "version" INTEGER NOT NULL DEFAULT 0,
    "created_date" TIMESTAMP NOT NULL,
    "last_modified_date" TIMESTAMP NOT NULL,
    "national_id" CHAR(10) UNIQUE NOT NULL,
    "first_name" VARCHAR,
    "last_name" VARCHAR,
    "birthdate" DATE
);
