CREATE TABLE IF NOT EXISTS "login" (
    "id" UUID,
    "user" VARCHAR(20) NOT NULL,
    "password" VARCHAR(8) NOT NULL,
    CONSTRAINT "pk_login" PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "ticket" (
    "id" UUID,
    "movie" VARCHAR(200) NOT NULL,
    "theater" VARCHAR(200) NOT NULL,
    "room" VARCHAR(200) NOT NULL,
    "time" VARCHAR(200)  NULL,
    "language" VARCHAR(200)  NULL,
    "subtitles" boolean NULL,
    "exhibition" VARCHAR(200)  NULL,
    CONSTRAINT "pk_ticket" PRIMARY KEY ("id")
);
