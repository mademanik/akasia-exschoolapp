CREATE TABLE public.extracurriculars
(
    id                      bigserial NOT NULL,
    created_date            timestamp(6) NULL,
    last_updated_date       timestamp(6) NULL,
    description             varchar(255) NULL,
    end_date                timestamp(6) NULL,
    "location"              varchar(255) NULL,
    "name"                  varchar(255) NULL,
    registration_end_date   timestamp(6) NULL,
    registration_start_date timestamp(6) NULL,
    start_date              timestamp(6) NULL,
    quota                   int4 NULL,
    mentor_id               int8 NULL,
	CONSTRAINT extracurriculars_pkey PRIMARY KEY (id)
);

-- public.extracurriculars foreign keys
ALTER TABLE public.extracurriculars
    ADD CONSTRAINT fknhk3ufsj3oefodm9m4oyrpysn FOREIGN KEY (mentor_id) REFERENCES public.mentors (id);