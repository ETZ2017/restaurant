create table clients
(
	id bigint not null
		constraint clients_pkey
			primary key,
	apt integer not null,
	date_created timestamp,
	date_modified timestamp,
	first_name varchar(255),
	house varchar(255),
	is_delete boolean,
	last_name varchar(255),
	street varchar(255)
);

alter table clients owner to postgres;

