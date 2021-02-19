create table dishes
(
	id bigint not null
		constraint dishes_pkey
			primary key,
	amount integer not null,
	date_created timestamp,
	date_modified timestamp,
	dish varchar(255),
	ingredients varchar(255),
	is_delete boolean,
	price numeric(19,2),
	unit_id bigint
		constraint fkm9btj3kq1rrcju4rbdpoon65o
			references units
);

alter table dishes owner to postgres;

