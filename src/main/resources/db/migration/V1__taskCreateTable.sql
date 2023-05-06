
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

INSERT INTO public.ticket(
	id, movie, theater, room, "time", language, subtitles, exhibition)
	VALUES ('c7cbc04d-a89a-4515-9dcb-8193cc0452b3', 'Guardiões da Galaxia vol 3','Cinemark Aricanduva', '9', '21h30m','DUB', false, 'XD 3D');