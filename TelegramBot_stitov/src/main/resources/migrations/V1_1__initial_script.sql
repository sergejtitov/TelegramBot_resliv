create table m_city
(
	city_id bigint not null
		constraint m_city_pkey
			primary key,
	name varchar(100) not null,
	country varchar(100) not null,
	comment varchar(1000)
);

alter table m_city owner to postgres;

create unique index m_city_name_uindex
	on m_city (name);

create table m_sights
(
	sights_id bigint not null
		constraint m_sights_pkey
			primary key,
	city_id bigint not null
		constraint m_sights_m_city_city_id_fk
			references m_city
				on update cascade on delete cascade,
	name varchar(100) not null,
	location varchar(200),
	sights_comment varchar(1000)
);

alter table m_sights owner to postgres;

create index m_sights_city_id_index
	on m_sights (city_id);

CREATE SEQUENCE public.m_city_id_seq
    INCREMENT 1
    START 15
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.m_city_id_seq
    OWNER TO postgres;

CREATE SEQUENCE public.m_sights_id_seq
    INCREMENT 1
    START 19
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.m_sights_id_seq
    OWNER TO postgres;

