create table units
(
	id bigint not null
		constraint units_pkey
			primary key,
	date_created timestamp,
	date_modified timestamp,
	is_delete boolean,
	unit varchar(255)
);

alter table units owner to postgres;

