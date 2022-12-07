
CREATE TABLE IF NOT EXISTS Fines.fines
(
    "fineId" bigint NOT NULL,
    "carNumber" character varying COLLATE pg_catalog."default" NOT NULL,
    username text COLLATE pg_catalog."default" NOT NULL,
    "usernameGai" text COLLATE pg_catalog."default" NOT NULL,
    "protocolDate" timestamp without time zone,
    fine double precision,
    court boolean,
    "fineWasPaid" boolean,
    "datePaid" timestamp without time zone,
    "lastDayToPay" timestamp without time zone,
    do_id bigint,
    CONSTRAINT fines_pkey PRIMARY KEY ("fineId")
)

TABLESPACE pg_default;