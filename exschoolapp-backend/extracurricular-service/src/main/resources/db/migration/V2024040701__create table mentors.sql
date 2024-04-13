CREATE TABLE public.mentors
(
    id                bigserial NOT NULL,
    created_date      timestamp(6) NULL,
    last_updated_date timestamp(6) NULL,
    address           varchar(255) NULL,
    email             varchar(255) NULL,
    gender            varchar(255) NULL,
    "name"            varchar(255) NULL,
    phone_number      varchar(255) NULL,
	CONSTRAINT mentors_pkey PRIMARY KEY (id)
);